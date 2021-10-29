FROM openjdk:15-alpine
ARG JAR_FILE=target/restDemo-1.1.0-SNAPSHOT.jar
COPY ${JAR_FILE} /usr/share/app.jar
ENTRYPOINT ["java","-jar","/usr/share/app.jar"]