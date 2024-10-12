package com.example.mypenavigatorapi.enrollments.domain.repositories;

import com.example.mypenavigatorapi.enrollments.domain.entities.CourseCompletionHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseCompletionHistoryRepository extends JpaRepository<CourseCompletionHistory, Long> {
    List<CourseCompletionHistory> findAllByUserId(Long userId);
    List<CourseCompletionHistory> findAllByCourseId(Long courseId);
}
