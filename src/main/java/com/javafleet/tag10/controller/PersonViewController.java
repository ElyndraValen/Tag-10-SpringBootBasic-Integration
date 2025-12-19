package com.javafleet.tag10.controller;

import com.javafleet.tag10.model.Person;
import com.javafleet.tag10.service.PersonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * PersonViewController - MVC Controller mit Thymeleaf
 * 
 * Demonstriert:
 * - @Controller (Tag 3)
 * - Model-View-Controller Pattern (Tag 4)
 * - Thymeleaf Template Integration (Tag 3)
 * - Post-Redirect-Get Pattern (Tag 4)
 * - Form Handling (Tag 4)
 * 
 * @author Elyndra Valen
 */
@Controller
@RequestMapping("/persons")
@RequiredArgsConstructor
@Slf4j
public class PersonViewController {
    
    private final PersonService personService;
    
    /**
     * GET /persons
     * Zeigt Liste aller Personen
     */
    @GetMapping(path = "/")
    public String listPersons(Model model) {
        log.info("📄 GET /persons - Thymeleaf View");
        model.addAttribute("persons", personService.getAllPersons());
        model.addAttribute("newPerson", new Person());
        model.addAttribute("totalCount", personService.getCount());
        return "persons";
    }
    
    /**
     * POST /persons/add
     * Erstellt neue Person und redirected
     * 
     * Post-Redirect-Get Pattern (Tag 4)
     */
    @PostMapping("/add")
    public String addPerson(@ModelAttribute Person person) {
        log.info("➕ POST /persons/add - Form Submit: {}", person.getFullName());
        personService.createPerson(person);
        return "redirect:/persons"; // PRG Pattern!
    }
    
    /**
     * GET /persons/delete/{id}
     * Löscht Person und redirected
     */
    @GetMapping("/delete/{id}")
    public String deletePerson(@PathVariable Long id) {
        log.info("🗑️ GET /persons/delete/{}", id);
        personService.deletePerson(id);
        return "redirect:/persons";
    }
}
