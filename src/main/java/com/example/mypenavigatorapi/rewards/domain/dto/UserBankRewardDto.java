package com.example.mypenavigatorapi.rewards.domain.dto;

import com.example.mypenavigatorapi.users.domain.dto.UserDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserBankRewardDto {
    private Long id;
    private UserDto user;
    private BankRewardDto bankReward;
}
