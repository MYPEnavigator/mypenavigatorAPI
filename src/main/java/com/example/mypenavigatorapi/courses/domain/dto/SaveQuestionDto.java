package com.example.mypenavigatorapi.courses.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SaveQuestionDto {
    @NotNull
    @NotBlank
    private String questionText;

    @NotNull
    @NotBlank
    private String type;

    @NotNull
    private List<SaveAnswerDto> answers;
}
