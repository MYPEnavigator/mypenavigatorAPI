package com.example.mypenavigatorapi.users.domain.entities;

import com.example.mypenavigatorapi.common.domain.entity.AuditModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "mypes")
public class Mype extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "ruc", unique = true)
    private String ruc;
}
