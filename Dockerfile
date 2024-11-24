# Use the official OpenJDK image as the base image
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the built jar file into the container
COPY target/product-api-0.0.1-SNAPSHOT.jar app.jar

# Expose the application's port
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
