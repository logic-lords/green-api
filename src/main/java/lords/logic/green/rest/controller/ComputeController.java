package lords.logic.green.rest.controller;

import lombok.AllArgsConstructor;
import lords.logic.green.model.Trip;
import lords.logic.green.model.User;
import lords.logic.green.rest.dto.RankingDto;
import lords.logic.green.rest.dto.TripEmissionDto;
import lords.logic.green.rest.dto.TripSpecsDto;
import lords.logic.green.rest.mapper.TripMapper;
import lords.logic.green.service.ComputeService;
import lords.logic.green.service.TripService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class ComputeController {
    private ComputeService computeService;
    private TripService tripService;
    private TripMapper tripMapper;
    @PostMapping("/users/{userId}/trips-list/compute")
    public TripEmissionDto getTripEmission(@RequestBody List<TripSpecsDto> specs,
                                           @PathVariable String userId){
        List<Trip> saved = tripService.crupdateTrips(specs.stream().map(
                (trip) -> tripMapper.toDomain(trip, userId)
        ).toList());
        return TripEmissionDto.builder()
                .emission(computeService.computeFromTripList(saved)).build();
    }

    @GetMapping("/users/weekly-rank/compute")
    public List<RankingDto> getTripEmission(){
        LinkedHashMap<User, TripEmissionDto> userRankings = computeService.computeWeeklyUserRank();
        List<RankingDto> rankingList = userRankings.entrySet().stream()
                .map(entry -> new RankingDto(entry.getKey().getUsername(), entry.getValue().getEmission()))
                .collect(Collectors.toUnmodifiableList());
        return rankingList;
    }
}
