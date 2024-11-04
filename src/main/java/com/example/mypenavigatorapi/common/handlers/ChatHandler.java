package com.example.mypenavigatorapi.common.handlers;

import com.example.mypenavigatorapi.common.events.MessageEvent;
import com.example.mypenavigatorapi.communication.domain.entities.Message;
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
public class ChatHandler extends TextWebSocketHandler {

    private final ObjectMapper mapper = new ObjectMapper();
    private final Map<Long, List<WebSocketSession>> sessionsByConversation = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(@NonNull WebSocketSession session) throws Exception {
        Long conversationId = extractConversationId(session);
        if (conversationId == null) {
            session.close();
            return;
        }

        sessionsByConversation.computeIfAbsent(conversationId, k -> new ArrayList<>()).add(session);
    }

    @Override
    public void afterConnectionClosed(@NonNull WebSocketSession session, @NonNull CloseStatus status) throws Exception {
        Long conversationId = extractConversationId(session);

        if (conversationId == null) return;

        List<WebSocketSession> sessions = sessionsByConversation.get(conversationId);
        if (sessions == null) return;

        sessions.remove(session);
        if (sessions.isEmpty()) {
            sessionsByConversation.remove(conversationId);
        }
    }

    @Override
    protected void handleTextMessage(@NonNull WebSocketSession session, @NonNull TextMessage message) throws Exception {
        Long conversationId = extractConversationId(session);
        if (conversationId == null) return;

        List<WebSocketSession> sessions = sessionsByConversation.get(conversationId);
        if (sessions == null) return;

        for (WebSocketSession webSocketSession : sessions) {
            if (webSocketSession.isOpen()) {
                webSocketSession.sendMessage(message);
            }
        }
    }

    @EventListener
    public void handleMessageEvent(MessageEvent event) {
        Message message = event.getMessage();
        Long conversationId = message.getConversation().getId();

        message.getConversation().setMessages(null);

        List<WebSocketSession> sessions = sessionsByConversation.get(conversationId);
        if (sessions == null) return;

        String messageJson = "";
        try {
            messageJson = mapper.writeValueAsString(message);
            TextMessage textMessage = new TextMessage(messageJson);

            for (WebSocketSession session : sessions) {
                if (session.isOpen()) {
                    session.sendMessage(textMessage);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private Long extractConversationId(WebSocketSession session) {
        String path = Objects.requireNonNull(session.getUri()).getPath();

        UriTemplate template = new UriTemplate("/chat/{conversationId}");
        Map<String, String> variables = template.match(path);
        String conversationIdStr = variables.get("conversationId");
        return conversationIdStr != null ? Long.parseLong(conversationIdStr) : null;
    }
}
