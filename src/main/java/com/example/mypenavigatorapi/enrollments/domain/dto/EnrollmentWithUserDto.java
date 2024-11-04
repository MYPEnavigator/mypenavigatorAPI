package com.example.mypenavigatorapi.enrollments.domain.dto;

import com.example.mypenavigatorapi.users.domain.dto.UserInfoDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnrollmentWithUserDto {
    private Long id;
    private Boolean isCompleted;
    private Integer progress;
    private String certificateUrl;
    private UserInfoDto user;
}
