package com.example.mypenavigatorapi.enrollments.controllers;

import com.example.mypenavigatorapi.common.mapper.Mapper;
import com.example.mypenavigatorapi.enrollments.domain.dto.CourseCompletionHistoryDto;
import com.example.mypenavigatorapi.enrollments.domain.dto.SaveCourseCompletionHistoryDto;
import com.example.mypenavigatorapi.enrollments.domain.dto.UserCourseCompletionHistoryDto;
import com.example.mypenavigatorapi.enrollments.services.CourseCompletionHistoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/course-completion-histories")
@Tag(name="course-completion-history", description = "endpoints to manage and register when user completes a course")
public class CourseCompletionHistoryController {
    @Autowired
    private CourseCompletionHistoryService courseCompletionHistoryService;

    @GetMapping("/user/{userId}")
    @Operation(summary = "Get all courses history that were completed by user id")
    public List<CourseCompletionHistoryDto> findAllByUserId(@PathVariable Long userId){
        return courseCompletionHistoryService.findAllByUserId(userId).stream()
                .map(courseCompletionHistory -> Mapper.map(courseCompletionHistory, CourseCompletionHistoryDto.class))
                .toList();
    }

    @GetMapping("/course/{courseId}")
    @Operation(summary = "Get all users history that complete a course")
    public List<UserCourseCompletionHistoryDto> findAllByCourseId(@PathVariable Long courseId){
        return courseCompletionHistoryService.findAllByCourseId(courseId).stream()
                .map(courseCompletionHistory -> Mapper.map(courseCompletionHistory, UserCourseCompletionHistoryDto.class))
                .toList();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get course completion history by id")
    public CourseCompletionHistoryDto findById(@PathVariable Long id) {
        return Mapper.map(courseCompletionHistoryService.findById(id), CourseCompletionHistoryDto.class);
    }

    @PostMapping
    @Operation(summary = "Create a new course completion history")
    public CourseCompletionHistoryDto save(@Valid @RequestBody SaveCourseCompletionHistoryDto dto){
        return Mapper.map(courseCompletionHistoryService.save(dto), CourseCompletionHistoryDto.class);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete course completion history by id")
    public ResponseEntity<?> delete(@PathVariable Long id){
        return courseCompletionHistoryService.delete(id);
    }
}
