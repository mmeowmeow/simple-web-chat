# Stage 1: Build the application
FROM maven:3.9.4-eclipse-temurin-17-alpine AS build

# Set the working directory in the container
WORKDIR /build

# Copy the pom.xml file to the container
COPY pom.xml .

# Download the dependencies
RUN mvn dependency:go-offline -B

# Copy the source code to the container
COPY src ./src

# Build the application with Maven
RUN mvn package -DskipTests

# Stage 2: Create the runtime image
FROM eclipse-temurin:17-jre-alpine AS runtime

# Set the working directory in the container
WORKDIR /app

# Copy the built JAR file from the previous stage
COPY --from=build /build/target/*.jar chat.jar

EXPOSE 8080
# Set the entry point for the container
ENTRYPOINT ["java", "-jar", "chat.jar"]
