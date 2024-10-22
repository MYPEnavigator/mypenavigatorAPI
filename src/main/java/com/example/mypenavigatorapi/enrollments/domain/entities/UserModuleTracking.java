package com.example.mypenavigatorapi.enrollments.domain.entities;

import com.example.mypenavigatorapi.courses.domain.entities.Course;
import com.example.mypenavigatorapi.courses.domain.entities.Module;
import com.example.mypenavigatorapi.users.domain.entities.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "user_module_tracking")
public class UserModuleTracking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "is_completed", columnDefinition = "boolean default false")
    private Boolean isCompleted;

    @Column(name = "is_started")
    private Boolean isStarted;

    @Column(name = "is_blocked", columnDefinition = "boolean default false")
    private Boolean isBlocked;

    @Column(name = "is_video_completed", columnDefinition = "boolean default false")
    private Boolean isVideoCompleted;

    @Column(name = "is_material_downloaded", columnDefinition = "boolean default false")
    private Boolean isMaterialDownloaded;

    @Column(name = "test_score")
    private Integer testScore;

    @Column(name = "progress")
    private Integer progress;

    @Column(name = "started_at")
    private Date startedAt;

    @Column(name = "completed_at")
    private Date completedAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "module_id")
    private Module module;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
}
