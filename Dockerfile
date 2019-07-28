FROM openjdk:8-jdk
MAINTAINER Yannis Kapsalas (kapsalas_gi@yahoo.gr)
ADD target/email-health-spring-boot email-health-spring-boot
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "email-health-spring-boot"]