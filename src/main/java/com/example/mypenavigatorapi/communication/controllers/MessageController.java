package com.example.mypenavigatorapi.communication.controllers;

import com.example.mypenavigatorapi.common.mapper.Mapper;
import com.example.mypenavigatorapi.communication.domain.dto.MessageDto;
import com.example.mypenavigatorapi.communication.domain.dto.SaveMessageDto;
import com.example.mypenavigatorapi.communication.domain.entities.Message;
import com.example.mypenavigatorapi.communication.services.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/messages")
@Tag(name = "messages", description = "endpoints to manage messages between users")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @PostMapping
    @Operation(summary = "Send a message")
    public MessageDto sendMessage(@Valid @RequestBody SaveMessageDto dto) {
        Message message = messageService.sendMessage(dto);

        return Mapper.map(message, MessageDto.class);
    }
}
