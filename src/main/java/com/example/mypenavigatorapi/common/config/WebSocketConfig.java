package com.example.mypenavigatorapi.common.config;

import com.example.mypenavigatorapi.common.handlers.ChatHandler;
import com.example.mypenavigatorapi.common.handlers.NotificationHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    private final ChatHandler chatHandler;
    private final NotificationHandler notificationHandler;

    public WebSocketConfig(ChatHandler chatHandler, NotificationHandler notificationHandler) {
        this.chatHandler = chatHandler;
        this.notificationHandler = notificationHandler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(chatHandler, "/ws/chat/{conversationId}")
            .setAllowedOrigins("*");

        registry.addHandler(notificationHandler, "/ws/notifications/{userId}")
            .setAllowedOrigins("*");
    }
}
