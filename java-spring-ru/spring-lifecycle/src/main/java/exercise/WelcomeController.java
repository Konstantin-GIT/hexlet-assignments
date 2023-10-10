package exercise;

import exercise.daytimes.Daytime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

// BEGIN
@RestController
@RequestScope
public class WelcomeController {

    @Autowired
    private Daytime daytime;

    @Autowired
    private Meal meal;

    @GetMapping(path = "/daytime")
    public String getGreeting() {
        String timeOfDay = daytime.getName();
        String mealTime = meal.getMealForDaytime(timeOfDay);

        return "It is " + timeOfDay + " now. Enjoy your " + mealTime;
    }

}
// END
