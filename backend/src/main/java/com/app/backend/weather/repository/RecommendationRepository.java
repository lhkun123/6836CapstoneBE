package com.app.backend.weather.repository;

import com.app.backend.weather.model.Recommendation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecommendationRepository extends JpaRepository<Recommendation, Long> {

    @Query(value = "SELECT * FROM recommendation WHERE type = ?1 ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Optional<Recommendation> findTopByTypeRandomly(String type);
}
