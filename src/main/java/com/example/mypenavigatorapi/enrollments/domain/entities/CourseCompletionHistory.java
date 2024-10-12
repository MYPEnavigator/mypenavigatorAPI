package com.example.mypenavigatorapi.enrollments.domain.entities;

import com.example.mypenavigatorapi.common.domain.entity.AuditModel;
import com.example.mypenavigatorapi.courses.domain.entities.Course;
import com.example.mypenavigatorapi.users.domain.entities.Bank;
import com.example.mypenavigatorapi.users.domain.entities.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "course_completion_histories")
public class CourseCompletionHistory extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "completion_date")
    private Date completionDate;

    @Column(name = "points_earned")
    private Integer pointsEarned;

    @Column(name = "result_percentage")
    private Integer resultPercentage;

    @ManyToOne
    @JoinColumn(name = "bank_id")
    private Bank bank;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
}
