package com.app.backend.weather.service;

import com.app.backend.weather.model.Field;
import com.app.backend.weather.model.Recommendation;
import com.app.backend.weather.repository.FieldRepository;
import com.app.backend.weather.repository.RecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RecommendationService {
    @Autowired
    private RecommendationRepository recommendationRepository;

    @Autowired
    private FieldRepository fieldRepository;

    public Map<String, Object> generateRecommendation(Map<String, String> weatherData) {
        Map<String, Object> recommendation = new LinkedHashMap<>();
        String weatherType = weatherData.get("weather_type");

        Optional<Recommendation> recommendationEntityOptional = recommendationRepository.findTopByTypeOrderByScoreDesc(weatherType);

        if (recommendationEntityOptional.isPresent()) {
            Recommendation recommendationEntity = recommendationEntityOptional.get();
            recommendation.put("advice", recommendationEntity.getMessage());
        } else {
            recommendation.put("advice", "No recommendation available for the current weather condition.");
        }

        String recommendationType = determineRecommendationType(weatherType);
        boolean showHikingList = !recommendationType.equals("not_recommended");
        recommendation.put("showHikingList", showHikingList);

        if (showHikingList) {
            List<Field> fields = fieldRepository.findTop5ByRecommendationTypeOrderByRatingDesc(recommendationType);
            List<Map<String, Object>> fieldData = new ArrayList<>();
            for (Field field : fields) {
                Map<String, Object> fieldInfo = new HashMap<>();
                fieldInfo.put("name", field.getName());
                fieldInfo.put("rating", field.getRating());
                fieldInfo.put("recommendation_type", field.getRecommendationType());
                fieldInfo.put("location", field.getLocation());
                fieldInfo.put("difficulty", field.getDifficulty());
                fieldInfo.put("distance", field.getDistance());
                fieldInfo.put("estimated_time", field.getEstimatedTime());
                fieldInfo.put("imageUrl", field.getImageUrl());
                fieldInfo.put("previewImageUrl1", field.getPreviewImageUrl1());
                fieldInfo.put("previewImageUrl2", field.getPreviewImageUrl2());
                fieldInfo.put("previewImageUrl3", field.getPreviewImageUrl3());
                fieldInfo.put("previewImageUrl4", field.getPreviewImageUrl4());
                fieldInfo.put("previewImageUrl5", field.getPreviewImageUrl5());
                fieldInfo.put("description", field.getDescription());
                fieldData.add(fieldInfo);
            }
            recommendation.put("fields", fieldData);
        }

        recommendation.putAll(weatherData);
        return recommendation;
    }

    private String determineRecommendationType(String weatherType) {
        switch (weatherType) {
            case "clear_sky":
            case "few_clouds":
                return "highly_recommended";
            case "scattered_clouds":
            case "broken_clouds":
                return "recommended";
            case "shower_rain":
            case "mist":
                return "low_recommended";
            case "rain":
            case "thunderstorm":
            case "snow":
                return "not_recommended";
            default:
                return "not_recommended";
        }
    }
}
