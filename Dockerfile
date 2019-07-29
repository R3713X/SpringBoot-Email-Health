FROM openjdk:8-jdk
MAINTAINER Yannis Kapsalas (kapsalas_gi@yahoo.gr)
ADD target/email-health-spring-boot.jar email-health-spring-boot.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "email-health-spring-boot.jar"]
