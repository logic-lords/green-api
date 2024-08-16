package lords.logic.green.rest.mapper;

import lords.logic.green.model.Trip;
import lords.logic.green.rest.dto.TripDto;
import org.springframework.stereotype.Component;

@Component
public class TripMapper {
    public TripDto toTripDto(Trip trip) {
        return TripDto.builder()
                .transportId(trip.getId())
                .build();
    }
}
