# Usamos imagen base ligera con JDK 17
FROM openjdk:21-jdk-slim

# Copiamos cualquier archivo .jar del directorio target y lo renombramos dentro del contenedor como app.jar
COPY target/*.jar /app/app.jar

# Exponemos el puerto por defecto 8080 (puedes cambiarlo si usas otro puerto)
EXPOSE 8086

# Comando para ejecutar el .jar
ENTRYPOINT ["java", "-jar", "/app/app.jar"]