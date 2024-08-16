package lords.logic.green.rest.mapper;


import lords.logic.green.model.Trip;
import lords.logic.green.rest.dto.TripDto;
import lords.logic.green.rest.dto.TripEmissionDto;
import org.springframework.stereotype.Component;

@Component
public class TripEmissionMapper {
    public TripEmissionDto toTripEmissionDto(Double emission) {
        return TripEmissionDto.builder()
                .emission(emission)
                .build();
    }
}
