package com.example.mypenavigatorapi.courses.controllers;

import com.example.mypenavigatorapi.common.mapper.Mapper;
import com.example.mypenavigatorapi.courses.domain.dto.ModuleDto;
import com.example.mypenavigatorapi.courses.domain.dto.SaveModuleDto;
import com.example.mypenavigatorapi.courses.services.ModuleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/modules")
@Tag(name = "modules", description = "modules endpoints")
public class ModuleController {
    @Autowired
    private ModuleService moduleService;

    @GetMapping
    @Operation(summary = "Get all modules on a course")
    public List<ModuleDto> findAllByCourseId(
            @RequestParam(value = "courseId") Long courseId){
        return moduleService.findAllByCourseId(courseId)
                .stream()
                .map(module -> Mapper.map(module, ModuleDto.class))
                .toList();
    }

    @PostMapping
    @Operation(summary = "Create a new module")
    public ModuleDto save(
            @Valid @RequestBody SaveModuleDto dto,
            @RequestParam(value = "courseId") Long courseId){
        return Mapper.map(moduleService.save(dto, courseId), ModuleDto.class);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update module by id")
    public ModuleDto update(
            @PathVariable("id") Long id,
            @Valid @RequestBody SaveModuleDto dto){
        return Mapper.map(moduleService.update(id, dto), ModuleDto.class);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete module by id")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        return moduleService.delete(id);
    }
}
