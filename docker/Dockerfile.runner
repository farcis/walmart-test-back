FROM openjdk:8-jdk-alpine

MAINTAINER Francisco Arcis "framcisco.arcis.s@gmail.com"

VOLUME /tmp

EXPOSE 8080

ARG JAR_FILE=build/libs/walmart-test-back-0.0.1-SNAPSHOT.jar

ADD ${JAR_FILE} app.jar

ENTRYPOINT ["java","-Dserver.port=$PORT","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]