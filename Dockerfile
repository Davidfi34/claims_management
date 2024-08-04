# Usa Amazon Corretto JDK 17 en una imagen Alpine como imagen base
FROM amazoncorretto:17-alpine-jdk

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el archivo JAR generado al contenedor
COPY target/claims_services-0.0.1-SNAPSHOT.jar app.jar

# Exponer el puerto en el que la aplicación escucha (por defecto 8080)
EXPOSE 8080

# Define el punto de entrada para ejecutar la aplicación
ENTRYPOINT ["java","-jar","/app/app.jar"]

