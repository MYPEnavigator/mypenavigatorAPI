package com.example.mypenavigatorapi.common.events;

import com.example.mypenavigatorapi.communication.domain.entities.Message;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class MessageEvent extends ApplicationEvent {
    private final Message message;

    public MessageEvent(Object source, Message message) {
        super(source);
        this.message = message;
    }

}
