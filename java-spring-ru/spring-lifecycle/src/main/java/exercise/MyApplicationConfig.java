package exercise;

import java.time.LocalDateTime;

import exercise.daytimes.Daytime;
import exercise.daytimes.Morning;
import exercise.daytimes.Day;
import exercise.daytimes.Evening;
import exercise.daytimes.Night;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.RequestScope;

// BEGIN
@Configuration
public class MyApplicationConfig {

    @Bean
    public Daytime getDaytime() {
        int hour = LocalDateTime.now().toLocalTime().getHour();
       // System.out.println("Datetimee now = " + hour);
        if (hour >= 6 && hour < 12) {
            return new Morning();
        } else if (hour >= 12 && hour < 18) {
            return new Day();
        } else if (hour >= 18 && hour < 23) {
            return new Evening();
        } else
            return new Night();

    }

}
// END
