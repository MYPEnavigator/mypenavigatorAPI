package com.example.mypenavigatorapi.enrollments.services;

import com.example.mypenavigatorapi.common.exceptions.BadRequestException;
import com.example.mypenavigatorapi.common.exceptions.ResourceNotFoundException;
import com.example.mypenavigatorapi.common.mapper.Mapper;
import com.example.mypenavigatorapi.courses.domain.entities.Course;
import com.example.mypenavigatorapi.courses.services.CourseService;
import com.example.mypenavigatorapi.enrollments.domain.dto.SaveEnrollmentDto;
import com.example.mypenavigatorapi.enrollments.domain.entities.Enrollment;
import com.example.mypenavigatorapi.enrollments.domain.repositories.EnrollmentRepository;
import com.example.mypenavigatorapi.users.domain.entities.User;
import com.example.mypenavigatorapi.users.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollmentService {
    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private CourseService courseService;

    @Autowired
    private UserService userService;

    public List<Enrollment> findAllByUserId(Long userId) {
        return enrollmentRepository.findByUserId(userId);
    }

    public List<Enrollment> findAllByCourseId(Long courseId) {
        return enrollmentRepository.findByCourseId(courseId);
    }

    public Enrollment findById(Long id) {
        return enrollmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Enrollment", "id", id));
    }

    public Enrollment enrollUser(SaveEnrollmentDto dto){
        enrollmentRepository.findByUserIdAndCourseId(dto.getUserId(), dto.getCourseId())
                .ifPresent(enrollment -> {
                    throw new BadRequestException("User is already enrolled in this course");
                });

        User user = userService.findById(dto.getUserId());
        Course course = courseService.findById(dto.getCourseId());

        Enrollment enrollment = Mapper.map(dto, Enrollment.class);

        enrollment.setUser(user);
        enrollment.setCourse(course);

        return enrollmentRepository.save(enrollment);
    }

    public Enrollment update(Long id, SaveEnrollmentDto dto){
        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Enrollment", "id", id));

        Mapper.merge(dto, enrollment);
        return enrollmentRepository.save(enrollment);
    }

    public ResponseEntity<?> delete(Long id){
        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Enrollment", "id", id));

        enrollmentRepository.delete(enrollment);

        return ResponseEntity.ok().build();
    }
}
