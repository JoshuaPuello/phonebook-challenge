FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 8083
RUN mkdir -p /app/
RUN mkdir -p /app/logs/
ADD target/phonebook-0.0.1-SNAPSHOT.jar /app/app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom", "-jar", "/app/app.jar"]
