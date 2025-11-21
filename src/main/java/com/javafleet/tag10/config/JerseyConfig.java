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
 * In der application.propperties gibt es auch eine Endpunktkonfiguration
 * # Jersey (JAX-RS) Configuration (Tag 9)
 * spring.jersey.application-path=/jakarta
 * @author Franz-Martin Schmidt
 */


@Component
@ApplicationPath("/jakarta")
public class JerseyConfig extends ResourceConfig {
    
    public JerseyConfig() {
        // Registriere JAX-RS Resources
        register(PersonResource.class);
        
        // Logging Features (optional)
        // register(LoggingFeature.class);
    }
}
