package lords.logic.green.rest.controller;

import lombok.AllArgsConstructor;
import lords.logic.green.rest.dto.TripDto;
import lords.logic.green.rest.dto.UserDto;
import lords.logic.green.rest.mapper.TripMapper;
import lords.logic.green.rest.mapper.UserMapper;
import lords.logic.green.service.TripService;
import lords.logic.green.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/users")
@AllArgsConstructor
public class UserController {
    private final UserService service;
    private final UserMapper mapper;
    private final TripService tripService;
    private final TripMapper tripMapper;

    @GetMapping
    public List<UserDto> getUsers(){
        return service.getUsers().stream().map(mapper::toUserDto).toList();
    }

    @GetMapping("/{userId}")
    public UserDto getUser(@PathVariable String userId){
        return mapper.toUserDto(service.getUser(userId));
    }

    @GetMapping("/{userId}/trips/{tripId}")
    public TripDto getTrip(@PathVariable String tripId){
        return tripMapper.toTripDto(tripService.getTrip(tripId));
    }

    @GetMapping("/{userId}/trips")
    public List<TripDto> getTrips(){
        return tripService.getTrips().stream().map(tripMapper::toTripDto).toList();
    }
}
