package com.example.mypenavigatorapi.communication.domain.dto;

import com.example.mypenavigatorapi.users.domain.dto.UserInfoDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GeneralSurveyDto {
    private Long id;
    private String question1;
    private String question2;
    private String question3;
    private String question4;
    private String question5;

    private UserInfoDto user;
}
