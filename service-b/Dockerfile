# Dockerfile for Service B
FROM eclipse-temurin:21-jdk-alpine AS build
WORKDIR /app
COPY . /app
RUN ./mvnw dependency:go-offline -B || true
RUN ./mvnw package -DskipTests || true

FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/service-b-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","app.jar"]
