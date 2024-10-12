package com.example.mypenavigatorapi.users.domain.entities;

import com.example.mypenavigatorapi.common.domain.entity.AuditModel;
import com.example.mypenavigatorapi.users.domain.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "dni")
    private String dni;

    @Column(name = "role")
    private Role role;

    @ManyToOne(optional = true)
    @JoinColumn(name = "bank_id", nullable = true)
    private Bank bank;

    @ManyToOne(optional = true)
    @JoinColumn(name = "mype_id", nullable = true)
    private Mype mype;

}
