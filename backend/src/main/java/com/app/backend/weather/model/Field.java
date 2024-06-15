package com.app.backend.weather.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;

@Entity
public class Field {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "rating")
    private int rating;

    @Column(name = "recommendation_type")
    private String recommendationType;  // 将字段名修改为 recommendation_type

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


    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getRecommendationType() {
        return recommendationType;
    }

    public void setRecommendationType(String recommendationType) {
        this.recommendationType = recommendationType;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(String estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPreviewImageUrl1() {
        return previewImageUrl1;
    }

    public void setPreviewImageUrl1(String previewImageUrl1) {
        this.previewImageUrl1 = previewImageUrl1;
    }

    public String getPreviewImageUrl2() {
        return previewImageUrl2;
    }

    public void setPreviewImageUrl2(String previewImageUrl2) {
        this.previewImageUrl2 = previewImageUrl2;
    }

    public String getPreviewImageUrl3() {
        return previewImageUrl3;
    }

    public void setPreviewImageUrl3(String previewImageUrl3) {
        this.previewImageUrl3 = previewImageUrl3;
    }

    public String getPreviewImageUrl4() {
        return previewImageUrl4;
    }

    public void setPreviewImageUrl4(String previewImageUrl4) {
        this.previewImageUrl4 = previewImageUrl4;
    }

    public String getPreviewImageUrl5() {
        return previewImageUrl5;
    }

    public void setPreviewImageUrl5(String previewImageUrl5) {
        this.previewImageUrl5 = previewImageUrl5;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
