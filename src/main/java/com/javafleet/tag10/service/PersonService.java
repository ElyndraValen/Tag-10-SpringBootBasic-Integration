package com.javafleet.tag10.service;

import com.javafleet.tag10.model.Person;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

/**
 * PersonService - Business Logic Layer
 * 
 * Demonstriert:
 * - @Service Annotation (Tag 2)
 * - Jakarta EE Lifecycle (@PostConstruct, @PreDestroy) (Tag 2, 9)
 * - Singleton Scope (Default, Tag 7)
 * - Logging Best Practices (Tag 5)
 * - AOP Target (Tag 6)
 * 
 * @author Elyndra Valen
 */
@Service
@Slf4j
public class PersonService {
    
    private final List<Person> persons = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong(1);
    
    /**
     * Jakarta EE Lifecycle Callback
     * Wird nach Dependency Injection aufgerufen
     */
    @PostConstruct
    public void init() {
        log.info("🚀 PersonService initialized!");
        log.info("📍 Scope: Singleton (Default)");
        log.info("💡 Jakarta EE @PostConstruct executed");
        
        // Initialisiere mit Test-Daten
        createPerson(new Person(null, "Elyndra", "Valen"));
        createPerson(new Person(null, "Nova", "Trent"));
        createPerson(new Person(null, "Franz-Martin", "Schmidt"));
        
        log.info("✅ {} Test-Personen initialisiert", persons.size());
    }
    
    /**
     * Jakarta EE Lifecycle Callback
     * Wird vor dem Zerstören der Bean aufgerufen
     */
    @PreDestroy
    public void cleanup() {
        log.info("🛑 PersonService wird zerstört...");
        log.info("📊 Finale Statistik: {} Personen im System", persons.size());
        log.info("💡 Jakarta EE @PreDestroy executed");
    }
    
    /**
     * Erstellt eine neue Person
     * 
     * @param person Person ohne ID
     * @return Person mit generierter ID
     */
    public Person createPerson(Person person) {
        person.setId(idCounter.getAndIncrement());
        persons.add(person);
        log.debug("✅ Person erstellt: {}", person.getFullName());
        return person;
    }
    
    /**
     * Gibt alle Personen zurück
     */
    public List<Person> getAllPersons() {
        log.debug("📋 {} Personen abgerufen", persons.size());
        return new ArrayList<>(persons);
    }
    
    /**
     * Findet eine Person anhand der ID
     */
    public Optional<Person> findById(Long id) {
        return persons.stream()
            .filter(p -> p.getId().equals(id))
            .findFirst();
    }
    
    /**
     * Aktualisiert eine Person
     */
    public boolean updatePerson(Long id, Person updatedPerson) {
        Optional<Person> existingPerson = findById(id);
        if (existingPerson.isPresent()) {
            Person person = existingPerson.get();
            person.setFirstname(updatedPerson.getFirstname());
            person.setLastname(updatedPerson.getLastname());
            log.info("✏️ Person aktualisiert: {}", person.getFullName());
            return true;
        }
        log.warn("⚠️ Person mit ID {} nicht gefunden", id);
        return false;
    }
    
    /**
     * Löscht eine Person
     */
    public boolean deletePerson(Long id) {
        boolean removed = persons.removeIf(p -> p.getId().equals(id));
        if (removed) {
            log.info("🗑️ Person mit ID {} gelöscht", id);
        } else {
            log.warn("⚠️ Person mit ID {} nicht gefunden", id);
        }
        return removed;
    }
    
    /**
     * Gibt die Anzahl der Personen zurück
     */
    public int getCount() {
        return persons.size();
    }
}
