package com.example.mypenavigatorapi.rewards.domain.dto;

import com.example.mypenavigatorapi.common.domain.dto.AuditDto;
import com.example.mypenavigatorapi.users.domain.dto.BankDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BankRewardDto extends AuditDto {
    private Long id;
    private String name;
    private String description;
    private Integer requiredPoints;
    private String imageUrl;
    private BankDto bank;
}
