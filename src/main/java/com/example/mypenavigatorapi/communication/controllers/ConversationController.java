package com.example.mypenavigatorapi.communication.controllers;

import com.example.mypenavigatorapi.common.mapper.Mapper;
import com.example.mypenavigatorapi.communication.domain.dto.ConversationDto;
import com.example.mypenavigatorapi.communication.domain.dto.SaveConversationDto;
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

    @GetMapping
    @Operation(summary = "Find by sender and receiver")
    public ConversationDto findBySenderAndReceiver(
            @RequestParam("senderId") Long senderId,
            @RequestParam("receiverId") Long receiverId) {
        return Mapper.map(conversationService.findBySenderAndReceiver(senderId, receiverId), ConversationDto.class);
    }

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


    @DeleteMapping("/{conversationId}")
    @Operation(summary = "Delete conversation by id")
    public ResponseEntity<?> deleteConversation(@PathVariable("conversationId") Long conversationId) {
        return conversationService.deleteConversation(conversationId);
    }
}
