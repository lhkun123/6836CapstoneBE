package com.app.backend.weather.controller;

import com.app.backend.common.model.JsonResponse;
import com.app.backend.weather.model.Field;
import com.app.backend.weather.service.FieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/fields")
public class FieldController {
    @Autowired
    private FieldService fieldService;

    @GetMapping
    public JsonResponse<List<Field>> getAllFields() {
        List<Field> fields = fieldService.getAllFields();
        return new JsonResponse<>(fields);
    }
}
