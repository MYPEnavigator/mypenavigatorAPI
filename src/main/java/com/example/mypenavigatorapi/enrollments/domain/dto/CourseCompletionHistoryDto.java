package com.example.mypenavigatorapi.enrollments.domain.dto;

import com.example.mypenavigatorapi.courses.domain.dto.CourseDto;
import com.example.mypenavigatorapi.users.domain.dto.BankDto;
import com.example.mypenavigatorapi.users.domain.dto.UserDto;
import com.example.mypenavigatorapi.users.domain.entities.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseCompletionHistoryDto {
    private Long id;
    private String completionDate;
    private Integer pointsEarned;
    private Integer resultPercentage;
    private User user;
    private BankDto bank;
    private CourseDto course;
}
