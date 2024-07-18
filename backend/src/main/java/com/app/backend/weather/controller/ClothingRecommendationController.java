package com.app.backend.weather.controller;

import com.app.backend.common.model.JsonResponse;
import com.app.backend.weather.service.ClothingRecommendationService;
import com.app.backend.user.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ClothingRecommendationController {

    @Autowired
    private WeatherService weatherService;

    @Autowired
    private ClothingRecommendationService clothingRecommendationService;

    @GetMapping("/weather-recommendation")
    public JsonResponse<Map<String, String>> getWeatherRecommendation(@RequestParam String city) {
        Map<String, String> weatherData = weatherService.getWeather(city);
        String weatherDescription = String.format(
                "The current temperature in %s is %s°C with %s. The humidity is %s%% and the weather condition is %s.",
                weatherData.get("city"),
                weatherData.get("temperature"),
                weatherData.get("weather_description"),
                weatherData.get("humidity"),
                weatherData.get("weather_condition")
        );

        String recommendation = clothingRecommendationService.getClothingRecommendation(weatherDescription);
        Map<String, String> responseData = new HashMap<>();
        responseData.put("recommendation", recommendation);
        return new JsonResponse<>(responseData);
    }



}

