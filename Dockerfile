# ---- Build stage: compile and package the app with Maven + JDK 17 ----
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
# Pre-download dependencies (cached layer) for faster rebuilds.
RUN mvn -B dependency:go-offline
COPY src ./src
RUN mvn -B clean package -DskipTests

# ---- Run stage: small JRE image that just runs the jar ----
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 7478
ENTRYPOINT ["java", "-jar", "app.jar"]
