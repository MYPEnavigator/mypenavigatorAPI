package com.example.mypenavigatorapi.rewards.domain.dto;

import com.example.mypenavigatorapi.users.domain.dto.BankDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserBankPointsDto {
    private Long id;
    private Integer points;
    private BankDto bank;
}
