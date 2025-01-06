# Stage 1: Build the application
FROM gradle:8.10-jdk21 AS builder

# Set working directory
WORKDIR /app

# Copy the keystore file into the container
COPY src/main/resources/mykeystore.p12 /app/resources/mykeystore.p12


# Copy Gradle wrapper files
COPY build.gradle settings.gradle /app/
COPY gradle /app/gradle

# Pre-download dependencies
RUN gradle build --no-daemon --parallel || true

# Copy the entire project
COPY . /app

# Build the JAR file
RUN gradle bootJar --no-daemon

# Stage 2: Create the runtime image
FROM openjdk:21-jdk-slim

# Set working directory
WORKDIR /app

# Copy the JAR file from the builder stage
COPY --from=builder /app/build/libs/*.jar app.jar

# Expose the port your application uses
EXPOSE 8443

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
