package com.example.mypenavigatorapi.courses.domain.repositories;

import com.example.mypenavigatorapi.courses.domain.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findAllByTestId(Long testId);
}
