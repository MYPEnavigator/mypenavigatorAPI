package com.example.mypenavigatorapi.enrollments.controllers;

import com.example.mypenavigatorapi.common.mapper.Mapper;
import com.example.mypenavigatorapi.enrollments.domain.dto.SaveUserModuleTrackingDto;
import com.example.mypenavigatorapi.enrollments.domain.dto.UserModuleTrackingDto;
import com.example.mypenavigatorapi.enrollments.services.UserModuleTrackingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user-module-tracking")
@Tag(name = "user-module-tracking", description = "endpoints to manage user's module progress")
public class UserModuleTrackingController {
    @Autowired
    private UserModuleTrackingService userModuleTrackingService;

    @GetMapping("")
    @Operation(summary = "Get all modules tracking by course id and user id")
    public List<UserModuleTrackingDto> findAllByUserIdAndCourseId(
            @RequestParam("userId") Long userId,
            @RequestParam("courseId") Long courseId
    ) {
        return userModuleTrackingService.findAllByUserIdAndCourseId(userId, courseId).stream()
                .map(userModuleTracking -> Mapper.map(userModuleTracking, UserModuleTrackingDto.class))
                .toList();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get module tracking by id")
    public UserModuleTrackingDto findById(@PathVariable Long id) {
        return Mapper.map(userModuleTrackingService.findById(id), UserModuleTrackingDto.class);
    }

    @PostMapping
    @Operation(summary = "Create a new module tracking")
    public UserModuleTrackingDto save(
            @Valid @RequestBody SaveUserModuleTrackingDto dto,
            @RequestParam("userId") Long userId,
            @RequestParam("courseId") Long courseId,
            @RequestParam("moduleId") Long moduleId
    ) {
        return Mapper.map(
                userModuleTrackingService.save(dto, userId, courseId, moduleId),
                UserModuleTrackingDto.class);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update module tracking by id")
    public UserModuleTrackingDto update(
            @PathVariable Long id,
            @Valid @RequestBody SaveUserModuleTrackingDto dto
    ) {
        return Mapper.map(userModuleTrackingService.update(id, dto), UserModuleTrackingDto.class);
    }

    @PatchMapping("/{id}/complete")
    @Operation(summary = "Complete module tracking by id")
    public UserModuleTrackingDto complete(@PathVariable Long id) {
        return Mapper.map(userModuleTrackingService.completeModule(id), UserModuleTrackingDto.class);
    }

    @PatchMapping("/{id}/start")
    @Operation(summary = "Start module tracking by id")
    public UserModuleTrackingDto start(@PathVariable Long id) {
        return Mapper.map(userModuleTrackingService.startModule(id), UserModuleTrackingDto.class);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete module tracking by id")
    public void delete(@PathVariable Long id) {
        userModuleTrackingService.delete(id);
    }

}
