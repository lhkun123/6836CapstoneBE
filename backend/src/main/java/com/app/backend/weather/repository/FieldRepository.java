package com.app.backend.weather.repository;

import com.app.backend.weather.model.Field;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FieldRepository extends JpaRepository<Field, Long> {

    @Query(value = "SELECT * FROM field WHERE recommendation_type = ?1 ORDER BY RAND() LIMIT 5", nativeQuery = true)
    List<Field> findTop5ByRecommendationTypeRandomly(String recommendationType);

    @Query(value = "SELECT * FROM field WHERE recommendation_type IN (?1, ?2) ORDER BY RAND() LIMIT 5", nativeQuery = true)
    List<Field> findTop5ByRecommendationTypesRandomly(String recommendationType1, String recommendationType2);

    @Query(value = "SELECT * FROM field ORDER BY RAND() LIMIT 5", nativeQuery = true)
    List<Field> findTop5Randomly();

    @Query(value = "SELECT * FROM field WHERE name = ?1", nativeQuery = true)
    Field findFieldByName(String name);


}
