package com.example.mypenavigatorapi.communication.domain.entities;

import com.example.mypenavigatorapi.common.domain.entity.AuditModel;
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

    @OneToMany(mappedBy = "conversation", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<ConversationParticipant> participants;
}
