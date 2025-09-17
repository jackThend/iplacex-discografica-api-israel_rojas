# stage 1: compilar la aplicacion usando gradle
FROM gradle:8.8.0-jdk21 AS build
WORKDIR /app

# copiar archivos del proyecto
COPY . .

# compilar el proyecto (usa gradle incluido en la imagen base)
RUN gradle build --no-daemon

# stage 2: ejecutar la aplicacion usando openjdk
FROM openjdk:21-slim
WORKDIR /app

# copiar el jar generado desde la etapa de build. usar comodin para evitar
# fijar el nombre o la version del artifact. el jar se renombreara a un nombre
# fijo para el runtime.
COPY --from=build /app/build/libs/*.jar ./app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "./app.jar"]