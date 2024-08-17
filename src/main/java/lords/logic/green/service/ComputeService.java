package lords.logic.green.service;

import lombok.AllArgsConstructor;
import lords.logic.green.model.Transport;
import lords.logic.green.model.Trip;
import lords.logic.green.model.exception.NotFoundException;
import lords.logic.green.repository.TransportRepository;
import lords.logic.green.repository.TripRepository;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
@AllArgsConstructor
public class ComputeService {

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
}
