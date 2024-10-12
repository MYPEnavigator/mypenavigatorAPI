package com.example.mypenavigatorapi.users.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private Integer experiencePoints;
    private Integer level;
    private MypeDto mype;
    private BankDto bank;
}
