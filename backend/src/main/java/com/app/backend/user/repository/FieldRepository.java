package com.app.backend.user.repository;

import com.app.backend.user.model.weatherhome.Field;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FieldRepository extends JpaRepository<Field, Long> {
    List<Field> findTop5ByRecommendationTypeOrderByRatingDesc(String recommendationType);
}
