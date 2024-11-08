package com.example.mypenavigatorapi.enrollments.domain.dto;

import com.example.mypenavigatorapi.courses.domain.dto.CourseInfoDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnrollmentWithCourseDto {
    private Long id;
    private Boolean isCompleted;
    private Integer progress;
    private String certificateUrl;
    private CourseInfoDto course;
}
