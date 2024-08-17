package lords.logic.green.rest.mapper;

import lombok.AllArgsConstructor;
import lords.logic.green.model.Transport;
import lords.logic.green.model.Trip;
import lords.logic.green.model.enums.TransportType;
import lords.logic.green.model.enums.TransportTypeSize;
import lords.logic.green.rest.dto.TripDto;
import lords.logic.green.rest.dto.TripSpecsDto;
import lords.logic.green.service.TransportService;
import lords.logic.green.service.UserService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class TripMapper {
    private UserService userService;
    private TransportService transportService;
    public TripDto toTripDto(Trip trip) {
        return TripDto.builder()
                .transportId(trip.getId())
                .build();
    }

    public Trip toDomain(TripSpecsDto tripSpecsDto, String userId) {
        List<Transport> saved =
                transportService.crupdateTransport(List.of(Transport.builder()
                .type(TransportType.valueOf(tripSpecsDto.getCarType().toUpperCase()))
                                .size(TransportTypeSize.MID)
                                .fuelConsumptionPerKm(tripSpecsDto.getFuelConsumption())
                .build()));

        return Trip.builder()
                .user(userService.getUser(userId))
                .distance(tripSpecsDto.getKilometers())
                .onboard(tripSpecsDto.getPassengers())
                .transport(saved.get(0))
                .build();
    }
}
