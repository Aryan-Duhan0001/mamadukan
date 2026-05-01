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
ENTRYPOINT ["java", "-jar", "app.jar"]