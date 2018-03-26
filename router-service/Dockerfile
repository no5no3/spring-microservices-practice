FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD ./build/libs/app-0.0.1.jar /router-service.jar
ENTRYPOINT ["java","-jar","/router-service.jar","--spring.profiles.active=prod"]
#CMD ["-c"] #this will add to ENTRYPOINT, will be overridden when running the container with alternative arguments
EXPOSE 8083