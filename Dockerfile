FROM openjdk:17-jdk-slim-buster
RUN gradle build --no-daemon || return 0
ARG JAR_FILE=build/libs/clevertec-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-Dspring.profiles.active=docker", "-Dfile.encoding=UTF-8", "-Djava.security.egd=file:/dev/./urandom", "-Xmx2g", "-Xms2g", "-jar", "app.jar"]
