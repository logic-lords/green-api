FROM maven:3.9.8-sapmachine-21 AS build

WORKDIR /app

COPY . .

RUN mvn -DskipTests package

FROM amazoncorretto:21-alpine-jdk

COPY --from=build /app/target/*.jar app.jar

EXPOSE  8080

ENTRYPOINT ["sh", "-c", "spring_datasource_url=${db_uri} spring_datasource_username=${db_user} spring_datasource_password=${db_pass} encryption_key=${key} java -jar app.jar"]