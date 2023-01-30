FROM maven:3.8.3-openjdk-17 as BUILD
WORKDIR /usr/src/app
COPY checks.xml .
COPY checkstyle_suppressions.xml .
COPY licenseheader.txt .
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src src
RUN mvn -B -e -C -T 1C package package -Dmaven.test.skip=true -Dcheckstyle.skip=true
# Start with a base image containing Java runtime
FROM openjdk:17-jdk-slim-buster
# Add Maintainer Info
LABEL maintainer="srojasm@grupoasd.com"
LABEL org.opencontainers.image.source https://github.com/srojasm-asd/Practica-ActivosFijos
# Add a volume pointing to /tmp
VOLUME /tmp
# Make port 9090 available to the world outside this container
EXPOSE 9090
COPY --from=BUILD /usr/src/app/target/*jar /opt/target/app.jar
WORKDIR /opt/target

ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar app.jar $JAR_OPTS
