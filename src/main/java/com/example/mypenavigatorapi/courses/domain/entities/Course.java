package com.example.mypenavigatorapi.courses.domain.entities;

import com.example.mypenavigatorapi.common.domain.entity.AuditModel;
import com.example.mypenavigatorapi.users.domain.entities.Bank;
import com.example.mypenavigatorapi.users.domain.entities.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "courses")
public class Course extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "slug")
    private String slug;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "video_url")
    private String videoUrl;

    @Lob
    @Column(name = "description")
    private String description;

    @Column(name = "reward_points")
    private Integer rewardPoints;

    @Column(name = "manager_name")
    private String managerName;

    @Column(name = "signature_url")
    private String signatureUrl;

    @Column(name = "level")
    private String level;

    @ElementCollection
    @CollectionTable(name = "course_syllabus", joinColumns = @JoinColumn(name = "course_id"))
    @Column(name = "syllabus_item")
    private List<String> syllabus;

    @ManyToOne
    @JoinColumn(name = "bank_id")
    private Bank bank;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Module> modules;
}
