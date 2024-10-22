package com.example.mypenavigatorapi.enrollments.domain.repositories;

import com.example.mypenavigatorapi.enrollments.domain.entities.UserModuleTracking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserModuleTrackingRepository  extends JpaRepository<UserModuleTracking, Long> {
    List<UserModuleTracking> findAllByUserIdAndCourseId(Long userId, Long courseId);
}
