# Usar una imagen base con Java 17
FROM eclipse-temurin:17-jre

# Directorio donde se colocará la aplicación en el contenedor
WORKDIR /app

# Copiar el archivo JAR del proyecto al directorio /app en el contenedor
COPY build/libs/*.jar app.jar

# Exponer el puerto que usa la aplicacion
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]

