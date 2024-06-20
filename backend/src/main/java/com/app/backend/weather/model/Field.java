package com.app.backend.weather.model;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "field")
public class Field {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "rating")
    private int rating;

    @Column(name = "recommendation_type")
    private String recommendationType;

    @Column(name = "location")
    private String location;

    @Column(name = "difficulty")
    private String difficulty;

    @Column(name = "distance")
    private double distance;

    @Column(name = "estimated_time")
    private String estimatedTime;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "preview_image_url1")
    private String previewImageUrl1;

    @Column(name = "preview_image_url2")
    private String previewImageUrl2;

    @Column(name = "preview_image_url3")
    private String previewImageUrl3;

    @Column(name = "preview_image_url4")
    private String previewImageUrl4;

    @Column(name = "preview_image_url5")
    private String previewImageUrl5;

    @Column(name = "description", length = 500)
    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "America/Vancouver")
    @Column(name = "create_time", updatable = false)
    private Date createTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "America/Vancouver")
    @Column(name = "update_time")
    private Date updateTime;

    @PrePersist
    protected void onCreate() {
        createTime = new Date();
        updateTime = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updateTime = new Date();
    }
}
