package com.example.mypenavigatorapi.courses.domain.dto;

import com.example.mypenavigatorapi.users.domain.dto.BankDto;
import com.example.mypenavigatorapi.users.domain.dto.UserInfoDto;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CourseInfoDto {
    private Long id;
    private String title;
    private String slug;
    private String description;
    private String duration;
    private Integer rewardPoints;
    private String imageUrl;
    private String videoUrl;
    private String managerName;
    private String signatureUrl;
    private String level;
    private BankDto bank;
    private List<String> syllabus = new ArrayList<>();
    private UserInfoDto user;
}
