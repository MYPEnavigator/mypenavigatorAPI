package com.example.mypenavigatorapi.users.domain.dto;

import com.example.mypenavigatorapi.common.domain.dto.AuditDto;
import com.example.mypenavigatorapi.users.domain.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto extends AuditDto {
    private Long id;
    private String name;
    private String lastName;
    private String dni;
    private Role role;
    private String profilePicture;
    private boolean isGoogleAccount = false;
    private String email;
    private MypeDto mype;
    private BankDto bank;
}
