package com.example.mypenavigatorapi.communication.controllers;

import com.example.mypenavigatorapi.common.mapper.Mapper;
import com.example.mypenavigatorapi.communication.domain.dto.GeneralSurveyDto;
import com.example.mypenavigatorapi.communication.domain.dto.SaveGeneralSurveyDto;
import com.example.mypenavigatorapi.communication.services.GeneralSurveyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/general-survey")
@Tag(name = "General Survey", description = "endpoints to manage survey")
public class GeneralSurveyController {
    @Autowired
    private GeneralSurveyService generalSurveyService;

    @GetMapping
    @Operation(summary = "Get all general surveys")
    public List<GeneralSurveyDto> findAll() {
        return generalSurveyService.findAll().stream()
                .map(generalSurvey -> Mapper.map(generalSurvey, GeneralSurveyDto.class))
                .toList();
    }

    @GetMapping("/{userId}")
    @Operation(summary = "Get general survey by user id")
    public GeneralSurveyDto findByUserId(@PathVariable("userId") Long userId) {
        return Mapper.map(generalSurveyService.findByUserId(userId), GeneralSurveyDto.class);
    }

    @PostMapping
    @Operation(summary = "Create a new general survey")
    public GeneralSurveyDto create(
            @RequestBody SaveGeneralSurveyDto dto,
            @RequestParam("userId") Long userId
    ) {
        return Mapper.map(generalSurveyService.create(dto, userId), GeneralSurveyDto.class);
    }
}
