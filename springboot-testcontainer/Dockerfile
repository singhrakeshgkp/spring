FROM openjdk:17-alpine
ADD target/springboot-testcontainer.jar springboot-testcontainer.jar
EXPOSE 8888
ENTRYPOINT ["java", "-jar", "springboot-testcontainer.jar"]