FROM openjdk:21-jdk-slim
WORKDIR /app
COPY target/Persona-service-0.0.1-SNAPSHOT.jar app.jar
# Expone el puerto que usa el servicio (ajusta si es otro)
EXPOSE 8089
ENTRYPOINT ["java", "-jar", "app.jar"]
