package exercise.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import exercise.HttpClient;

import java.util.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import exercise.CityNotFoundException;
import exercise.repository.CityRepository;
import exercise.model.City;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.text.html.Option;


@Service
public class WeatherService {

    @Autowired
    CityRepository cityRepository;

    // Клиент
    HttpClient client;

    private ObjectMapper objectMapper = new ObjectMapper();

    // При создании класса сервиса клиент передаётся снаружи
    // В теории это позволит заменить клиент без изменения самого сервиса
    WeatherService(HttpClient client) {
        this.client = client;
    }

    // BEGIN
    public Map<String, String> getWeatherOfCity(Long id) throws JsonProcessingException {
        var city = cityRepository.findById(id)
            .orElseThrow(() -> new CityNotFoundException("City not found"));

        return objectMapper.readValue(client.get("http://weather/api/v2/cities/" + city.getName()), Map.class );
        }

    public List<Map<String, String>> getAllCitiesAndWeather(List<City> cities) throws CityNotFoundException, JsonProcessingException {
        List<Map<String, String>> citiesAndWeather = new ArrayList<>();
        for (City city : cities) {
            Map<String, String> currentCity = objectMapper.readValue(client.get("http://weather/api/v2/cities/" + city.getName()), Map.class );
            Map<String, String> cityAndWeather = new HashMap<>();
            cityAndWeather.put("temperature", currentCity.get("temperature"));
            cityAndWeather.put("name", currentCity.get("name"));
            citiesAndWeather.add(cityAndWeather);
        }

        return citiesAndWeather;

    }
    // END
}
