# Spring Boot with Docker
What is docker?\
(for more details click  [here](https://docs.docker.com/get-started/overview/))\
### Setup Frist Spring Boot Docker Project
1. Create Spring boot application.
2. Add Dockerfile in the root directory. Specify the required properties in docker file(for more details about dockerfile click  [here](https://docs.docker.com/engine/reference/builder/
))\ 
3. Create docker image. To create docker image follow below steps.
   * Open command prompt
   * Navigate to your project root directory whrere dockerfile is present
   * run docker build -t springboot-graphql-docker.jar[jar name] .[root directory]
   * Verify the docker image using command docker image ls, your image should be there.
 4. Run docker image using command  docker run -p 9090:8181 springboot-graphql-docker.jar
 5. since we have used mysql with our spring boot application so application will not run. First we need to create docker network for mysql database. To create Docker network follow below steps.
  * Run command docker pull mysql:5.7
  * create docker network for spring boot application to communicate with mysql database. Here is the command to create docker network docker network create               springboot-mysql-net
  * Run the mysql container in the network. Use below command to run it.
    docker run -it --name mysqldb --network=springboot-mysql-net -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=graphqltest -e MYSQL_USER=sysuser -e MYSQL_PASSWORD=root     -d mysql:5.7
  * Now lets verify if mysql is running. docker exec -it 679d9abd108ce0a5436c707d90483201e1f2b1c54398896b16c55ae568ed12f1 bash
    mysql -u sysuser -p graphqltest .
    show database
    
    
  
     
