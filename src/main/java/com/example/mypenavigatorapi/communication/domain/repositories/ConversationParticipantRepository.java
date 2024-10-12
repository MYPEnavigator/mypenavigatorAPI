package com.example.mypenavigatorapi.communication.domain.repositories;

import com.example.mypenavigatorapi.communication.domain.entities.ConversationParticipant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConversationParticipantRepository extends JpaRepository<ConversationParticipant, Long> {
    List<ConversationParticipant> findAllByConversationId(Long conversationId);
}
