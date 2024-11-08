package com.example.mypenavigatorapi.rewards.controllers;

import com.example.mypenavigatorapi.common.mapper.Mapper;
import com.example.mypenavigatorapi.rewards.domain.dto.UserBankPointsDto;
import com.example.mypenavigatorapi.rewards.services.UserBankPointsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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

    @GetMapping
    @Operation(summary = "Get user's points in a bank")
    public UserBankPointsDto findByUserIdAndBankId(
            @RequestParam("userId") Long userId,
            @RequestParam("bankId") Long bankId) {
        return Mapper.map(userBankPointsService.findByUserIdAndBankId(userId, bankId), UserBankPointsDto.class);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete user's points in a bank")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return userBankPointsService.delete(id);
    }
}
