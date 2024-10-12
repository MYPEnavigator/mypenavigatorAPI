package com.example.mypenavigatorapi.courses.domain.dto;

import com.example.mypenavigatorapi.courses.domain.entities.Question;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TestDto {
    private Long id;
    private String title;
    private List<Question> questions;
}
