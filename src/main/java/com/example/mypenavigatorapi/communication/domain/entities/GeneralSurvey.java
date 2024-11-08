package com.example.mypenavigatorapi.communication.domain.entities;

import com.example.mypenavigatorapi.users.domain.entities.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "general_surveys")
public class GeneralSurvey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    User user;

    @Column(name = "question_1")
    private String question1;

    @Column(name = "question_2")
    private String question2;

    @Column(name = "question_3")
    private String question3;

    @Column(name = "question_4")
    private String question4;

    @Column(name = "question_5")
    private String question5;
}
