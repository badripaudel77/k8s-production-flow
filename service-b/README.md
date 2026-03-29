# Service B

A simple Spring Boot REST API that calls Service A.

## Endpoint
- `GET /combined` — calls Service A at `http://service-a:8080/message` and returns `Service B received: <message-from-A>`

## Build & Run
```
mvn spring-boot:run
```
