package lords.logic.green.service;

import lombok.AllArgsConstructor;
import lords.logic.green.model.Trip;
import lords.logic.green.model.exception.NotFoundException;
import lords.logic.green.repository.TripRepository;
import lords.logic.green.repository.TripRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TripService {
    private final TripRepository repository;
    public Trip getTrip(String tripId){
        return repository.findById(tripId).orElseThrow(() -> new NotFoundException("Trip of" +
                " id: " + tripId + " not found."));
    }

    public List<Trip> getTrips(){
        return repository.findAll();
    }

    public List<Trip> crupdateTrips(List<Trip> toSave) {
        List<Trip> saved = repository.saveAll(toSave);
        saved.forEach((trip) ->
                trip.getTransport().setCo2Emission(200d));
        return  saved;
    }
}
