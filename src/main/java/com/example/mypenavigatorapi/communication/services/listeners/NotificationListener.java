package com.example.mypenavigatorapi.communication.services.listeners;

import com.example.mypenavigatorapi.communication.events.NewNotificationEvent;
import com.example.mypenavigatorapi.communication.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationListener {
    @Autowired
    private NotificationService notificationService;

    @EventListener
    public void onNotificationEvent(NewNotificationEvent event) {
        notificationService.save(event.getNotification(), event.getUserId(), event.isPublishEvent());
    }
}
