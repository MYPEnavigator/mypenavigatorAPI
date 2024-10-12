package com.example.mypenavigatorapi.courses.domain.dto;

import com.example.mypenavigatorapi.users.domain.dto.BankDto;
import com.example.mypenavigatorapi.users.domain.dto.UserDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseDto {
    private Long id;
    private String title;
    private String description;
    private Integer rewardPoints;
    private String imageUrl;
    private String videoUrl;
    private BankDto bank;
    private UserDto user;
}
