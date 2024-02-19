FROM eclipse-temurin:17-jdk-alpine
# COPY /app/GeoMapping-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app/GeoMapping-0.0.1-SNAPSHOT.jar"]
