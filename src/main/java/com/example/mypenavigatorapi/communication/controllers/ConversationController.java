package com.example.mypenavigatorapi.communication.controllers;

import com.example.mypenavigatorapi.common.mapper.Mapper;
import com.example.mypenavigatorapi.communication.domain.dto.ConversationDto;
import com.example.mypenavigatorapi.communication.domain.dto.SaveConversationDto;
import com.example.mypenavigatorapi.communication.domain.entities.Conversation;
import com.example.mypenavigatorapi.communication.services.ConversationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/conversations")
@Tag(name = "conversations", description = "endpoints to manage conversations between users")
public class ConversationController {
    @Autowired
    private ConversationService conversationService;


    @GetMapping("/{userId}")
    @Operation(summary = "Get all conversations by user id")
    public List<ConversationDto> findAllConversationsByUserId(@PathVariable("userId") Long userId) {
        return conversationService.findAllConversationsByUserId(userId).stream()
                .map(conversation -> Mapper.map(conversation, ConversationDto.class))
                .toList();
    }

    @PostMapping()
    @Operation(summary = "Start a new conversation")
    public ConversationDto save(@Valid @RequestBody SaveConversationDto dto) {
        return Mapper.map(conversationService.saveConversation(dto), ConversationDto.class);
    }

    @PostMapping("/{conversationId}/participants/{userId}")
    @Operation(summary = "Add participant to conversation")
    public ConversationDto addParticipantToConversation(@PathVariable("conversationId") Long conversationId,
                                                        @PathVariable("userId") Long userId) {
        Conversation conversation = conversationService.addParticipantToConversation(conversationId, userId);
        return Mapper.map(conversation, ConversationDto.class);
    }

    @PatchMapping("/{conversationId}/participants/{userId}")
    @Operation(summary = "Remove participant from conversation")
    public ConversationDto removeParticipantFromConversation(@PathVariable("conversationId") Long conversationId,
                                                             @PathVariable("userId") Long userId) {
        Conversation conversation = conversationService.removeParticipantFromConversation(conversationId, userId);
        return Mapper.map(conversation, ConversationDto.class);
    }

    @DeleteMapping("/{conversationId}")
    @Operation(summary = "Delete conversation by id")
    public ResponseEntity<?> deleteConversation(@PathVariable("conversationId") Long conversationId) {
        return conversationService.deleteConversation(conversationId);
    }
}
