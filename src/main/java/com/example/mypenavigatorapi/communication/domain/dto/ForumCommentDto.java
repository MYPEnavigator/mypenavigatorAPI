package com.example.mypenavigatorapi.communication.domain.dto;

import com.example.mypenavigatorapi.courses.domain.dto.CourseInfoDto;
import com.example.mypenavigatorapi.users.domain.dto.UserInfoDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ForumCommentDto {
    private Long id;
    private String content;
    private UserInfoDto user;
    private List<ForumCommentDto> children;
}
