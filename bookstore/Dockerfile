FROM openjdk:17-jdk-slim
COPY target/bookstore-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
# Expose the port the app runs on
# EXPOSE 8080     