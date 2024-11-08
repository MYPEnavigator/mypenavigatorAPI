package com.example.mypenavigatorapi.enrollments.domain.dto;

import com.example.mypenavigatorapi.courses.domain.dto.CourseInfoDto;
import com.example.mypenavigatorapi.users.domain.dto.BankDto;
import com.example.mypenavigatorapi.users.domain.dto.UserInfoDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseCompletionHistoryDto {
    private Long id;
    private String completionDate;
    private Integer pointsEarned;
    private Integer resultPercentage;
    private UserInfoDto user;
    private BankDto bank;
    private CourseInfoDto course;
}
