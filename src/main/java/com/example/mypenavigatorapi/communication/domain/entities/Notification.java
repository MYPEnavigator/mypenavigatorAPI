package com.example.mypenavigatorapi.communication.domain.entities;

import com.example.mypenavigatorapi.users.domain.entities.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "notifications")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "notification_text")
    private String text;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
