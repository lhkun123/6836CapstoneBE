package com.app.backend.weather.controller;

import com.app.backend.common.model.JsonResponse;
import com.app.backend.weather.service.RecommendationService;
import com.app.backend.user.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class RecommendationController {
    @Autowired
    private RecommendationService recommendationService;

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/recommendation")
    public JsonResponse<Map<String, Object>> getRecommendation(@RequestParam String city) {
        Map<String, String> weatherData = weatherService.getWeather(city);
        Map<String, Object> recommendation = recommendationService.generateRecommendation(weatherData);
        return new JsonResponse<>(recommendation);
    }
}
