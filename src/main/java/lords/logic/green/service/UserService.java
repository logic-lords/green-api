package lords.logic.green.service;

import lombok.AllArgsConstructor;
import lords.logic.green.model.User;
import lords.logic.green.model.exception.NotFoundException;
import lords.logic.green.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository repository;
    public User getUser(String userId){
        return repository.findById(userId).orElseThrow(() -> new NotFoundException("User of" +
                " id: " + userId + " not found."));
    }

    public List<User> getUsers(){
        return repository.findAll();
    }
}
