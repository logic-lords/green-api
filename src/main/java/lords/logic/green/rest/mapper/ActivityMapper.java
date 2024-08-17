package lords.logic.green.rest.mapper;

import lords.logic.green.model.Activity;
import lords.logic.green.rest.dto.ActivityDto;
import org.springframework.stereotype.Component;

@Component
public class ActivityMapper {
    public ActivityDto toActivityDto(Activity activity) {
        return ActivityDto.builder()
                .id(activity.getId())
                .name(activity.getName())
                .description(activity.getDescription())
                .price(activity.getPrice())
                .imageUrl(activity.getImageUrl())
                .build();
    }

    public Activity toDomain(ActivityDto activity) {
        return Activity.builder()
                .id(activity.getId())
                .name(activity.getName())
                .imageUrl(activity.getImageUrl())
                .description(activity.getDescription())
                .price(activity.getPrice())
                .build();
    }
}
