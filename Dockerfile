FROM openjdk:17
LABEL key="Containerising Application"
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} API.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/springboot.jar"]