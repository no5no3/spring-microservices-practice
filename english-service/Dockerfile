FROM openjdk:8-jdk-alpine
VOLUME /tmp/english
ADD ./build/libs/app-0.0.1.jar /english-service.jar
ENTRYPOINT ["java","-jar","/english-service.jar","--spring.profiles.active=prod"]
#CMD ["-c"] #this will add to ENTRYPOINT, will be overridden when running the container with alternative arguments
EXPOSE 8082