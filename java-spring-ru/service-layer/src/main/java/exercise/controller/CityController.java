package exercise.controller;
import com.fasterxml.jackson.core.JsonProcessingException;
import exercise.CityNotFoundException;
import exercise.model.City;
import exercise.repository.CityRepository;
import exercise.service.WeatherService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequiredArgsConstructor
public class CityController {

    private final CityRepository cityRepository;

    private final WeatherService weatherService;

    // BEGIN
    @GetMapping(path="/search")
    List<Map<String, String>> getCityByName(@RequestParam(required = false) String name) throws JsonProcessingException {
        List<City> cities;
        if (name == null || name.isEmpty()) {
            cities = cityRepository.findAllByOrderByNameAsc();
        } else {
            cities = cityRepository.findAllByNameStartingWithIgnoreCase(name);
        }
        return weatherService.getAllCitiesAndWeather(cities);
    }


    @GetMapping(path="/cities/{id}")
    Map<String, String> getWeatherOfCity(@PathVariable Long id) throws JsonProcessingException {
        return Optional.of(weatherService.getWeatherOfCity(id))
            .orElseThrow(() -> new CityNotFoundException("City not found"));
    }

    // END
}

