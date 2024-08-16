package lords.logic.green.rest.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransportDto {
    private String id;
    private String type;
    private String size;
    private int placeNumber;
    private Double co2Emission;
    private Double fuelConsumptionPerKm;
}
