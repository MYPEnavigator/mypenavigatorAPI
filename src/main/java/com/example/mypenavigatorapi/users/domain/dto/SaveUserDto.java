package com.example.mypenavigatorapi.users.domain.dto;

import com.example.mypenavigatorapi.users.domain.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class SaveUserDto {
    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    @Email
    private String email;

    private String password;

    @NotNull
    @NotBlank
    private Role role;

    @NotNull
    @NotBlank
    @Length(min = 8, max = 8, message = "Dni must have 8 digits")
    private String dni;
}
