FROM openjdk:17-alpine
# Port exposed to outside container
#EXPOSE 8181  useful if docker compose is not being used
ADD target/springboot-graphql.jar springboot-graphql-app.jar
ENTRYPOINT ["java","-jar","/springboot-graphql-app.jar"]
