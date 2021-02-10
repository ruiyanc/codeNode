FROM openjdk:8u275-jre-slim
MAINTAINER yanrui yrui134@gamil.com

COPY target/demo-0.0.1-SNAPSHOT.jar /demo.jar

ENTRYPOINT ["java", "-jar", "/demo.jar"]