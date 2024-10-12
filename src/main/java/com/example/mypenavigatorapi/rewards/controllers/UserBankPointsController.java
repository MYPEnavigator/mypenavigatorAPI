package com.example.mypenavigatorapi.rewards.controllers;

import com.example.mypenavigatorapi.common.mapper.Mapper;
import com.example.mypenavigatorapi.rewards.domain.dto.SaveUserBankPointsDto;
import com.example.mypenavigatorapi.rewards.domain.dto.UserBankPointsDto;
import com.example.mypenavigatorapi.rewards.services.UserBankPointsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bank-points")
@Tag(name = "user-bank-points", description = "endpoints to manage user points with any bank")
public class UserBankPointsController {
    @Autowired
    private UserBankPointsService userBankPointsService;

    @GetMapping("/users/{userId}")
    @Operation(summary = "Get all user's points in banks")
    public List<UserBankPointsDto> findAllByUserId(@PathVariable("userId") Long userId) {
        return userBankPointsService.findAllByUserId(userId)
                .stream()
                .map(userBankPoints -> Mapper.map(userBankPoints, UserBankPointsDto.class))
                .toList();
    }

    @PostMapping
    @Operation(summary = "Create a new user's points in a bank")
    public UserBankPointsDto save(
            @RequestParam(value = "userId", defaultValue = "0") Long userId,
            @RequestParam(value = "bankId", defaultValue = "0") Long bankId,
            @Valid @RequestBody SaveUserBankPointsDto dto
            ) {
        return Mapper.map(userBankPointsService.save(userId, bankId, dto), UserBankPointsDto.class);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update user's points in a bank by id")
    public UserBankPointsDto update(
            @PathVariable("id") Long id,
            @Valid @RequestBody SaveUserBankPointsDto dto
            ) {
        return Mapper.map(userBankPointsService.update(id, dto), UserBankPointsDto.class);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete user's points in a bank")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return userBankPointsService.delete(id);
    }
}
