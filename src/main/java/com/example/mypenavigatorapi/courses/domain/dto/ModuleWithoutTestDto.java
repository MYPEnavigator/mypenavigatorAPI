package com.example.mypenavigatorapi.courses.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModuleWithoutTestDto {
    private Long id;
    private String title;
    private String imageUrl;
    private String materialUrl;
    private String videoUrl;
    private Integer order;
}
