package com.example.mypenavigatorapi.enrollments.domain.repositories;

import com.example.mypenavigatorapi.enrollments.domain.entities.UserModuleTracking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserModuleTrackingRepository  extends JpaRepository<UserModuleTracking, Long> {

    @Query("SELECT u FROM UserModuleTracking u WHERE u.user.id = :userId AND u.course.id = :courseId ORDER BY u.module.order")
    List<UserModuleTracking> findAllByUserIdAndCourseId(Long userId, Long courseId);
}
