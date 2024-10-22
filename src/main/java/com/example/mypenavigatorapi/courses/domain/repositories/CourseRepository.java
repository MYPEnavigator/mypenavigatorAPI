package com.example.mypenavigatorapi.courses.domain.repositories;

import com.example.mypenavigatorapi.courses.domain.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findAllByUserId(Long userId);
    List<Course> findAllByBankId(Long bankId);

    Optional<Course> findBySlug(String slug);
}
