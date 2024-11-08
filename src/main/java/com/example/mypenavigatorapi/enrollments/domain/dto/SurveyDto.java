package com.example.mypenavigatorapi.enrollments.domain.dto;

import com.example.mypenavigatorapi.users.domain.dto.UserDto;
import com.example.mypenavigatorapi.users.domain.dto.UserInfoDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SurveyDto {
    private Long id;
    private List<SurveyQuestionResponseDto> responses;
    private UserInfoDto user;
}
