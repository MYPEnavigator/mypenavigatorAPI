package com.example.mypenavigatorapi.enrollments.domain.repositories;

import com.example.mypenavigatorapi.enrollments.domain.entities.Survey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SurveyRepository extends JpaRepository<Survey, Long> {
    List<Survey> findAllByCourseId(Long courseId);
}
