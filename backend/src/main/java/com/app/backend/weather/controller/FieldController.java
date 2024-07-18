package com.app.backend.weather.controller;

import com.app.backend.common.model.JsonResponse;
import com.app.backend.weather.model.Field;
import com.app.backend.weather.service.FieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FieldController {
    @Autowired
    private FieldService fieldService;

    @GetMapping("/api/fields")
    public JsonResponse<List<Field>> getAllFields() {
        List<Field> fields = fieldService.getAllFields();
        return new JsonResponse<>(fields);
    }

    @GetMapping("/api/field")
    public JsonResponse<Field> getFieldByName(@RequestParam String field) {
        Field result = fieldService.getFieldByName(field);
        return new JsonResponse<>(result);
    }

    @GetMapping("/api/trails-recommendation")
    public JsonResponse<String> getRecommendationFromGPT(@RequestParam String location) {
        String result = fieldService.getRecommendationFromGPT(location);
        return new JsonResponse<>(result);
    }
}
