package lords.logic.green.rest.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lords.logic.green.rest.dto.Auth;
import lords.logic.green.rest.dto.SignUp;
import lords.logic.green.rest.mapper.UserMapper;
import lords.logic.green.service.UserService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
@Tag(name = "Auth")
public class AuthController {
    private final UserMapper mapper;
    private final UserService service;

    @PostMapping("/signup")
    public Map<String, String> signUp(@RequestBody SignUp auth) {
        return service.signUp(auth);
    }

    @PostMapping("/signin")
    public Map<String, String> signIn(@RequestBody Auth toAuthenticate) {
        return service.signIn(toAuthenticate);
    }

}
