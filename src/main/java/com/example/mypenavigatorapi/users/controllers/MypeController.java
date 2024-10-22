package com.example.mypenavigatorapi.users.controllers;

import com.example.mypenavigatorapi.common.mapper.Mapper;
import com.example.mypenavigatorapi.users.domain.dto.MypeDto;
import com.example.mypenavigatorapi.users.domain.dto.SaveMypeDto;
import com.example.mypenavigatorapi.users.services.MypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/mypes")
@Tag(name = "mypes", description = "mypes endpoints")
public class MypeController {
    @Autowired
    private MypeService mypeService;

    @GetMapping
    @Operation(summary = "Get all mypes")
    public List<MypeDto> findAll() {
        return mypeService.findAll().stream()
                .map(mype -> Mapper.map(mype, MypeDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get mype by id")
    public MypeDto findById(@PathVariable("id") Long id) {
        return Mapper.map(mypeService.findById(id), MypeDto.class);
    }

    @GetMapping("/ruc/{ruc}")
    @Operation(summary = "Get mype by ruc")
    public MypeDto findByRuc(@PathVariable("ruc") String ruc) {
        return Mapper.map(mypeService.findByRuc(ruc), MypeDto.class);
    }

    @PostMapping
    @Operation(summary = "Create a new mype")
    public MypeDto save(@Valid @RequestBody SaveMypeDto dto) {
        return Mapper.map(mypeService.save(dto), MypeDto.class);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update mype by id")
    public MypeDto update(
            @PathVariable("id") Long id,
            @Valid @RequestBody SaveMypeDto dto
    ) {
        return Mapper.map(mypeService.update(id, dto), MypeDto.class);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete mype by id")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return mypeService.delete(id);
    }
}
