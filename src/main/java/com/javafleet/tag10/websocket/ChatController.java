package com.javafleet.tag10.websocket;

import com.javafleet.tag10.service.ApplicationStatistics;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

/**
 * ChatController - STOMP WebSocket Messaging
 * 
 * Demonstriert:
 * - @MessageMapping (Tag 8)
 * - @SendTo Broadcasting (Tag 8)
 * - High-Level STOMP API
 * - Real-Time Communication
 * 
 * @author Nova Trent
 */
@Controller
@RequiredArgsConstructor
@Slf4j
public class ChatController {
    
    private final ApplicationStatistics statistics;
    
    /**
     * Empfängt Chat-Messages und broadcasted sie an alle Clients
     * 
     * Client sendet an: /app/chat.send
     * Alle Clients empfangen von: /topic/public
     */
    @MessageMapping("/chat.send")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage message) {
        log.info("💬 WebSocket Message: {} sagt '{}'", 
            message.getSender(), message.getContent());
        
        message.setTimestamp(System.currentTimeMillis());
        return message;
    }
    
    /**
     * User betritt den Chat
     */
    @MessageMapping("/chat.join")
    @SendTo("/topic/public")
    public ChatMessage joinChat(@Payload ChatMessage message, 
                                SimpMessageHeaderAccessor headerAccessor) {
        
        // Username in WebSocket Session speichern
        headerAccessor.getSessionAttributes().put("username", message.getSender());
        
        statistics.incrementWebSocketConnections();
        
        log.info("👋 {} hat den Chat betreten", message.getSender());
        
        message.setType(MessageType.JOIN);
        message.setContent(message.getSender() + " ist dem Chat beigetreten!");
        message.setTimestamp(System.currentTimeMillis());
        
        return message;
    }
}
