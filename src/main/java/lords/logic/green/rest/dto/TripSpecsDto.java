package lords.logic.green.rest.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TripSpecsDto {
    private String carType;
    private Double kilometers;
    private String fuelType;
    private Double fuelConsumption;
    private Integer passengers;
}
