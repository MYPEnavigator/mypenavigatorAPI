package com.example.mypenavigatorapi.users.domain.dto;

import com.example.mypenavigatorapi.users.domain.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private Long id;
    private String name;
    private String lastName;
    private String dni;
    private Role role;
    private String profilePicture;
    private String email;
    private MypeDto mype;
    private BankDto bank;
}
