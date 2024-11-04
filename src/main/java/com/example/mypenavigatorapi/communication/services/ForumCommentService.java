package com.example.mypenavigatorapi.communication.services;

import com.example.mypenavigatorapi.common.mapper.Mapper;
import com.example.mypenavigatorapi.communication.domain.dto.SaveForumCommentDto;
import com.example.mypenavigatorapi.communication.domain.entities.ForumComment;
import com.example.mypenavigatorapi.communication.domain.repositories.ForumCommentRepository;
import com.example.mypenavigatorapi.courses.domain.entities.Course;
import com.example.mypenavigatorapi.courses.services.CourseService;
import com.example.mypenavigatorapi.users.domain.entities.User;
import com.example.mypenavigatorapi.users.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class ForumCommentService {
    @Autowired
    private ForumCommentRepository forumCommentRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CourseService courseService;

    public List<ForumComment> findAllByCourseId(Long courseId) {
        return forumCommentRepository.findAllByCourseId(courseId, Sort.by(Sort.Direction.DESC, "createdAt"))
                .stream()
                .filter(forumComment -> forumComment.getParent() == null)
                .toList();
    }

    public List<ForumComment> findAllByParentId(Long parentId) {
        return forumCommentRepository.findAllByParentId(parentId);
    }

    public List<ForumComment> findAllByUserId(Long userId) {
        return forumCommentRepository.findAllByUserId(userId);
    }

    public ForumComment save(SaveForumCommentDto dto){
        User user = userService.findById(dto.getUserId());
        Course course = courseService.findById(dto.getCourseId());

        ForumComment forumComment = new ForumComment();

        forumComment.setContent(dto.getContent());
        forumComment.setUser(user);
        forumComment.setCourse(course);

        if(dto.getParentId() != null){
            ForumComment parent = forumCommentRepository.findById(dto.getParentId())
                    .orElseThrow(() -> new RuntimeException("Parent not found"));
            forumComment.setParent(parent);
        }

        return forumCommentRepository.save(forumComment);
    }

    public ResponseEntity<?> delete(Long id){
        ForumComment forumComment = forumCommentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Forum comment not found"));

        forumCommentRepository.delete(forumComment);

        return ResponseEntity.ok().build();
    }
}
