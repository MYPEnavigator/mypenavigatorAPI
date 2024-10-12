package com.example.mypenavigatorapi.courses.controllers;

import com.example.mypenavigatorapi.common.mapper.Mapper;
import com.example.mypenavigatorapi.courses.domain.dto.SaveTestDto;
import com.example.mypenavigatorapi.courses.domain.dto.TestDto;
import com.example.mypenavigatorapi.courses.services.TestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/tests")
@Tag(name = "tests", description = "tests endpoints")
public class TestController {
    @Autowired
    private TestService testService;

    @GetMapping
    @Operation(summary = "Get all tests")
    public List<TestDto> findAll() {
        return testService.findAll().stream()
                .map(test -> Mapper.map(test, TestDto.class))
                .toList();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get test by id")
    public TestDto findById(
            @PathVariable("id") Long id) {
        return Mapper.map(testService.findById(id), TestDto.class);
    }

    @PostMapping
    @Operation(summary = "Create a new test")
    public TestDto save(
            @Valid @RequestBody SaveTestDto dto,
            @RequestParam(value = "moduleId") Long moduleId) {
        return Mapper.map(testService.save(dto, moduleId), TestDto.class);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update test by id")
    public TestDto update(
            @PathVariable("id") Long id,
            @Valid @RequestBody SaveTestDto dto) {
        return Mapper.map(testService.update(id, dto), TestDto.class);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete test by id")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return testService.delete(id);
    }
}
