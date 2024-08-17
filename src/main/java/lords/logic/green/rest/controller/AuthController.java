package lords.logic.green.rest.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lords.logic.green.rest.dto.Auth;
import lords.logic.green.rest.dto.AuthResponse;
import lords.logic.green.rest.dto.AuthResponseDto;
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
    public AuthResponseDto signUp(@RequestBody SignUp auth) {
        AuthResponse response = service.signUp(auth);
        return AuthResponseDto.builder()
                .user(mapper.toUserDto(response.getUser()))
                .token(response.getToken())
                .build();
    }

    @PostMapping("/signin")
    public AuthResponseDto signIn(@RequestBody Auth toAuthenticate) {
        AuthResponse response = service.signIn(toAuthenticate);
        return AuthResponseDto.builder()
                .user(mapper.toUserDto(response.getUser()))
                .token(response.getToken())
                .build();
    }

}
