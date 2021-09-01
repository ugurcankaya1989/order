FROM openjdk:13-jdk-alpine
VOLUME /tmp
COPY target/*.jar order.jar
EXPOSE 8082
ENTRYPOINT ["java","-jar","/order.jar"]