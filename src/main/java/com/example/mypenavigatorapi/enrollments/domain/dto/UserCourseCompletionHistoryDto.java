package com.example.mypenavigatorapi.enrollments.domain.dto;

import com.example.mypenavigatorapi.courses.domain.dto.CourseDto;
import com.example.mypenavigatorapi.users.domain.dto.BankDto;
import com.example.mypenavigatorapi.users.domain.dto.UserDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCourseCompletionHistoryDto {
    private Long id;
    private String completionDate;
    private Integer pointsEarned;
    private BankDto bank;
    private UserDto user;
}
