# STAGE 1: Build the application
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean install -DskipTests

# STAGE 2: Run the application
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build /app/target/tasks-0.0.1-SNAPSHOT.jar ./tasks.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "tasks.jar"]