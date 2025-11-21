package com.javafleet.tag10.service;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * ApplicationStatistics - Application-Scoped Component
 * 
 * Demonstriert:
 * - Application Scope / Singleton (Tag 7)
 * - Shared State über alle Sessions
 * - Thread-Safe Counters (AtomicInteger)
 * 
 * Diese Bean ist Application-weit einzigartig und
 * wird von allen Usern gemeinsam verwendet.
 * 
 * @author Code Sentinel
 */
@Component
@Slf4j
@Getter
public class ApplicationStatistics {
    
    private final AtomicInteger totalApiCalls = new AtomicInteger(0);
    private final AtomicInteger totalViewRequests = new AtomicInteger(0);
    private final AtomicInteger totalWebSocketConnections = new AtomicInteger(0);
    
    @PostConstruct
    public void init() {
        log.info("📊 ApplicationStatistics initialized (Application Scope)");
        log.info("💡 Diese Bean ist shared über ALLE Sessions!");
    }
    
    public void incrementApiCalls() {
        int count = totalApiCalls.incrementAndGet();
        log.debug("📈 Total API Calls: {}", count);
    }
    
    public void incrementViewRequests() {
        int count = totalViewRequests.incrementAndGet();
        log.debug("📈 Total View Requests: {}", count);
    }
    
    public void incrementWebSocketConnections() {
        int count = totalWebSocketConnections.incrementAndGet();
        log.info("🔌 WebSocket Connection #{}", count);
    }
    
    public void decrementWebSocketConnections() {
        int count = totalWebSocketConnections.decrementAndGet();
        log.info("🔌 WebSocket Disconnection (remaining: {})", count);
    }
}
