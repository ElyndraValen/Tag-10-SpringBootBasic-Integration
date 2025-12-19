# 📝 Spring Boot Basic - Cheat Sheet

**Alle wichtigen Konzepte aus 10 Tagen auf einen Blick!**

---

## 🏷️ Annotations Übersicht

### Spring Core Annotations

```java
@SpringBootApplication  // Main Application Class
@Component             // Generic Spring Bean
@Service              // Business Logic Layer
@Repository           // Data Access Layer (Aufbau-Kurs)
@Controller           // MVC Controller (returns view name)
@RestController       // REST API Controller (returns data)
```

### Dependency Injection

```java
@Autowired            // Field/Constructor/Setter Injection
@RequiredArgsConstructor  // Lombok - Constructor Injection
@Qualifier("name")    // Bean Selection by name
```

### Jakarta EE Lifecycle

```java
@PostConstruct        // After Bean creation & DI
@PreDestroy          // Before Bean destruction
@Resource            // Jakarta EE DI (alternative zu @Autowired)
@Inject              // Jakarta EE DI (alternative zu @Autowired)
```

### Web MVC

```java
@RequestMapping("/path")  // Map URL to method/class
@GetMapping              // HTTP GET
@PostMapping             // HTTP POST
@PutMapping              // HTTP PUT
@DeleteMapping           // HTTP DELETE
@PathVariable           // URL Path Variable
@RequestParam           // Query Parameter
@RequestBody            // Request Body (JSON)
@ModelAttribute         // Form Data Binding
```

### JAX-RS (Jakarta EE)

```java
@Path("/resource")    // Resource Path
@GET                 // HTTP GET
@POST                // HTTP POST
@PUT                 // HTTP PUT
@DELETE              // HTTP DELETE
@PathParam("id")     // Path Parameter
@QueryParam("name")  // Query Parameter
@Produces           // Response Media Type
@Consumes           // Request Media Type
```

### Bean Scopes

```java
@Scope("singleton")   // Default - Eine Instanz
@Scope("prototype")   // Neue Instanz bei jedem Request
@Scope("request")     // Eine Instanz pro HTTP Request
@Scope("session")     // Eine Instanz pro HTTP Session
@Scope("application") // Eine Instanz pro ServletContext
```

### AOP (Aspect-Oriented Programming)

```java
@Aspect              // Aspect Definition
@Before             // Before Method Execution
@After              // After Method Execution
@Around             // Around Method Execution
@AfterReturning     // After Successful Return
@AfterThrowing      // After Exception
```

---

## 📦 Bean Lifecycle

```
1. Constructor Call
         ↓
2. Dependency Injection
         ↓
3. @PostConstruct
         ↓
4. Bean Ready for Use
         ↓
5. @PreDestroy
         ↓
6. Bean Destroyed
```

---

## 🏗️ Layer Architecture

```
┌────────────────────────────────────┐
│   PRESENTATION LAYER               │
│   @RestController / @Controller    │
│   - HTTP Requests                  │
│   - Response Formatting            │
└────────────────────────────────────┘
              ↕
┌────────────────────────────────────┐
│   BUSINESS LOGIC LAYER             │
│   @Service                         │
│   - Business Rules                 │
│   - Transactions                   │
│   - Validation                     │
└────────────────────────────────────┘
              ↕
┌────────────────────────────────────┐
│   DATA ACCESS LAYER                │
│   @Repository                      │
│   - Database Access                │
│   - CRUD Operations                │
└────────────────────────────────────┘
```

---

## 🎯 Best Practices

### ✅ DO

```java
// Constructor Injection (Best Practice!)
@Service
@RequiredArgsConstructor
public class PersonService {
    private final PersonRepository repo;
}

// Singleton Services (stateless!)
@Service  // Default: Singleton
public class PersonService { }

// Jakarta EE Standards nutzen
@PostConstruct
public void init() { }

// Layer-Trennung einhalten
@RestController  // Presentation
@Service        // Business Logic
@Repository     // Data Access
```

### ❌ DON'T

```java
// Field Injection vermeiden
@Service
public class PersonService {
    @Autowired
    private PersonRepository repo; // Schlecht!
}

// Services mit State vermeiden
@Service
public class PersonService {
    private List<Person> cache; // Gefährlich in Singleton!
}

// Falsche Scopes
@Service
@Scope("session") // ANTI-PATTERN!
public class PersonService { }
```

---

## 📝 Configuration Properties

```properties
# Server
server.port=8080
server.servlet.context-path=/app

# Logging
logging.level.root=INFO
logging.level.com.example=DEBUG
logging.file.name=app.log

# Thymeleaf
spring.thymeleaf.cache=false
spring.thymeleaf.prefix=classpath:/templates/

# DevTools
spring.devtools.restart.enabled=true

# Custom Properties
app.name=My Application
app.version=1.0.0
```

---

## 🔌 REST API Patterns

### Spring MVC

```java
@RestController
@RequestMapping("/api/persons")
public class PersonController {
    
    @GetMapping
    public List<Person> getAll() { }
    
    @GetMapping("/{id}")
    public ResponseEntity<Person> getById(@PathVariable Long id) { }
    
    @PostMapping
    public ResponseEntity<Person> create(@RequestBody Person person) { }
    
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, 
                                       @RequestBody Person person) { }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) { }
}
```

