package com.example.mypenavigatorapi.rewards.domain.dto;

import com.example.mypenavigatorapi.users.domain.dto.UserDto;
import com.example.mypenavigatorapi.users.domain.dto.UserInfoDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserBankRewardDto {
    private Long id;
    private Date earnedAt;
    private UserInfoDto user;
    private BankRewardDto bankReward;
}
