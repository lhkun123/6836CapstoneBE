package com.app.backend.weather.service;

import com.app.backend.common.config.OpenAIConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

@Service
public class ClothingRecommendationService {

    @Autowired
    private OpenAIConfig openAIConfig;

    public String getClothingRecommendation(String weatherDescription) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + openAIConfig.getApiKey());
        headers.set("Content-Type", "application/json");

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", "gpt-3.5-turbo");
        requestBody.put("messages", new Object[]{
                new HashMap<String, String>() {{
                    put("role", "user");
                    put("content", "The current weather is: " + weatherDescription + ". What should I wear today?");
                }}
        });

        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                openAIConfig.getApiUrl(),
                HttpMethod.POST,
                requestEntity,
                String.class
        );

        String responseBody = response.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        String recommendation = "";
        try {
            JsonNode root = objectMapper.readTree(responseBody);
            recommendation = root.get("choices").get(0).get("message").get("content").asText();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return recommendation;
    }

}
