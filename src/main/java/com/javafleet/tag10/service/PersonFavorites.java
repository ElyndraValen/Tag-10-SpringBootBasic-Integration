package com.javafleet.tag10.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashSet;
import java.util.Set;

/**
 * PersonFavorites - Session-Scoped Component
 * 
 * Demonstriert:
 * - Session Scope (Tag 7)
 * - ScopedProxyMode (Tag 7)
 * - Pro User eine eigene Instanz!
 * 
 * Jeder User hat seine eigene Favoriten-Liste,
 * die während der Session persistent bleibt.
 * 
 * @author Elyndra Valen
 */
@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, 
       proxyMode = ScopedProxyMode.TARGET_CLASS)
@Slf4j
public class PersonFavorites {
    
    private final Set<Long> favoritePersonIds = new HashSet<>();
    
    public PersonFavorites() {
        log.info("🌟 PersonFavorites Bean erstellt für neue Session");
    }
    
    public void addFavorite(Long personId) {
        favoritePersonIds.add(personId);
        log.info("⭐ Person {} zu Favoriten hinzugefügt", personId);
    }
    
    public void removeFavorite(Long personId) {
        favoritePersonIds.remove(personId);
        log.info("☆ Person {} aus Favoriten entfernt", personId);
    }
    
    public boolean isFavorite(Long personId) {
        return favoritePersonIds.contains(personId);
    }
    
    public Set<Long> getAllFavorites() {
        return new HashSet<>(favoritePersonIds);
    }
    
    public int getFavoriteCount() {
        return favoritePersonIds.size();
    }
}
