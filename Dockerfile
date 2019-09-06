FROM java:8-alpine
MAINTAINER Your Name <you@example.com>

ADD target/dinizeitor-0.0.1-SNAPSHOT-standalone.jar /dinizeitor/app.jar

EXPOSE 8080

CMD ["java", "-jar", "/dinizeitor/app.jar"]
