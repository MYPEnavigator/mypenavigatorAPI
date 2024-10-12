package com.example.mypenavigatorapi.enrollments.controllers;

import com.example.mypenavigatorapi.common.mapper.Mapper;
import com.example.mypenavigatorapi.enrollments.domain.dto.SaveSurveyDto;
import com.example.mypenavigatorapi.enrollments.domain.dto.SurveyDto;
import com.example.mypenavigatorapi.enrollments.services.SurveyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/surveys")
@Tag(name="surveys", description = "endpoints to manage user surveys when finish a course")
public class SurveyController {
    @Autowired
    private SurveyService surveyService;


    @GetMapping("/course/{courseId}")
    @Operation(summary = "Get all surveys by course id")
    public List<SurveyDto> findAllByCourseId(@PathVariable Long courseId){
        return surveyService.findAllByCourseId(courseId).stream()
                .map(survey -> Mapper.map(survey, SurveyDto.class))
                .toList();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get survey by id")
    public SurveyDto findById(@PathVariable Long id) {
        return Mapper.map(surveyService.findById(id), SurveyDto.class);
    }

    @PostMapping
    @Operation(summary = "Create a new survey")
    public SurveyDto save(@Valid @RequestBody SaveSurveyDto dto){
        return Mapper.map(surveyService.save(dto), SurveyDto.class);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete survey by id")
    public ResponseEntity<?> delete(@PathVariable Long id){
        return surveyService.delete(id);
    }
}
