package com.javafleet.tag10.websocket;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ChatMessage - WebSocket Message Model
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage {
    
    private MessageType type;
    private String content;
    private String sender;
    private long timestamp;
}
