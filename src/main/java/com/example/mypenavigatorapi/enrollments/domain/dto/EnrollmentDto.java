package com.example.mypenavigatorapi.enrollments.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnrollmentDto {
    private Long id;
    private Boolean isCompleted;
    private Boolean isApproved;
    private Integer progress;
    private String certificateUrl;
}
