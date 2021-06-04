FROM maven:3-jdk-8-alpine

COPY ./zby-web/zby-web-1.0-SNAPSHOT.jar zby-eureka-client.jar
VOLUME /tmp
ENV PORT 4455
EXPOSE $PORT
CMD [ "java", "-Dserver.port=${PORT}","-jar","zby-eureka-client.jar" ]
