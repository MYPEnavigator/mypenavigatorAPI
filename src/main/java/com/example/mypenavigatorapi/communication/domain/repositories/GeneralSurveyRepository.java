package com.example.mypenavigatorapi.communication.domain.repositories;

import com.example.mypenavigatorapi.communication.domain.entities.GeneralSurvey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GeneralSurveyRepository extends JpaRepository<GeneralSurvey, Long> {
    Optional<GeneralSurvey> findByUserId(Long userId);
}
