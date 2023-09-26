package exercise;

import exercise.model.User;
import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WelcomeController {

    @GetMapping("/")
    public String root() {
        return "Welcome to Spring";
    }

}
