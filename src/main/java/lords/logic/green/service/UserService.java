package lords.logic.green.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lords.logic.green.model.Principal;
import lords.logic.green.model.Role;
import lords.logic.green.model.User;
import lords.logic.green.model.exception.NotFoundException;
import lords.logic.green.repository.UserRepository;
import lords.logic.green.rest.dto.Auth;
import lords.logic.green.rest.dto.AuthResponse;
import lords.logic.green.rest.dto.SignUp;
import lords.logic.green.security.JwtService;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository repository;
    private final UserDetailsServiceImpl userDetailsServiceImpl;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    public User getUser(String userId){
        return repository.findById(userId).orElseThrow(() -> new NotFoundException("User of" +
                " id: " + userId + " not found."));
    }

    public List<User> getUsers(){
        return repository.findAll();
    }

    public AuthResponse signIn(Auth toAuthenticate) {
        String email = toAuthenticate.getEmail();
        Optional<User> existingUser = repository.findByEmail(email);
        UserDetails principal = userDetailsServiceImpl.loadUserByUsername(email);
        if (!passwordEncoder.matches(toAuthenticate.getPassword(), principal.getPassword())) {
            throw new BadCredentialsException("Wrong Password!");
        }
        Map<String, String>  token =    jwtService.generate(principal);
        return AuthResponse.builder()
                .token(token.get("token"))
                .user(existingUser.get()).build();
    }

    @Transactional
    public AuthResponse signUp(SignUp user) {
        String email = user.getEmail();
        Optional<User> existingUser = repository.findByEmail(email);
        if (existingUser.isPresent()) {
            throw new DuplicateKeyException("User with the email address: " + email + " already exists.");
        }
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        User createdUser =
                crupdateUsers(
                        List.of(
                                User.builder()
                                        .username(user.getUsername())
                                        .email(user.getEmail())
                                        .role(Role.USER)
                                        .password(hashedPassword)
                                        .build()))
                        .get(0);
        Principal principal = Principal.builder().user(createdUser).build();
        Map<String, String> token = jwtService.generate(principal);
        return AuthResponse.builder()
                .token(token.get("token"))
                .user(createdUser).build();
    }

    @Transactional
    public List<User> crupdateUsers(List<User> toCrupdate) {
        return repository.saveAll(toCrupdate);
    }
}
