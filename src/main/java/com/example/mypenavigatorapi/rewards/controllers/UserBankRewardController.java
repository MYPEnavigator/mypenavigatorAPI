package com.example.mypenavigatorapi.rewards.controllers;

import com.example.mypenavigatorapi.common.mapper.Mapper;
import com.example.mypenavigatorapi.rewards.domain.dto.UserBankRewardDto;
import com.example.mypenavigatorapi.rewards.services.UserBankRewardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/bank-rewards")
@Tag(name = "user-bank-rewards", description = "endpoints to manage rewards claimed by users")
public class UserBankRewardController {
    @Autowired
    private UserBankRewardService userBankRewardService;

    @GetMapping("/user/{userId}")
    @Operation(summary = "Get all rewards claimed by user")
    public List<UserBankRewardDto> findAllByUserId(@PathVariable("userId") Long userId) {
        return userBankRewardService.findAllByUserId(userId)
                .stream()
                .map(userBankReward -> Mapper.map(userBankReward, UserBankRewardDto.class))
                .toList();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get reward claimed by id")
    public UserBankRewardDto findById(@PathVariable("id") Long id) {
        return Mapper.map(userBankRewardService.findById(id), UserBankRewardDto.class);
    }

    @PostMapping("/{userId}/claim-reward/{rewardId}")
    @Operation(summary = "Claim a reward by user")
    public UserBankRewardDto claimReward(
            @PathVariable("userId") Long userId,
            @PathVariable("rewardId") Long rewardId
            ) {
        return Mapper.map(userBankRewardService.claimReward(userId, rewardId), UserBankRewardDto.class);
    }
}
