package com.app.backend.weather.controller;

import com.app.backend.common.model.JsonResponse;
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
    public JsonResponse<Map<String, String>> getWeather(@RequestParam String city) {
        Map<String, String> weatherData = weatherService.getWeather(city);
        return new JsonResponse<>(weatherData);
    }
}
