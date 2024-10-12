package com.example.mypenavigatorapi.rewards.domain.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveUserBankRewardDto {
    @NotNull
    @Positive
    private Long bankRewardId;

    @NotNull
    @Positive
    private Long userId;
}
