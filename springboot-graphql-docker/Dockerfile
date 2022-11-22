FROM openjdk:11
# Port exposed to outside container
EXPOSE 8181
ADD target/springboot-graphql-docker.jar springboot-graphql-docker.jar
ENTRYPOINT ["java","-jar","/springboot-graphql-docker.jar"]
