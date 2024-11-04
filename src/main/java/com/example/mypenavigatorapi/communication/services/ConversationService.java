package com.example.mypenavigatorapi.communication.services;

import com.example.mypenavigatorapi.common.exceptions.ResourceNotFoundException;
import com.example.mypenavigatorapi.common.mapper.Mapper;
import com.example.mypenavigatorapi.communication.domain.dto.SaveConversationDto;
import com.example.mypenavigatorapi.communication.domain.entities.Conversation;
import com.example.mypenavigatorapi.communication.domain.repositories.ConversationRepository;
import com.example.mypenavigatorapi.users.domain.entities.User;
import com.example.mypenavigatorapi.users.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


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

    public Conversation findBySenderAndReceiver(Long senderId, Long receiverId) {
        return conversationRepository.findByFirstParticipantIdAndSecondParticipantId(senderId, receiverId)
                .orElseGet(() -> conversationRepository.findByFirstParticipantIdAndSecondParticipantId(receiverId, senderId)
                        .orElseThrow(() -> new ResourceNotFoundException("Conversation", "senderId and receiverId", senderId + " and " + receiverId)));
    }

    public Conversation saveConversation(SaveConversationDto dto) {
        Conversation conversation = new Conversation();

        User user = userService.findById(dto.getFirstParticipantId());
        User user2 = userService.findById(dto.getSecondParticipantId());

        conversation.setFirstParticipant(user);
        conversation.setSecondParticipant(user2);

        return conversationRepository.save(conversation);
    }

    public ResponseEntity<?> deleteConversation(Long conversationId) {
        Conversation conversation = conversationRepository.findById(conversationId)
                .orElseThrow(() -> new ResourceNotFoundException("Conversation", "id", conversationId));

        conversationRepository.delete(conversation);

        return ResponseEntity.ok().build();
    }
}
