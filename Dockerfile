# STAGE 1: Build (Isme Render khud Maven install karega)
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
# Saari files copy karo
COPY . .
# Render ke andar hi target folder banega
RUN mvn clean package -DskipTests

# STAGE 2: Run (Sirf bani hui JAR file uthao)
FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app
# Pehle stage se JAR file yahan copy karo
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
