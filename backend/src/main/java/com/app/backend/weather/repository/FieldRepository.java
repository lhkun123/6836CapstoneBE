package com.app.backend.weather.repository;

import com.app.backend.weather.model.Field;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FieldRepository extends JpaRepository<Field, Long> {
    List<Field> findTop5ByRecommendationTypeOrderByRatingDesc(String recommendationType);

    Field findByName(String name);

    Field findByLocation(String location);
}
