package com.example.mypenavigatorapi.communication.services;

import com.example.mypenavigatorapi.common.events.MessageEvent;
import com.example.mypenavigatorapi.common.exceptions.ResourceNotFoundException;
import com.example.mypenavigatorapi.common.mapper.Mapper;
import com.example.mypenavigatorapi.communication.domain.dto.SaveMessageDto;
import com.example.mypenavigatorapi.communication.domain.dto.SaveNotificationDto;
import com.example.mypenavigatorapi.communication.domain.entities.Conversation;
import com.example.mypenavigatorapi.communication.domain.entities.Message;
import com.example.mypenavigatorapi.communication.domain.repositories.ConversationRepository;
import com.example.mypenavigatorapi.communication.domain.repositories.MessageRepository;
import com.example.mypenavigatorapi.communication.events.NewNotificationEvent;
import com.example.mypenavigatorapi.users.domain.entities.User;
import com.example.mypenavigatorapi.users.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ConversationRepository conversationRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ApplicationEventPublisher eventPublisher;


    public Message sendMessage(SaveMessageDto dto) {
        Message message = new Message();

        User user = userService.findById(dto.getSenderId());
        Conversation conversation = conversationRepository.findById(dto.getConversationId())
                .orElseThrow(() -> new ResourceNotFoundException("Conversation", "id", dto.getConversationId()));

        message.setContent(dto.getContent());
        message.setSender(user);
        message.setConversation(conversation);
        message.setSentAt(new Date());

        Message savedMessage = messageRepository.save(message);



        sendNotification(conversation, savedMessage);
        eventPublisher.publishEvent(new MessageEvent(this, savedMessage));
        return savedMessage;
    }

    public void sendNotification(Conversation conversation, Message message) {
        User receiver = Objects.equals(conversation.getFirstParticipant().getId(), message.getSender().getId()) ?
                conversation.getSecondParticipant() : conversation.getFirstParticipant();

        SaveNotificationDto newNotification = new SaveNotificationDto();
        newNotification.setTitle("Nuevo mensaje");
        newNotification.setText("Has recibido un nuevo mensaje de " + message.getSender().getName());
        eventPublisher.publishEvent(new NewNotificationEvent(this, receiver.getId(), newNotification,true));
    }
}
