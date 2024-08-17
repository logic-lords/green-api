package lords.logic.green.rest.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lords.logic.green.rest.dto.TripDto;
import lords.logic.green.rest.dto.TripEmissionDto;
import lords.logic.green.rest.dto.ActivityDto;
import lords.logic.green.rest.mapper.TripEmissionMapper;
import lords.logic.green.rest.mapper.TripMapper;
import lords.logic.green.rest.mapper.ActivityMapper;
import lords.logic.green.service.ComputeService;
import lords.logic.green.service.TripService;
import lords.logic.green.service.ActivityService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@AllArgsConstructor
@Tag(name = "Activity")
public class ActivityController {
    private final ActivityService service;
    private final ActivityMapper mapper;
    private final ActivityService activityService;

    @GetMapping("/activitys")
    public List<ActivityDto> getActivities(){
        return service.getActivities().stream().map(mapper::toActivityDto).toList();
    }

    @GetMapping("/activitys/{activityId}")
    public ActivityDto getActivity(@PathVariable String activityId){
        return mapper.toActivityDto(service.getActivity(activityId));
    }

    @PutMapping("/activitys")
    public List<ActivityDto> cruptdateActivity(@RequestBody List<ActivityDto> activityDtos){

        return activityService.crupdateActivity(
                activityDtos.stream().map(mapper::toDomain).toList()
        ).stream().map(mapper::toActivityDto).toList();
    }
}
