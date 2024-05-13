## Build stage
FROM gradle:jdk21-jammy AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon

## Package stage
FROM openjdk:21-jdk-slim

ENV port 9200
ENV datasource.url 'jdbc:postgresql://host.docker.internal:5432/countries'
ENV datasource.user 'postgres'
ENV datasource.password 'postgres'
ENV mongo.host 'host.docker.internal'
ENV mongo.port '27017'
ENV mongo.database 'mongo'


RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/*.jar /app/country-api-1.0.0.jar
ENTRYPOINT ["java","-jar","/app/country-api-1.0.0.jar"]
