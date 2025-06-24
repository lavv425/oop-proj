# Maven build
FROM maven:3.9.6-eclipse-temurin AS builder

WORKDIR /app

# Projects file copy
COPY pom.xml .
COPY src ./src

# Build the application JAR
RUN mvn clean package -DskipTests

# Final image with JRE
FROM eclipse-temurin:23-jre

WORKDIR /app

# Copy the JAR file from the builder stage
COPY --from=builder /app/target/devtrack-1.0.0.jar app.jar

# Go
ENTRYPOINT ["java", "-jar", "app.jar"]