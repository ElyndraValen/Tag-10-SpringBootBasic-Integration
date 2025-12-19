package com.javafleet.tag10.controller;

import com.javafleet.tag10.model.Person;
import com.javafleet.tag10.service.PersonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * PersonApiController - REST API mit Spring MVC
 * 
 * Demonstriert:
 * - @RestController (Tag 1)
 * - Constructor Injection (Tag 6 - Best Practice!)
 * - HTTP Methods (GET, POST, PUT, DELETE)
 * - ResponseEntity für flexible Responses
 * 
 * @author Elyndra Valen
 */
@RestController
@RequestMapping("/api/persons")
@RequiredArgsConstructor
@Slf4j
public class PersonApiController {
    
    // Constructor Injection - Best Practice!
    private final PersonService personService;
    
    /**
     * GET /api/persons
     * Gibt alle Personen als JSON zurück
     */
    @GetMapping
    public List<Person> getAllPersons() {
        log.info("🔍 GET /api/persons - Spring MVC");
        return personService.getAllPersons();
    }
    
    /**
     * GET /api/persons/{id}
     * Gibt eine spezifische Person zurück
     */
    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable Long id) {
        log.info("🔍 GET /api/persons/{} - Spring MVC", id);
        return personService.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
    
    /**
     * POST /api/persons
     * Erstellt eine neue Person
     */
    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {
        log.info("➕ POST /api/persons - Spring MVC: {}", person.getFullName());
        Person created = personService.createPerson(person);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
    
    /**
     * PUT /api/persons/{id}
     * Aktualisiert eine Person
     */
    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePerson(@PathVariable Long id, @RequestBody Person person) {
        log.info("✏️ PUT /api/persons/{} - Spring MVC", id);
        boolean updated = personService.updatePerson(id, person);
        return updated ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
    
    /**
     * DELETE /api/persons/{id}
     * Löscht eine Person
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable Long id) {
        log.info("🗑️ DELETE /api/persons/{} - Spring MVC", id);
        boolean deleted = personService.deletePerson(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
