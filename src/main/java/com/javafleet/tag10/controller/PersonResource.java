package com.javafleet.tag10.controller;

import com.javafleet.tag10.model.Person;
import com.javafleet.tag10.service.PersonService;
import jakarta.annotation.PostConstruct;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * PersonResource - REST API mit JAX-RS (Jakarta EE Standard)
 * 
 * Demonstriert:
 * - JAX-RS Annotations (@Path, @GET, @POST, etc.) (Tag 9)
 * - Jakarta EE @PostConstruct (Tag 2 + 9 Zusammenführung!)
 * - Migration-Readiness (Code funktioniert auch auf WildFly!)
 * - Spring Boot 3.x + Jakarta EE Integration
 * 
 * DER GAMECHANGER: Minimale Migration-Kosten von Legacy zu Modern!
 * 
 * @author Elyndra Valen
 */
@Component
@Path("/jaxrs/persons")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequiredArgsConstructor
@Slf4j
public class PersonResource {
    
    private final PersonService personService;
    
    /**
     * Jakarta EE Lifecycle Callback
     * 
     * WICHTIG: Dies ist DER Moment wo Tag 2 und Tag 9 zusammenkommen!
     * @PostConstruct funktioniert in:
     * - Spring Boot Services (Tag 2)
     * - JAX-RS Resources (Tag 9)
     * - WildFly Applications
     * - GlassFish Applications
     * 
     * = STANDARD = PORTABILITÄT!
     */
    @PostConstruct
    public void init() {
        log.info("🚀 PersonResource (JAX-RS) initialized!");
        log.info("💡 Jakarta EE @PostConstruct in JAX-RS - Tag 2 + Tag 9 vereint!");
        log.info("🔄 Dieser Code funktioniert auch auf WildFly/GlassFish!");
    }
    
    /**
     * GET /jaxrs/persons
     */
    @GET
    public Response getAllPersons() {
        log.info("🔍 GET /jaxrs/persons - JAX-RS (Jakarta EE)");
        List<Person> persons = personService.getAllPersons();
        return Response.ok(persons).build();
    }
    
    /**
     * GET /jaxrs/persons/{id}
     */
    @GET
    @Path("/{id}")
    public Response getPersonById(@PathParam("id") Long id) {
        log.info("🔍 GET /jaxrs/persons/{} - JAX-RS", id);
        return personService.findById(id)
            .map(person -> Response.ok(person).build())
            .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }
    
    /**
     * POST /jaxrs/persons
     */
    @POST
    public Response createPerson(Person person) {
        log.info("➕ POST /jaxrs/persons - JAX-RS: {}", person.getFullName());
        Person created = personService.createPerson(person);
        return Response.status(Response.Status.CREATED).entity(created).build();
    }
    
    /**
     * PUT /jaxrs/persons/{id}
     */
    @PUT
    @Path("/{id}")
    public Response updatePerson(@PathParam("id") Long id, Person person) {
        log.info("✏️ PUT /jaxrs/persons/{} - JAX-RS", id);
        boolean updated = personService.updatePerson(id, person);
        return updated 
            ? Response.ok().build() 
            : Response.status(Response.Status.NOT_FOUND).build();
    }
    
    /**
     * DELETE /jaxrs/persons/{id}
     */
    @DELETE
    @Path("/{id}")
    public Response deletePerson(@PathParam("id") Long id) {
        log.info("🗑️ DELETE /jaxrs/persons/{} - JAX-RS", id);
        boolean deleted = personService.deletePerson(id);
        return deleted 
            ? Response.noContent().build() 
            : Response.status(Response.Status.NOT_FOUND).build();
    }
}
