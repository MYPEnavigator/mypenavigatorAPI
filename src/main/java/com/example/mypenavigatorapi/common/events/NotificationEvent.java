package com.example.mypenavigatorapi.common.events;

import com.example.mypenavigatorapi.communication.domain.entities.Notification;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class NotificationEvent extends ApplicationEvent {
    private final Notification notification;

    public NotificationEvent(Object source, Notification notification) {
        super(source);
        this.notification = notification;
    }
}
