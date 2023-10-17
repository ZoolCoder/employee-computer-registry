# Use the official OpenJDK base image
FROM openjdk:17-slim

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file into the container
COPY target/employee-device-registry.jar .

# Expose the port your Spring Boot app is listening on
EXPOSE 8080

# Command to run your application
CMD ["java", "-jar", "employee-computer-registry.jar"]