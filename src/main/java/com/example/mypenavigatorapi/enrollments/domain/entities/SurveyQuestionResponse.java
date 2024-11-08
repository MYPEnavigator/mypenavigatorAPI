package com.example.mypenavigatorapi.enrollments.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "survey_question_responses")
public class SurveyQuestionResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "question")
    private String question;

    @Column(name = "rating")
    private Integer rating;

    @ManyToOne
    @JoinColumn(name = "survey_id", insertable = true, updatable = true)
    private Survey survey;
}
