FROM maven:3-amazoncorretto-21 AS build

COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package -DskipTests

FROM amazoncorretto:21-alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ARG JAR_FILE=target/*.jar
COPY --from=build /home/app/target/*.jar app.jar

RUN ls

ENTRYPOINT ["java","-jar","/app.jar"]