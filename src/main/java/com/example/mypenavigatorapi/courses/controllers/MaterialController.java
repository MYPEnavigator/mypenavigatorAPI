package com.example.mypenavigatorapi.courses.controllers;

import com.example.mypenavigatorapi.common.mapper.Mapper;
import com.example.mypenavigatorapi.courses.domain.dto.MaterialDto;
import com.example.mypenavigatorapi.courses.domain.dto.SaveMaterialDto;
import com.example.mypenavigatorapi.courses.services.MaterialService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/materials")
@Tag(name = "materials", description = "materials endpoints")
public class MaterialController {
    @Autowired
    private MaterialService materialService;

    @GetMapping
    @Operation(summary = "Get all materials on a module")
    public List<MaterialDto> findAllByModuleId(
            @RequestParam(value = "moduleId") Long moduleId){
        return materialService.findAllByModuleId(moduleId)
                .stream()
                .map(material -> Mapper.map(material, MaterialDto.class))
                .toList();
    }

    @PostMapping
    @Operation(summary = "Create a new material")
    public MaterialDto save(
            @Valid @RequestBody SaveMaterialDto dto,
            @RequestParam(value = "moduleId") Long moduleId){
        return Mapper.map(materialService.save(dto, moduleId), MaterialDto.class);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update material by id")
    public MaterialDto update(
            @PathVariable("id") Long id,
            @Valid @RequestBody SaveMaterialDto dto){
        return Mapper.map(materialService.update(id, dto), MaterialDto.class);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete material by id")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        return materialService.delete(id);
    }
}
