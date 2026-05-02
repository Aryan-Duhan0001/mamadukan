
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
=======
# Step 1: Java 17 ka base image (Chhota aur fast)
FROM eclipse-temurin:17-jdk-jammy

# Step 2: Working directory set karo
WORKDIR /app

# Step 3: Maven build ke baad jo .jar file banti hai use copy karo
# Dhyan rahe: 'target/*.jar' tabhi kaam karega jab aapne build chalaya ho
COPY target/*.jar app.jar

# Step 4: Port 8080 expose karo (Spring Boot ka default)
EXPOSE 8080

# Step 5: Application start karne ki command
>>>>>>> origin/master
ENTRYPOINT ["java", "-jar", "app.jar"]