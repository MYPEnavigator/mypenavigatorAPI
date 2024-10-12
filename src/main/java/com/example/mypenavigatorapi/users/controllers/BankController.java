package com.example.mypenavigatorapi.users.controllers;

import com.example.mypenavigatorapi.common.mapper.Mapper;
import com.example.mypenavigatorapi.users.domain.dto.BankDto;
import com.example.mypenavigatorapi.users.domain.dto.SaveBankDto;
import com.example.mypenavigatorapi.users.services.BankService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/banks")
@Tag(name = "banks", description = "banks endpoints")
public class BankController {
    @Autowired
    private BankService bankService;

    @GetMapping
    @Operation(summary = "Get all banks")
    public List<BankDto> findAll() {
        return bankService.findAll().stream()
                .map(bank -> Mapper.map(bank, BankDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get bank by id")
    public BankDto findById(@PathVariable("id") Long id) {
        return Mapper.map(bankService.findById(id), BankDto.class);
    }

    @PostMapping
    @Operation(summary = "Create a new bank")
    public BankDto save(@Valid @RequestBody SaveBankDto dto) {
        return Mapper.map(bankService.save(dto), BankDto.class);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update bank by id")
    public BankDto update(
            @PathVariable("id") Long id,
            @Valid @RequestBody SaveBankDto dto
    ) {
        return Mapper.map(bankService.update(id, dto), BankDto.class);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete bank by id")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return bankService.delete(id);
    }
}
