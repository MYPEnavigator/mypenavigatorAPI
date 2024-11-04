package com.example.mypenavigatorapi.communication.domain.repositories;

import com.example.mypenavigatorapi.communication.domain.entities.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ConversationRepository extends JpaRepository<Conversation, Long> {
    @Query("SELECT c FROM Conversation c  WHERE c.firstParticipant.id = :userId or c.secondParticipant.id = :userId")
    List<Conversation> findAllByUserId(@Param("userId") Long userId);

    Optional<Conversation> findByFirstParticipantIdAndSecondParticipantId(Long firstParticipantId, Long secondParticipantId);
}
