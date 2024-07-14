# Use an official Maven image as the base image
FROM maven:3.8-openjdk-17 AS build

# Set the working directory in the container
WORKDIR /app

# Copy the parent pom.xml file
COPY pom.xml .

# Copy the module pom.xml files
COPY calendly-service/pom.xml calendly-service/
COPY calendly-core/pom.xml calendly-core/

# Copy your other files
COPY calendly-service/src calendly-service/src
COPY calendly-core/src calendly-core/src

# Build the application
RUN mvn package -DskipTests

# Use OpenJDK for the final image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the built artifact from the build stage
COPY --from=build /app/calendly-service/target/calendly-service-1.0-SNAPSHOT.jar app.jar

# Expose port 8080
EXPOSE 8000

# Specify the command to run your application
ENTRYPOINT ["java", "-XX:+HeapDumpOnOutOfMemoryError", "-Xmx2048m", "-jar", "app.jar"]