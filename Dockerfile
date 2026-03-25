FROM eclipse-temurin:21-jdk

MAINTAINER production

COPY target/Micro1-0.0.1-SNAPSHOT.jar Micro1-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","Micro1-0.0.1-SNAPSHOT.jar"]