# Use a lightweight Java image
FROM openjdk:17-jdk-slim
# it is the test comment
# Set working directory
WORKDIR /app

# Copy jar file to image
COPY target/jd-1.0.0.jar app.jar
#test amend commit info
# Command to run jar
ENTRYPOINT ["java", "-jar", "app.jar"]
