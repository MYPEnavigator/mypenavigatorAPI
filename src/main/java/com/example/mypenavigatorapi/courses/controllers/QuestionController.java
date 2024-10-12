package com.example.mypenavigatorapi.courses.controllers;

import com.example.mypenavigatorapi.common.mapper.Mapper;
import com.example.mypenavigatorapi.courses.domain.dto.QuestionDto;
import com.example.mypenavigatorapi.courses.domain.dto.SaveQuestionDto;
import com.example.mypenavigatorapi.courses.services.QuestionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/questions")
@Tag(name = "questions", description = "questions endpoints")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @GetMapping
    @Operation(summary = "Get all test's questions")
    public List<QuestionDto> findAllByTestId(Long testId) {
        return questionService.findAllByTestId(testId)
                .stream()
                .map(question -> Mapper.map(question, QuestionDto.class))
                .toList();
    }

    @PostMapping
    @Operation(summary = "Create a new question")
    public QuestionDto save(
            @Valid @RequestBody SaveQuestionDto dto,
            @PathParam("testId") Long testId) {
        return Mapper.map(questionService.save(dto, testId), QuestionDto.class);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update question by id")
    public QuestionDto update(
            @PathVariable("id") Long id,
            @Valid @RequestBody SaveQuestionDto dto) {
        return Mapper.map(questionService.update(id, dto), QuestionDto.class);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete question by id")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return questionService.delete(id);
    }
}
