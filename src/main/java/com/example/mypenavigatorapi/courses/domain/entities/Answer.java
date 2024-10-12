package com.example.mypenavigatorapi.courses.domain.entities;

import com.example.mypenavigatorapi.common.domain.entity.AuditModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "answers")
public class Answer extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "answer_text")
    private String answerText;

    @Column(name = "is_correct")
    private Boolean isCorrect;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "question_id", insertable = true)
    private Question question;
}
