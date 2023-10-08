package exercise.controller;

import exercise.daytime.Daytime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

// BEGIN


@RestController
public class WelcomeController {


    @Autowired
    private Daytime datetime;

    @GetMapping(path = "/welcome")
    public String getGreeting() {
        return "It is " + datetime.getName() + " now! Welcome to Spring!";
    }
}
// END
