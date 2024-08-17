package lords.logic.green.rest.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ActivityDto {
    private String id;
    private String name;
    private String description;
    private String imageUrl;
    private Double price;
}
