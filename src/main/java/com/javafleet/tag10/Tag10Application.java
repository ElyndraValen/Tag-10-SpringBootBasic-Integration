package com.javafleet.tag10;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Spring Boot Basic - Tag 10: Integration & Abschluss
 * 
 * Das große Finale - alle Konzepte zusammengeführt!
 * 
 * @author Elyndra Valen, Java Fleet Systems Consulting
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.javafleet.*")
public class Tag10Application {
    
    public static void main(String[] args) {
        SpringApplication.run(Tag10Application.class, args);
        
        System.out.println("\n" +
            "╔═══════════════════════════════════════════════════════════╗\n" +
            "║   🎉 SPRING BOOT BASIC - TAG 10: INTEGRATION & ABSCHLUSS    ║\n" +
            "║                                                           ║\n" +
            "║   🚀 Application gestartet auf http://localhost:8080        ║\n" +
            "║                                                           ║\n" +
            "║   ?  Alle 9 Tage zusammengeführt:                         ║\n" +
            "║   ✅ REST API (Spring MVC + JAX-RS)                        ║\n" +
            "║   ✅ Thymeleaf Views & Forms                               ║\n" +
            "║   ✅ Dependency Injection & AOP                            ║\n" +
            "║   ✅ Bean Scopes & Lifecycle                               ║\n" +
            "║   ✅ WebSockets (Low-Level + STOMP)                        ║\n" +
            "║   ✅ Configuration & Logging                               ║\n" +
            "║   ✅ Jakarta EE Standards Integration                      ║\n" +
            "║                                                           ║\n" +
            "║   💪 DU HAST ES GESCHAFFT!                                  ║\n" +
            "╚═══════════════════════════════════════════════════════════╝\n");
    }
}
