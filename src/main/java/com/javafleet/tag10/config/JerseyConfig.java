package com.javafleet.tag10.config;

import com.javafleet.tag10.controller.PersonResource;
import jakarta.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

/**
 * JerseyConfig - JAX-RS Configuration
 * 
 * Demonstriert:
 * - Jersey Integration in Spring Boot (Tag 9)
 * - JAX-RS Resource Registration
 * - Jakarta EE Standards in Spring Boot 3.x
 * 
 * Diese Config ermöglicht JAX-RS neben Spring MVC!
 * 
 * @author Franz-Martin Schmidt
 */
@Component
@ApplicationPath("/api")
public class JerseyConfig extends ResourceConfig {
    
    public JerseyConfig() {
        // Registriere JAX-RS Resources
        register(PersonResource.class);
        
        // Logging Features (optional)
        // register(LoggingFeature.class);
    }
}
