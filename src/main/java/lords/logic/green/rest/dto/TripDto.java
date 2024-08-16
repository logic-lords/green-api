package lords.logic.green.rest.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TripDto {
    private String id;
    private Double distance;
    private String userId;
    private String transportId;
}
