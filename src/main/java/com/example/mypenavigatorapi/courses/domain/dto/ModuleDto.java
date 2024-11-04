package com.example.mypenavigatorapi.courses.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModuleDto {
    private Long id;
    private String title;
    private String imageUrl;
    private String materialUrl;
    private String videoUrl;
    private String duration;
    private Integer order;
    private TestDto test;
}
