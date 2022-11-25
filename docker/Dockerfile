FROM openjdk:17-jdk-slim-buster
ARG JAR_FILE=/build/libs/*.jar
COPY ${JAR_FILE} app.jar
COPY "birthday.csv" "birthday.csv"

ENTRYPOINT ["java","-jar","/app.jar"]
