package com.javafleet.tag10.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * WebSocketConfig - STOMP WebSocket Configuration
 * 
 * Demonstriert:
 * - WebSocket Configuration (Tag 8)
 * - STOMP Protocol (Tag 8)
 * - Message Broker Setup
 * 
 * High-Level WebSocket API für Production-Ready Real-Time Features!
 * 
 * @author Nova Trent
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // Simple In-Memory Broker für /topic
        config.enableSimpleBroker("/topic");
        
        // Application Destination Prefix
        config.setApplicationDestinationPrefixes("/app");
    }
    
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // WebSocket Endpoint mit SockJS Fallback
        registry.addEndpoint("/ws")
                .setAllowedOriginPatterns("*")
                .withSockJS();
    }
}
