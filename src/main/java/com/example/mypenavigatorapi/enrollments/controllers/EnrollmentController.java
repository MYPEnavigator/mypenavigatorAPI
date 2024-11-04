package com.example.mypenavigatorapi.enrollments.controllers;

import com.example.mypenavigatorapi.common.mapper.Mapper;
import com.example.mypenavigatorapi.enrollments.domain.dto.EnrollmentDto;
import com.example.mypenavigatorapi.enrollments.domain.dto.EnrollmentWithUserDto;
import com.example.mypenavigatorapi.enrollments.domain.dto.SaveEnrollmentDto;
import com.example.mypenavigatorapi.enrollments.services.EnrollmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/enrollments")
@Tag(name = "enrollments", description = "endpoints to register user enrollments in courses")
public class EnrollmentController {
    @Autowired
    private EnrollmentService enrollmentService;

    @GetMapping("/user/{userId}")
    @Operation(summary = "Get all course where the user is enroll")
    public List<EnrollmentDto> findAllByUserId(@PathVariable("userId") Long userId) {
        return enrollmentService.findAllByUserId(userId)
                .stream()
                .map(enrollment -> Mapper.map(enrollment, EnrollmentDto.class))
                .toList();
    }

    @GetMapping("/course/{courseId}")
    @Operation(summary = "Get all users that are enroll on the course")
    public List<EnrollmentWithUserDto> findAllByCourseId(@PathVariable("courseId") Long courseId) {
        return enrollmentService.findAllByCourseId(courseId)
                .stream()
                .map(enrollment -> Mapper.map(enrollment, EnrollmentWithUserDto.class))
                .toList();
    }

    @GetMapping("/user/{userId}/course/{courseId}")
    @Operation(summary = "Get user enrollment in a course")
    public EnrollmentDto findByUserAndCourseId(@PathVariable("userId") Long userId,
                                              @PathVariable("courseId") Long courseId) {
        return Mapper.map(enrollmentService.findByUserAndCourseId(userId, courseId), EnrollmentDto.class);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get enrollment by id")
    public EnrollmentDto findById(@PathVariable("id") Long id) {
        return Mapper.map(enrollmentService.findById(id), EnrollmentDto.class);
    }

    @PostMapping
    @Operation(summary = "Enroll a user in a course")
    public EnrollmentDto enrollUser(
            @Valid @RequestBody SaveEnrollmentDto dto,
            @RequestParam("userId") Long userId,
            @RequestParam("courseId") Long courseId
    ){
        return Mapper.map(enrollmentService.enrollUser(dto, userId, courseId), EnrollmentDto.class);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update enrollment by id")
    public EnrollmentDto update(@PathVariable("id") Long id,
                                @Valid @RequestBody SaveEnrollmentDto dto){
        return Mapper.map(enrollmentService.update(id, dto), EnrollmentDto.class);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete enrollment by id")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        return enrollmentService.delete(id);
    }

}
