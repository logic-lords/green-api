package lords.logic.green.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lords.logic.green.model.Activity;
import lords.logic.green.model.exception.NotFoundException;
import lords.logic.green.repository.ActivityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ActivityService {
    private final ActivityRepository repository;
    public Activity getActivity(String transportId){
        return repository.findById(transportId).orElseThrow(() -> new NotFoundException(
                "Activity" +
                " " +
                "of" +
                " id: " + transportId + " not found."));
    }

    public List<Activity> getActivities(){
        return repository.findAll();
    }

    @Transactional
    public List<Activity> crupdateActivity(List<Activity> transports){
        return repository.saveAll(transports);
    }
}
