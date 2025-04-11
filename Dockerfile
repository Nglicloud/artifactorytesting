# Use a lightweight Java image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy jar file to image
COPY target/jd-1.0.0.jar app.jar

# Command to run jar
ENTRYPOINT ["java", "-jar", "app.jar"]
