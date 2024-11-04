package com.example.mypenavigatorapi.communication.controllers;

import com.example.mypenavigatorapi.common.mapper.Mapper;
import com.example.mypenavigatorapi.communication.domain.dto.ForumCommentDto;
import com.example.mypenavigatorapi.communication.domain.dto.SaveForumCommentDto;
import com.example.mypenavigatorapi.communication.services.ForumCommentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/forum-comments")
@Tag(name = "forum-comments", description = "endpoints to manage forum comments")
public class ForumCommentController {
    @Autowired
    private ForumCommentService forumCommentService;

    @GetMapping("/course/{courseId}")
    public List<ForumCommentDto> findAllByCourseId(
            @PathVariable("courseId") Long courseId) {
        return forumCommentService.findAllByCourseId(courseId)
                .stream()
                .map(forumComment -> Mapper.map(forumComment, ForumCommentDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/user/{userId}")
    public List<ForumCommentDto> findAllByUserId(
            @PathVariable("userId") Long userId) {
        return forumCommentService.findAllByUserId(userId)
                .stream()
                .map(forumComment -> Mapper.map(forumComment, ForumCommentDto.class))
                .collect(Collectors.toList());
    }

    @PostMapping
    public ForumCommentDto save(@Valid @RequestBody SaveForumCommentDto dto) {
        return Mapper.map(forumCommentService.save(dto), ForumCommentDto.class);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(
            @PathVariable("id") Long id) {
        return forumCommentService.delete(id);
    }
}
