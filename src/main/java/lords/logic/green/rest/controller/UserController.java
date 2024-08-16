package lords.logic.green.rest.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lords.logic.green.rest.dto.TripDto;
import lords.logic.green.rest.dto.TripEmissionDto;
import lords.logic.green.rest.dto.UserDto;
import lords.logic.green.rest.mapper.TripEmissionMapper;
import lords.logic.green.rest.mapper.TripMapper;
import lords.logic.green.rest.mapper.UserMapper;
import lords.logic.green.service.ComputeService;
import lords.logic.green.service.TripService;
import lords.logic.green.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@RestController("/users")
@AllArgsConstructor
@Tag(name = "User")
public class UserController {
    private final UserService service;
    private final UserMapper mapper;
    private final TripService tripService;
    private final TripMapper tripMapper;
    private final ComputeService computeService;
    private final TripEmissionMapper tripEmissionMapper;

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

    @GetMapping("/{userId}/trips/{tripId}/compute")
    public TripEmissionDto getTripEmission(@PathVariable String userId, @PathVariable String tripId) {
        return tripEmissionMapper.toTripEmissionDto(computeService.computeTripCO2Emission(tripId));
    }

    @GetMapping("/{userId}/daily/{date}/compute")
    public TripEmissionDto getDailyEmission(@PathVariable String userId, @PathVariable LocalDate date) {
        return tripEmissionMapper.toTripEmissionDto(computeService.computeDailyCO2Consumption(userId, date));
    }

    @GetMapping("/{userId}/trips")
    public List<TripDto> getTrips(){
        return tripService.getTrips().stream().map(tripMapper::toTripDto).toList();
    }
}
