ARG REPO=xxxdockerhub.xxx.com:9527
ARG JRE=$REPO/emis/java/openjdk:8u212-alpine-4
FROM $JRE
WORKDIR /app
COPY target/*.jar demo.jar
ENTRYPOINT ["java", "-jar","demo.jar"]