# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk

# Set the working directory
WORKDIR /app

# Copy the JAR file into the container
COPY target/web_lab1-1.0-SNAPSHOT-jar-with-dependencies.jar app.jar

# Expose the port the app runs on
EXPOSE 1212

# Run the application
ENTRYPOINT ["java", "-DFCGI_PORT=14123", "-jar", "app.jar"]