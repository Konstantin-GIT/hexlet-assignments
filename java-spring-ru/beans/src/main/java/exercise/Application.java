package exercise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

import exercise.daytime.Daytime;
import exercise.daytime.Day;
import exercise.daytime.Night;
import org.springframework.context.annotation.Bean;


// BEGIN
import org.springframework.web.context.annotation.RequestScope;
// END

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // BEGIN
    @Bean
    @RequestScope
    public Daytime getName() { // имя метода не важно
        int hour = LocalDateTime.now().toLocalTime().getHour();
        System.out.println("Datetimee now = " + hour);
        if (hour >= 6 && hour < 22) {
        return new Day();
        }
        else return new Night();
    }
    // END
}
