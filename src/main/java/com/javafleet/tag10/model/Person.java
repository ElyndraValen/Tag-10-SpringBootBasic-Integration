package com.javafleet.tag10.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Person Model - Zentrale Domain-Klasse
 * 
 * Verwendet in:
 * - REST API (Tag 1, 9)
 * - Thymeleaf Views (Tag 3, 4)
 * - Service Layer (Tag 2, 6, 7)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    
    private Long id;
    private String firstname;
    private String lastname;
    
    /**
     * Vollständiger Name für Display-Zwecke
     */
    public String getFullName() {
        return firstname + " " + lastname;
    }
}
