package com.example.mypenavigatorapi.communication.domain.entities;

import com.example.mypenavigatorapi.common.domain.entity.AuditModel;
import com.example.mypenavigatorapi.users.domain.entities.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "conversation_participants")
public class ConversationParticipant extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    @Column(name = "joined_at")
    private Date joinedAt;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "conversation_id", insertable = true)
    private Conversation conversation;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
