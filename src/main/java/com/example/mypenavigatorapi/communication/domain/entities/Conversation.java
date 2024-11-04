package com.example.mypenavigatorapi.communication.domain.entities;

import com.example.mypenavigatorapi.common.domain.entity.AuditModel;
import com.example.mypenavigatorapi.users.domain.entities.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "conversations")
public class Conversation extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "conversation")
    private List<Message> messages;

    @ManyToOne
    @JoinColumn(name = "first_participant_id")
    private User firstParticipant;

    @ManyToOne
    @JoinColumn(name = "second_participant_id")
    private User secondParticipant;
}
