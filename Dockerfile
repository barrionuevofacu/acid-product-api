FROM eclipse-temurin:17-jre-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} producto-api-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/producto-api-0.0.1-SNAPSHOT.jar"]