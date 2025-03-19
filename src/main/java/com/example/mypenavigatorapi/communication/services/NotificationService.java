package com.example.mypenavigatorapi.communication.services;

import com.example.mypenavigatorapi.common.events.NotificationEvent;
import com.example.mypenavigatorapi.common.exceptions.ResourceNotFoundException;
import com.example.mypenavigatorapi.common.mapper.Mapper;
import com.example.mypenavigatorapi.communication.domain.dto.SaveNotificationDto;
import com.example.mypenavigatorapi.communication.domain.entities.Notification;
import com.example.mypenavigatorapi.communication.domain.repositories.NotificationRepository;
import com.example.mypenavigatorapi.users.domain.entities.User;
import com.example.mypenavigatorapi.users.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public List<Notification> findAllByUserId(Long userId) {
        return notificationRepository.findAllByUserId(userId);
    }

    public Notification save(SaveNotificationDto dto, Long userId, boolean publishEvent) {
        User user = userService.findById(userId);

        Notification notification = Mapper.map(dto, Notification.class);

        notification.setUser(user);


        Notification notificationSaved = notificationRepository.save(notification);

        if(publishEvent) {
            eventPublisher.publishEvent(new NotificationEvent(this, notificationSaved));
        }
        return notificationSaved;
    }

    public List<Notification> seenNotifications(Long userId) {
        List<Notification> notifications = notificationRepository.findAllByUserId(userId)
                .stream()
                .filter(notification -> !notification.isSeen())
                .toList();

        notifications.forEach(notification -> notification.setSeen(true));

        notificationRepository.saveAll(notifications);

        return notificationRepository.findAllByUserId(userId);
    }

    public ResponseEntity<?> delete(Long id) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Notification", "id", id));

        notificationRepository.delete(notification);
        return ResponseEntity.ok().build();
    }
}
