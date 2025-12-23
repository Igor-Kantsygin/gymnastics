# Stage 1: Build jar
FROM maven:3.9.2-eclipse-temurin-17 AS build
WORKDIR /build

# Копируем только pom, чтобы кешировать зависимости
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Копируем исходники и собираем jar
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Runtime
FROM amazoncorretto:17
WORKDIR /app

# Копируем jar из предыдущего этапа
COPY --from=build /build/target/gymnastics-0.0.1-SNAPSHOT.jar app.jar

# Открываем порт
EXPOSE 8080

# Команда запуска
ENTRYPOINT ["java", "-jar", "app.jar"]
