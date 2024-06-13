# Указываем базовый образ для Java
FROM openjdk:17

VOLUME /tmp

# Указываем, что будем использовать аргумент JAR_FILE
ARG JAR_FILE=target/*.jar

#WORKDIR /app

# Копируем JAR файл в контейнер и называем его app.jar
COPY ${JAR_FILE} app.jar

# Указываем команду для запуска нашего JAR файла
ENTRYPOINT ["java","-jar","/app.jar"]