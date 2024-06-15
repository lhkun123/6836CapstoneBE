package com.app.backend.user.controller.weatherhome;

import com.app.backend.user.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class WeatherController {
    @Autowired
    private WeatherService weatherService;

    @GetMapping("/weather")
    public Map<String, String> getWeather(@RequestParam String city) {
        return weatherService.getWeather(city);
    }
}
