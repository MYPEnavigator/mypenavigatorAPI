package com.example.mypenavigatorapi.courses.domain.entities;

import com.example.mypenavigatorapi.common.domain.entity.AuditModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "course_modules")
public class Module extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "module_order")
    private Integer order;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "material_url")
    private String materialUrl;

    @Column(name = "video_url")
    private String videoUrl;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @OneToOne(mappedBy = "module")
    private Test test;
}
