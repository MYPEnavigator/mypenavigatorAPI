package com.example.mypenavigatorapi.courses.services;

import com.example.mypenavigatorapi.common.exceptions.BadRequestException;
import com.example.mypenavigatorapi.common.exceptions.ResourceNotFoundException;
import com.example.mypenavigatorapi.common.mapper.Mapper;
import com.example.mypenavigatorapi.courses.domain.dto.SaveCourseDto;
import com.example.mypenavigatorapi.courses.domain.entities.Course;
import com.example.mypenavigatorapi.courses.domain.repositories.CourseRepository;
import com.example.mypenavigatorapi.enrollments.domain.entities.Enrollment;
import com.example.mypenavigatorapi.enrollments.domain.repositories.EnrollmentRepository;
import com.example.mypenavigatorapi.enrollments.services.EnrollmentService;
import com.example.mypenavigatorapi.users.domain.entities.Bank;
import com.example.mypenavigatorapi.users.domain.entities.User;
import com.example.mypenavigatorapi.users.services.BankService;
import com.example.mypenavigatorapi.users.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    public List<Course> findAllByCreatorId(Long creatorId) {
        return courseRepository.findAllByUserId(creatorId);
    }

    public List<Course> findAllByUserId(Long userId) {
        List<Enrollment> enrollments = enrollmentRepository.findByUserId(userId);

        return enrollments.stream()
                .map(Enrollment::getCourse)
                .collect(Collectors.toList());
    }

    public List<Course> findAllByBankId(Long bankId) {
        return courseRepository.findAllByBankId(bankId);
    }

    public Course findById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course", "id", id));
    }

    public Course findBySlug(String slug) {
        return courseRepository.findBySlug(slug)
                .orElseThrow(() -> new ResourceNotFoundException("Course", "slug", slug));
    }

    public Course save(SaveCourseDto dto, Long userId){
        User user = userService.findById(userId);

        Bank bank = user.getBank();

        if(bank == null) {
            throw new BadRequestException("You must belong to a bank to create a course");
        }

        Course course = Mapper.map(dto, Course.class);
        course.setBank(bank);
        course.setUser(user);

        return courseRepository.save(course);
    }

    public Course update(Long id, SaveCourseDto dto){
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course", "id", id));

        Mapper.merge(dto, course);

        return courseRepository.save(course);
    }

    public ResponseEntity<?> delete (Long id){
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course", "id", id));

        courseRepository.delete(course);

        return ResponseEntity.ok().build();
    }
}
