package com.example.mypenavigatorapi.rewards.controllers;

import com.example.mypenavigatorapi.common.mapper.Mapper;
import com.example.mypenavigatorapi.rewards.domain.dto.BankRewardDto;
import com.example.mypenavigatorapi.rewards.domain.dto.SaveBankRewardDto;
import com.example.mypenavigatorapi.rewards.services.BankRewardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rewards")
@Tag(name = "bank-rewards", description = "endpoints to manage bank rewards")
public class BankRewardController {
    @Autowired
    private BankRewardService bankRewardService;

    @GetMapping("/banks/{bankId}")
    @Operation(summary = "Get all rewards by bank id")
    public List<BankRewardDto> findAllByBankId(@PathVariable("bankId") Long bankId) {
        return bankRewardService.findAllByBankId(bankId)
                .stream()
                .map(bankReward -> Mapper.map(bankReward, BankRewardDto.class))
                .toList();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get reward by id")
    public BankRewardDto findById(@PathVariable("id") Long id) {
        return Mapper.map(bankRewardService.findById(id), BankRewardDto.class);
    }

    @PostMapping
    @Operation(summary = "Create a new reward")
    public BankRewardDto save(@Valid @RequestBody SaveBankRewardDto dto,
                              @RequestParam("bankId") Long bankId) {
        return Mapper.map(bankRewardService.save(dto, bankId), BankRewardDto.class);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update reward by id")
    public BankRewardDto update(@PathVariable("id") Long id, @Valid @RequestBody SaveBankRewardDto dto) {
        return Mapper.map(bankRewardService.update(id, dto), BankRewardDto.class);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete reward by id")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return bankRewardService.delete(id);
    }
}
