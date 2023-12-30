# Use the official OpenJDK base image with Java 17
FROM openjdk:latest

# Set the working directory to /app
WORKDIR /app

# Copy the JAR file into the container at /app
COPY practice-0.0.1-SNAPSHOT-plain.jar /app/practice.jar

# Expose the port that the application will run on
EXPOSE 8080

# Specify the command to run on container startup
CMD ["java", "-jar", "/app/practice.jar"]
ENTRYPOINT ["java", "-jar", "practice-0.0.1-SNAPSHOT-plain.jar"]