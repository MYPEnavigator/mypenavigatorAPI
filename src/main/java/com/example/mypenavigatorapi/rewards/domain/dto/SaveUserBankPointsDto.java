package com.example.mypenavigatorapi.rewards.domain.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveUserBankPointsDto {
    @NotNull
    @Positive
    private Integer points;
}
