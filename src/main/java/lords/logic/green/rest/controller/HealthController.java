package lords.logic.green.rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class HealthController {


    @GetMapping("/ping")
    private String ping(){
        return "pong";
    }
}
