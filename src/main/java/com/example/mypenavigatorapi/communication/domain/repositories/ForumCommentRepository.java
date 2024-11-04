package com.example.mypenavigatorapi.communication.domain.repositories;

import com.example.mypenavigatorapi.communication.domain.entities.ForumComment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ForumCommentRepository extends JpaRepository<ForumComment, Long> {
    List<ForumComment> findAllByCourseId(Long courseId, Sort sort);
    List<ForumComment> findAllByParentId(Long parentId);
    List<ForumComment> findAllByUserId(Long userId);
}
