package com.example.mypenavigatorapi.communication.domain.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveForumCommentDto {
    @NotEmpty
    @NotNull
    private String content;

    @NotNull
    @Positive
    private Long userId;

    @NotNull
    @Positive
    private Long courseId;

    private Long parentId;
}
