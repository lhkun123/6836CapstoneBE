package com.app.backend.weather.service;

import com.app.backend.common.config.OpenAIConfig;
import com.app.backend.weather.model.Field;
import com.app.backend.weather.repository.FieldRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FieldService {
    @Autowired
    private FieldRepository fieldRepository;

    @Autowired
    private OpenAIConfig openAIConfig;

    public List<Field> getAllFields() {
        return fieldRepository.findAll();
    }

    public Field getFieldByName(String name){
        return fieldRepository.findFieldByName(name);
    }
    public String getRecommendationFromGPT(String location){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + openAIConfig.getApiKey());
        headers.set("Content-Type", "application/json");

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", "gpt-4o-mini");
        requestBody.put("messages", new Object[]{
              new HashMap<String, String>() {{
                  put("role", "user");
                  put("content", "What are top 10 trails near"+location+"please show the results in follwing format:\n" +
                        "Name:\n" +
                        "Distance:\n" +
                        "Rating:\n" +
                        "Description:\n" +
                        "Estimated Time:\n" +
                        "Difficulty:\n" +
                        "Location:");
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

    public Field getFieldById(Long id) {
        return fieldRepository.findById(id).orElse(null);
    }

    public Field saveField(Field field) {
        return fieldRepository.save(field);
    }

    public void deleteField(Long id) {
        fieldRepository.deleteById(id);
    }
}
