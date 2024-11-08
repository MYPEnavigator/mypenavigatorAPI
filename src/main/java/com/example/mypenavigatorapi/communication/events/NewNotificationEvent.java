package com.example.mypenavigatorapi.communication.events;

import com.example.mypenavigatorapi.communication.domain.dto.SaveNotificationDto;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class NewNotificationEvent extends ApplicationEvent {
    private final Long userId;
    private final SaveNotificationDto notification;
    private final boolean publishEvent;

    public NewNotificationEvent(Object source, Long userId, SaveNotificationDto notification, boolean publishEvent) {
        super(source);
        this.userId = userId;
        this.notification = notification;
        this.publishEvent = publishEvent;
    }
}
