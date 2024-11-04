package com.example.mypenavigatorapi.users.domain.dto;

import com.example.mypenavigatorapi.common.domain.dto.AuditDto;
import com.example.mypenavigatorapi.users.domain.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfoDto extends AuditDto {
    private Long id;
    private String name;
    private String lastName;
    private String dni;
    private Role role;
    private String profilePicture;
    private String email;
}
