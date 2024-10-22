package com.example.mypenavigatorapi.courses.controllers;

import com.example.mypenavigatorapi.common.mapper.Mapper;
import com.example.mypenavigatorapi.courses.domain.dto.CourseDto;
import com.example.mypenavigatorapi.courses.domain.dto.SaveCourseDto;
import com.example.mypenavigatorapi.courses.services.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/courses")
@Tag(name = "courses", description = "courses endpoints")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @GetMapping
    @Operation(summary = "Get all courses")
    public List<CourseDto> findAll() {
        return courseService.findAll().stream()
                .map(course -> Mapper.map(course, CourseDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Get all courses by user id")
    public List<CourseDto> findAllByUserId(@PathVariable("userId") Long userId) {
        return courseService.findAllByUserId(userId).stream()
                .map(course -> Mapper.map(course, CourseDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/bank/{bankId}")
    @Operation(summary = "Get all courses by bank id")
    public List<CourseDto> findAllByBankId(@PathVariable("bankId") Long bankId) {
        return courseService.findAllByBankId(bankId).stream()
                .map(course -> Mapper.map(course, CourseDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get course by id")
    public CourseDto findById(@PathVariable("id") Long id) {
        return Mapper.map(courseService.findById(id), CourseDto.class);
    }

    @PostMapping
    @Operation(summary = "Create a new course")
    public CourseDto save(@Valid @RequestBody SaveCourseDto dto,
                          @RequestParam(value = "userId", defaultValue = "0") Long userId) {
        return Mapper.map(courseService.save(dto, userId), CourseDto.class);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update course by id")
    public CourseDto update(@PathVariable("id") Long id,
                            @Valid @RequestBody SaveCourseDto dto) {
        return Mapper.map(courseService.update(id, dto), CourseDto.class);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete course by id")
    public ResponseEntity<?> delete(
            @PathVariable("id") Long id) {
        return courseService.delete(id);
    }

}
