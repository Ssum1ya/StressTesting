# Этап 1: Сборка приложения
FROM gradle:jdk26 AS builder
WORKDIR /app

# Копируем gradle файлы для кэширования зависимостей
COPY build.gradle* ./
COPY settings.gradle* ./
RUN gradle dependencies --no-daemon

# Копируем исходный код и собираем JAR
COPY src ./src
RUN gradle bootJar --no-daemon

# Этап 2: Запуск приложения
FROM eclipse-temurin:26-jre-jammy
WORKDIR /app

# Копируем собранный JAR из этапа сборки
COPY --from=builder /app/build/libs/*.jar app.jar
COPY --from=builder /app/src/main/resources/application.properties application.properties


EXPOSE 8081
CMD ["java", "-jar", "app.jar", "--spring.config.location=application.properties"]