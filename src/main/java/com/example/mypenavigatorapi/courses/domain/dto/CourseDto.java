package com.example.mypenavigatorapi.courses.domain.dto;

import com.example.mypenavigatorapi.users.domain.dto.BankDto;
import com.example.mypenavigatorapi.users.domain.dto.UserDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CourseDto {
    private Long id;
    private String title;
    private String slug;
    private String description;
    private Integer rewardPoints;
    private String imageUrl;
    private String videoUrl;
    private String managerName;
    private String signatureUrl;
    private String level;
    private List<String> syllabus;
    private BankDto bank;
    private UserDto user;
    private List<ModuleDto> modules;
}
