package lords.logic.green.rest.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
@Tag(name = "Health")
public class HealthController {


    @GetMapping("/ping")
    private String ping(){
        return "pong";
    }
}
