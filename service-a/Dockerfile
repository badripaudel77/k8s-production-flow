# Dockerfile for Service A
FROM eclipse-temurin:21-jdk-alpine AS build
WORKDIR /app
COPY . /app
RUN ./mvnw dependency:go-offline -B || true
RUN ./mvnw package -DskipTests || true

FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/service-a-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
