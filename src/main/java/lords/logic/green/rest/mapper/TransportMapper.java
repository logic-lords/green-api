package lords.logic.green.rest.mapper;

import lords.logic.green.model.Transport;
import lords.logic.green.model.enums.TransportType;
import lords.logic.green.model.enums.TransportTypeSize;
import lords.logic.green.rest.dto.TransportDto;
import org.springframework.stereotype.Component;

@Component
public class TransportMapper {
    public TransportDto toTransportDto(Transport user) {
        return TransportDto.builder()
                .id(user.getId())
                .placeNumber(user.getPlacesNumber())
                .type(user.getType().name())
                .fuelConsumptionPerKm(user.getFuelConsumptionPerKm())
                .co2Emission(user.getCo2Emission())
                .build();
    }

    public Transport toDomain(TransportDto user) {
        return Transport.builder()
                .id(user.getId())
                .placesNumber(user.getPlaceNumber())
                .type(TransportType.valueOf(user.getType()))
                .fuelConsumptionPerKm(user.getFuelConsumptionPerKm())
                .co2Emission(user.getCo2Emission())
                .size(TransportTypeSize.valueOf(user.getSize()))
                .build();
    }
}
