package com.example.mypenavigatorapi.common.handlers;

import com.example.mypenavigatorapi.common.events.NotificationEvent;
import com.example.mypenavigatorapi.communication.domain.entities.Notification;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.event.EventListener;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.util.UriTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class NotificationHandler extends TextWebSocketHandler {

    private final ObjectMapper mapper = new ObjectMapper();
    private final Map<Long, List<WebSocketSession>> sessionsByUserId = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(@NonNull WebSocketSession session) throws Exception {
        Long userId = extractUserId(session);
        if (userId == null) {
            session.close();
            return;
        }

        sessionsByUserId.computeIfAbsent(userId, k -> new ArrayList<>()).add(session);
    }

    @Override
    public void afterConnectionClosed(@NonNull WebSocketSession session, @NonNull CloseStatus status) throws Exception {
        Long userId = extractUserId(session);

        if (userId == null) return;

        List<WebSocketSession> sessions = sessionsByUserId.get(userId);
        if (sessions == null) return;

        sessions.remove(session);
        if (sessions.isEmpty()) {
            sessionsByUserId.remove(userId);
        }
    }

    @Override
    protected void handleTextMessage(@NonNull WebSocketSession session, @NonNull TextMessage message) throws Exception {
        Long conversationId = extractUserId(session);
        if (conversationId == null) return;

        List<WebSocketSession> sessions = sessionsByUserId.get(conversationId);
        if (sessions == null) return;

        for (WebSocketSession webSocketSession : sessions) {
            if (webSocketSession.isOpen()) {
                webSocketSession.sendMessage(message);
            }
        }
    }

    @EventListener
    public void handleNotificationEvent(NotificationEvent event) {
        Notification notification = event.getNotification();
        Long userId = notification.getUser().getId();

        notification.setUser(null);

        List<WebSocketSession> sessions = sessionsByUserId.get(userId);
        if (sessions == null) return;

        String notificationJson = "";
        try {
            notificationJson = mapper.writeValueAsString(notification);
            TextMessage textMessage = new TextMessage(notificationJson);

            for (WebSocketSession session : sessions) {
                if (session.isOpen()) {
                    session.sendMessage(textMessage);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private Long extractUserId(WebSocketSession session) {
        String path = Objects.requireNonNull(session.getUri()).getPath();

        UriTemplate template = new UriTemplate("/ws/notifications/{userId}");
        Map<String, String> variables = template.match(path);
        String userIdStr = variables.get("userId");
        return userIdStr != null ? Long.parseLong(userIdStr) : null;
    }
}
