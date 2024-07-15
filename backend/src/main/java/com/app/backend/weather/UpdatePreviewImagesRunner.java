package com.app.backend.weather;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.io.*;
import java.util.*;

import com.app.backend.weather.service.FieldService;

@Component
public class UpdatePreviewImagesRunner implements CommandLineRunner {

    @Autowired
    private FieldService fieldService;

    

    // @Override
    // public void run(String... args) throws Exception {
    //     String newUrl = "url:example.com";
    //     fieldService.updatePreviewImagesByName();
    //     System.out.println("Preview images updated successfully.");
    // }

    @Override
    public void run(String... args) throws Exception {
        String newUrl = "url:example.com";
        fieldService.updatePreviewImagesByName();
        System.out.println("by name, Preview  images updated successfully.");
    }
        
}