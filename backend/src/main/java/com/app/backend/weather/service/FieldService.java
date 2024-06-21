package com.app.backend.weather.service;

import com.app.backend.weather.model.Field;
import com.app.backend.weather.repository.FieldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FieldService {
    @Autowired
    private FieldRepository fieldRepository;

    public List<Field> getAllFields() {
        return fieldRepository.findAll();
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
