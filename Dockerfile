FROM eclipse-temurin:17-jdk-alpine

COPY target/parkinglot-0.0.1-SNAPSHOT.jar parking-lot.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/parking-lot.jar"]