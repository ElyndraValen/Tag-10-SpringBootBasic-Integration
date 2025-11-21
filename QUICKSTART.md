# ⚡ QUICKSTART - Spring Boot Basic Tag 10

**In 5 Minuten lauffähig!**

---

## 🚀 Schnellstart

### 1. Projekt starten

```bash
cd SpringBootBasic-Tag10
mvn spring-boot:run
```

✅ Application läuft auf: `http://localhost:8080`

### 2. Web-Interface öffnen

```
http://localhost:8080/persons
```

➡️ Hier kannst du Personen anlegen, ansehen und löschen!

---

## 🔌 API testen

### Spring MVC REST API

```bash
# Alle Personen abrufen
curl http://localhost:8080/api/persons

# Neue Person erstellen
curl -X POST http://localhost:8080/api/persons \
  -H "Content-Type: application/json" \
  -d '{"firstname":"Max","lastname":"Mustermann"}'

# Person mit ID 1 abrufen
curl http://localhost:8080/api/persons/1
```

### JAX-RS REST API (Jakarta EE Standard)

```bash
# Alle Personen abrufen (JAX-RS)
curl http://localhost:8080/api/jaxrs/persons

# Neue Person erstellen (JAX-RS)
curl -X POST http://localhost:8080/api/jaxrs/persons \
  -H "Content-Type: application/json" \
  -d '{"firstname":"Anna","lastname":"Schmidt"}'
```

---

## 📋 Wichtige Endpoints

| URL | Beschreibung |
|-----|--------------|
| `http://localhost:8080/persons` | Web-Interface (Thymeleaf) |
| `http://localhost:8080/api/persons` | REST API (Spring MVC) |
| `http://localhost:8080/api/jaxrs/persons` | REST API (JAX-RS) |

---

## 🛠️ Troubleshooting

### Port 8080 bereits belegt?

```bash
# In application.properties ändern:
server.port=8081
```

### Maven Build Fehler?

```bash
mvn clean install
```

### IDE Problems?

```bash
# Maven reimport
mvn clean
mvn install
# Projekt in IDE neu importieren
```

---

## 💡 Was du siehst

Beim Start der Application siehst du im Log:

```
╔═══════════════════════════════════════════════════════════╗
║   🎉 SPRING BOOT BASIC - TAG 10: INTEGRATION & ABSCHLUSS  ║
║                                                           ║
║   🚀 Application gestartet auf http://localhost:8080     ║
║                                                           ║
║   📚 Alle 9 Tage zusammengeführt:                        ║
║   ✅ REST API (Spring MVC + JAX-RS)                      ║
║   ✅ Thymeleaf Views & Forms                             ║
║   ✅ Dependency Injection & AOP                          ║
║   ✅ Bean Scopes & Lifecycle                             ║
║   ✅ WebSockets (Low-Level + STOMP)                      ║
║   ✅ Configuration & Logging                             ║
║   ✅ Jakarta EE Standards Integration                    ║
║                                                           ║
║   💪 DU HAST ES GESCHAFFT!                               ║
╚═══════════════════════════════════════════════════════════╝
```

---

## 📚 Nächste Schritte

1. ✅ Projekt gestartet
2. ✅ Web-Interface getestet
3. ✅ APIs mit cURL getestet

**Jetzt:** Lies das vollständige [README.md](README.md) für Details!

---

**Happy Coding!** 🚀

*Von Elyndra Valen & Java Fleet Systems*
