package com.example.mypenavigatorapi.communication.services;

import com.example.mypenavigatorapi.common.exceptions.ResourceNotFoundException;
import com.example.mypenavigatorapi.common.mapper.Mapper;
import com.example.mypenavigatorapi.communication.domain.dto.SaveConversationDto;
import com.example.mypenavigatorapi.communication.domain.entities.Conversation;
import com.example.mypenavigatorapi.communication.domain.entities.ConversationParticipant;
import com.example.mypenavigatorapi.communication.domain.repositories.ConversationRepository;
import com.example.mypenavigatorapi.users.domain.entities.User;
import com.example.mypenavigatorapi.users.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ConversationService {
    @Autowired
    private ConversationRepository conversationRepository;

    @Autowired
    private UserService userService;

    public List<Conversation> findAllConversationsByUserId(Long userId) {
        return conversationRepository.findAllByUserId(userId);
    }

    public Conversation saveConversation(SaveConversationDto dto) {
        Conversation conversation = Mapper.map(dto, Conversation.class);

        User user = userService.findById(dto.getFirstParticipantId());
        User user2 = userService.findById(dto.getSecondParticipantId());

        List<ConversationParticipant> participants = new ArrayList<>();

        ConversationParticipant participant = new ConversationParticipant();
        participant.setJoinedAt(new Date());
        participant.setUser(user);

        ConversationParticipant participant2 = new ConversationParticipant();
        participant2.setJoinedAt(new Date());
        participant2.setUser(user2);

        participants.add(participant);
        participants.add(participant2);

        conversation.setParticipants(participants);


        return conversationRepository.save(conversation);
    }

    public Conversation addParticipantToConversation(Long conversationId, Long userId) {
        Conversation conversation = conversationRepository.findById(conversationId)
                .orElseThrow(() -> new ResourceNotFoundException("Conversation", "id", conversationId));

        User user = userService.findById(userId);

        List<ConversationParticipant> participants = conversation.getParticipants();

        ConversationParticipant participant = new ConversationParticipant();
        participant.setJoinedAt(new Date());
        participant.setUser(user);
        participant.setConversation(conversation);

        participants.add(participant);

        conversation.setParticipants(participants);

        return conversationRepository.save(conversation);
    }

    public Conversation removeParticipantFromConversation(Long conversationId, Long userId) {
        Conversation conversation = conversationRepository.findById(conversationId)
                .orElseThrow(() -> new ResourceNotFoundException("Conversation", "id", conversationId));

        User user = userService.findById(userId);

        List<ConversationParticipant> participants = conversation.getParticipants();

        participants.removeIf(participant -> participant.getUser().getId().equals(userId));

        conversation.setParticipants(participants);

        return conversationRepository.save(conversation);
    }

    public ResponseEntity<?> deleteConversation(Long conversationId) {
        Conversation conversation = conversationRepository.findById(conversationId)
                .orElseThrow(() -> new ResourceNotFoundException("Conversation", "id", conversationId));

        conversationRepository.delete(conversation);

        return ResponseEntity.ok().build();
    }
}