### JAX-RS

```java
@Component
@Path("/persons")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonResource {
    
    @GET
    public Response getAll() { }
    
    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Long id) { }
    
    @POST
    public Response create(Person person) { }
    
    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, Person person) { }
    
    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) { }
}
```

---

## 🌐 Thymeleaf Basics

### Controller

```java
@Controller
@RequestMapping("/persons")
public class PersonViewController {
    
    @GetMapping
    public String list(Model model) {
        model.addAttribute("persons", service.getAll());
        return "persons"; // returns persons.html
    }
    
    @PostMapping("/add")
    public String add(@ModelAttribute Person person) {
        service.create(person);
        return "redirect:/persons"; // PRG Pattern!
    }
}
```

### Template (Thymeleaf)

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<body>
    <!-- Iteration -->
    <tr th:each="person : ${persons}">
        <td th:text="${person.firstname}">Max</td>
        <td th:text="${person.lastname}">Mustermann</td>
    </tr>
    
    <!-- Form Binding -->
    <form th:action="@{/persons/add}" th:object="${person}" method="post">
        <input type="text" th:field="*{firstname}" />
        <input type="text" th:field="*{lastname}" />
        <button type="submit">Save</button>
    </form>
    
    <!-- Conditionals -->
    <div th:if="${persons.isEmpty()}">
        No persons found.
    </div>
    
    <!-- Links -->
    <a th:href="@{/persons/{id}(id=${person.id})}">View</a>
</body>
</html>
```

---

## 🔄 AOP Example

```java
@Aspect
@Component
@Slf4j
public class LoggingAspect {
    
    // Log all service methods
    @Around("execution(* com.example.service..*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) 
            throws Throwable {
        long start = System.currentTimeMillis();
        
        Object result = joinPoint.proceed();
        
        long duration = System.currentTimeMillis() - start;
        log.info("{} took {} ms", 
            joinPoint.getSignature(), duration);
        
        return result;
    }
}
```

---

## 🌐 WebSocket (STOMP)

### Configuration

```java
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig 
        implements WebSocketMessageBrokerConfigurer {
    
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");
        config.setApplicationDestinationPrefixes("/app");
    }
    
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws").withSockJS();
    }
}
```

### Controller

```java
@Controller
public class ChatController {
    
    @MessageMapping("/chat.send")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage message) {
        return message;
    }
}
```

---

## 📊 Bean Scopes im Detail

| Scope | Beschreibung | Use Case |
|-------|-------------|----------|
| **singleton** | Eine Instanz für gesamte App | Services, Repositories |
| **prototype** | Neue Instanz bei jedem Request | Komplexe Objects |
| **request** | Eine Instanz pro HTTP Request | Request-spezifische Daten |
| **session** | Eine Instanz pro HTTP Session | User-spezifische Daten |
| **application** | Eine Instanz pro ServletContext | Application-weite Daten |

---

## 🎯 Migration Cheat Sheet

### Jakarta EE → Spring Boot

```java
// Jakarta EE (WildFly/GlassFish)
@Stateless
public class PersonService {
    @PostConstruct
    public void init() { }
}

// Spring Boot (minimal changes!)
@Service
public class PersonService {
    @PostConstruct  // Bleibt gleich!
    public void init() { }
}
```

### JAX-RS → Spring Boot

```java
// JAX-RS in Jakarta EE
@Path("/persons")
public class PersonResource {
    @GET
    public List<Person> getAll() { }
}

// JAX-RS in Spring Boot (Code bleibt!)
@Component  // Nur diese Zeile hinzufügen!
@Path("/persons")
public class PersonResource {
    @GET
    public List<Person> getAll() { }
}
```

---

## 🚀 Maven Commands

```bash
# Build Project
mvn clean install

# Run Application
mvn spring-boot:run

# Package as JAR
mvn package

# Skip Tests
mvn clean install -DskipTests

# Run specific Profile
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

---

## 📚 Wichtige Konzepte

### Dependency Injection (DI)

- **Constructor Injection**: Best Practice ✅
- **Field Injection**: Vermeiden ❌
- **Setter Injection**: Selten nötig

### Inversion of Control (IoC)

- Framework erstellt und verwaltet Beans
- Du definierst nur Dependencies
- Spring Container übernimmt Rest

### Aspect-Oriented Programming (AOP)

- Cross-Cutting Concerns (Logging, Security, Transactions)
- Separierung von Business Logic
- Wiederverwendbarer Code

---

## 🎓 Die 10 Tage im Überblick

| Tag | Thema | Key Concepts |
|-----|-------|--------------|
| 1 | REST API | @RestController, HTTP, JSON |
| 2 | Spring Container & DI | IoC, @Service, @PostConstruct |
| 3 | Thymeleaf | Template Engine, th:* |
| 4 | Forms & MVC | Model-View-Controller, PRG |
| 5 | Configuration & Logging | application.properties, SLF4J |
| 6 | DI & AOP | @Aspect, Cross-Cutting Concerns |
| 7 | Scopes | Singleton, Session, Request |
| 8 | WebSockets | STOMP, Real-Time |
| 9 | JAX-RS | Jakarta EE Standards |
| 10 | Integration | Alles zusammen! |

---

**Keep this sheet handy!** 📌

*Von Java Fleet Systems Consulting*
