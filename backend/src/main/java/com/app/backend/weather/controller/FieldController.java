

package com.app.backend.weather.controller;

import com.app.backend.weather.model.UpdatePreviewImagesRequest;
import com.app.backend.weather.model.Field;
import com.app.backend.weather.service.FieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/fields")
public class FieldController {

    @Autowired
    private FieldService fieldService;

    @PutMapping("/update-preview-images")
    public Response updatePreviewImages(@RequestParam String name, @RequestBody UpdatePreviewImagesRequest request) {
        boolean success = fieldService.updatePreviewImagesByName(
            // name,
            // request.getPreviewImageUrl1(),
            // request.getPreviewImageUrl2(),
            // request.getPreviewImageUrl3(),
            // request.getPreviewImageUrl4(),
            // request.getPreviewImageUrl5()
        );

        if (success) {
            return new Response("200", "Success!", null);
        } else {
            return new Response("400", "Update failed", null);
        }
    }

    


    @GetMapping("/name")
    public Response getLocationByName(@RequestParam String name) {
        String name1 = fieldService.getLocationByName(name);
        if (name1 != null) {
            return new Response("200", "Success!", name1);
        } else {
            return new Response("404", "Field not found", null);
        }
    }

    public static class Response {
        private String code;
        private String msg;
        private Object data;

        public Response(String code, String msg, Object data) {
            this.code = code;
            this.msg = msg;
            this.data = data;
        }

        // Getters and Setters
        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public Object getData() {
            return data;
        }      

        public void setData(Object data) {
            this.data = data;
        }
    }




}