package com.app.backend.user.controller.weatherhome;

import com.app.backend.user.model.weatherhome.Field;
import com.app.backend.user.service.FieldService;
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
    public List<Field> getAllFields() {
        return fieldService.getAllFields();
    }
}

