package lords.logic.green.service;

import lombok.AllArgsConstructor;
import lords.logic.green.model.Transport;
import lords.logic.green.model.Trip;
import lords.logic.green.model.User;
import lords.logic.green.model.exception.NotFoundException;
import lords.logic.green.repository.TransportRepository;
import lords.logic.green.repository.TripRepository;
import lords.logic.green.rest.dto.TripEmissionDto;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ComputeService {

    private final UserService userService;
    private TripRepository tripRepository;
    private TransportRepository transportRepository;

    public Double computeTripCO2Emission(String tripId) {
        Trip trip = tripRepository.findById(tripId).orElseThrow(() -> new NotFoundException("Trip of" +
                " id: " + tripId + " not found."));

        String transportId = trip.getTransport().getId();
        Transport transport = transportRepository.findById(transportId).orElseThrow(() -> new NotFoundException("Transport of" +
                " id: " + transportId + " not found."));

        return (trip.getDistance() * transport.getFuelConsumptionPerKm() * transport.getCo2Emission()) / trip.getOnboard();
    }

    public Double computeTripCO2Emission(Double distance, Transport transport, Integer onboard) {
        return (distance * transport.getFuelConsumptionPerKm() * transport.getCo2Emission()) / onboard;
    }

    public Double computeDailyCO2Consumption(String userId, LocalDate date) {
        List<Trip> trips = tripRepository.findTripByUserId(userId);

        List<Trip> filteredTrips = trips.stream()
                .filter(trip -> trip.getCreatedDatetime().atZone(ZoneId.systemDefault()).toLocalDate().equals(date))
                .toList();

        Double co2Consumption = 0.0;
        for (Trip userTrip : filteredTrips) {
            co2Consumption += computeTripCO2Emission(userTrip.getId());
        }

        return co2Consumption;
    }

    public Double computeWeeklyCO2Consumption(String userId, LocalDate currentDate) {
        LocalDate startOfWeek = currentDate.with(DayOfWeek.MONDAY);
        LocalDate endOfWeek = currentDate.with(DayOfWeek.SUNDAY);

        List<Trip> trips = tripRepository.findTripByUserId(userId);

        List<Trip> filteredTrips = trips.stream()
                .filter(trip -> {
                    LocalDate tripDate = trip.getCreatedDatetime().atZone(ZoneId.systemDefault()).toLocalDate();
                    return !tripDate.isBefore(startOfWeek) && !tripDate.isAfter(endOfWeek);
                })
                .toList();

        Double co2Consumption = 0.0;
        for (Trip userTrip : filteredTrips) {
            co2Consumption += computeTripCO2Emission(userTrip.getId());
        }

        return co2Consumption;
    }

    public HashMap<String, Double> compareTransport(Double distance, Integer onboard) {

        List<Transport> transports = transportRepository.findAll();

        HashMap<String, Double> comparedMap = new HashMap<>();
        for (Transport transport : transports) {
            comparedMap.put(transport.getType().name(), computeTripCO2Emission(distance, transport, onboard));
        }

        return comparedMap;
    }

    public Double computeFromTripList(List<Trip> tripList) {
        AtomicReference<Double> totalEmission = new AtomicReference<>(0d);
        tripList.forEach(
                (trip) -> {
                    totalEmission.updateAndGet(v -> v + computeTripCO2Emission(trip.getDistance(), trip.getTransport(),
                            trip.getOnboard()));
                }
        );

        return totalEmission.get();
    }

    public LinkedHashMap<User, TripEmissionDto> computeWeeklyUserRank() {
        List<User> users = userService.getUsers();
        HashMap<User, TripEmissionDto> weeklyUsers = new HashMap<>();
                users.forEach(
                        (user -> {
                            weeklyUsers.put(user, TripEmissionDto.builder()
                                    .emission(computeWeeklyCO2Consumption(user.getId(),
                                            LocalDate.now())).build());
                        })

                );
        List<Map.Entry<User, TripEmissionDto>> sortedUsers = weeklyUsers.entrySet()
                .stream()
                .sorted(Comparator.comparing(entry -> entry.getValue().getEmission()))  // Sort by emission
                .collect(Collectors.toUnmodifiableList());
        LinkedHashMap<User, TripEmissionDto> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<User, TripEmissionDto> entry : sortedUsers) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }
}
