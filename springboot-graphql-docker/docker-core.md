#### Docker and Docker compose


<details>
<summary><b>Docker core</b></summary>
<p>
   
   - Docker Volume 
     - when we install and run our container such as mysql it stores the data under ``` /var/lib/mysql ``` dir
     - if we restart or remove docker or docker container, data will be lost(fiel/folder will be deleted).
     - To avoid this we should map data directory to host machine ``` home/mysql/data ...etc ```
     - command to map volume ``` docker run -d -p 3307:3306 --net test-net --name mysqldb --env-file envfilename -v "c:/abc":var/lib/mysql mysql mysqldb  ```
   
</p>
</details>
<details>
<summary><b>Docker file description</b></summary>
<p>
   
   - FROM openjdk:11    
     - base image which will contain jdk and OS
   - EXPOSE 8181
     - Port exposed to outside container
     
   - ADD target/*.jar app.jar
     - will take jar file from given path and add that to docker image and file name will be app.jar
  - ENTRYPOINT ["java","-jar","/app.jar"]
    - Entry point for the application   
</p>
</details>

<details>
<summary><b>Docker Command</b></summary>
<p>
   
   - ``` docker images ``` -> list down all the available images
   - ``` docker network ls ``` -> provide the available networks
   - ``` docker network ```  -> list down the available command for netowkr such as connect create.. etc
   - ``` docker network create test-network<network name> ``` -> create new network
   - ``` docker ps ``` -> shows list of running container
   - ``` docker network connect test-network<network name> mysqldb<image name> ``` -> connect the application with the given network
   - ``` docker container inspect mysqldb<image name> ``` -> show the image details
   - ``` docker rmi app<image name> ``` -> to remove image
   - ``` docker rmi mysql<image name> -f  ``` -> remove the container forcefully
   - ``` docker log app<docker image name> ``` -> show the logs
   - ``` docker restart app<image name>  ``` -> will restart the container
   
     docker run -p 9090:9090 --name app<image name> --net test-net<network name> -e MYSQL_HOST=mysqldb -e MYSQL_USER=root -e MYSQL_PASSWORD=tooroot -e MYSQL_PORT=3306 app<application image name i.e spring boot>
     ```
   - we can also refer env varriable details fom a file using command ``` docker run -p 9090:9090 --name app --net test-net --env-file env<file name> app ```
   - 
</p>
</details>


<details>
<summary><b>Docker compose file description</b></summary>
<p>

   -  ``` version ``` -> docker compose file version
   -  ```  services ``` -> we can specify n number of services. service name could be anything
   -  ``` image ``` -> image name that will be used to create docker container
   -  ``` ports: 8080:8080 ``` ->Internal and external port number
   -  ```  build: . ``` -> build using docker file availabel in current directory
   - ``` container-name: springboot-test ```-> it will give the specified container name. if this prop not present in dockercompose random name will be assigned
   - ``` restart: always ``` it will try to restart the application unitl it connect to other services on whihc it has dependency such as myslqdb etc.
   
</p>
</details>
   
   
<details>
<summary><b>Docker compose command</b></summary>
<p>

   - ``` docker-compose up ``` -> it will perform following task.
     - check if image is available in the local registry.
     - if image is not available in local registry, it will try to pull from remote.
     - if its not available in remote then it will throw error.
   -  ``` docker-compose up --build ``` -> it will first build and then run the application
   -  ``` docker-compose down  ``` -> will stop and remove container, network etc.
   - ``` docker-compose up --scale springboot-test<container name> = 5  ``` -> will create 5 container before this neeed to perform below steps
     - same command can be used to scale down as well by reducing the number in command. ie. 5 then give 3 in that case it will remove rest 2 container.
     - remove container-name prop from docker compose file as here we are creating multiple instance so name should be different of each container
     - ``` 7000-7100:8181 ``` specify the port range as each container should be running on different port
   -  ```  docker image prune -a ``` -> Remove all images without at least one container associated to them
   -  ```  docker system prune -a ``` -> Remove many more things
   
</p>
</details>

   
   <details>
<summary><b>Docker, Docker compose and application.properties sample files</b></summary>
<p>

   - Docker file
   
     ```
      FROM openjdk:17-alpine
      ADD target/springboot-testcontainer.jar springboot-testcontainer.jar
      EXPOSE 8888
      ENTRYPOINT ["java", "-jar", "springboot-testcontainer.jar"]
     ```
   - Docker compose file
     ```yml
   
                version: '3'
         services: 
           springboot-testcontainer:
             image: springboot-testcontainer
            # env_file: ./environment.env
             environment: 
              - MYSQL_PASSWORD=tooroot
              - MYSQL_ROOT_PASSWORD=tooroot
              - MYSQL_DATABASE=testcontainerdb
              - MYSQLDB_LOCAL_PORT=3306
              - MYSQLDB_DOCKER_PORT=3306
              - SPRING_LOCAL_PORT=8888
              - SPRING_DOCKER_PORT=8888
              - DB_HOST_NAME= mysqldb
             depends_on:
               - mysqldb
             restart: always
             build: . # use command docker compose up --build to build the image
                      #(build: . means it will use docker file available in current dir)
             ports:
               - 8888:8888
           mysqldb:
            #container-name: mydbcontainer
            image: mysql
            #env_file: ./environment.env
            volumes:
              - ./dbvolume:/var/lib/mysql
            ports:
             - 3306:3306
            environment:
              - MYSQL_PASSWORD=pass
              - MYSQL_ROOT_PASSWORD=tooroot
              - MYSQL_DATABASE=testcontainerdb
              - MYSQLDB_LOCAL_PORT=3306
              - MYSQLDB_DOCKER_PORT=3306
              - SPRING_LOCAL_PORT=8888
              - SPRING_DOCKER_PORT=8888
              - DB_HOST_NAME= mysqldb
     ```
   - Application.properties file
    
     ```
      server.port=8888
      spring.datasource.url=jdbc:mysql://${DB_HOST_NAME:localhost}:${$MYSQLDB_DOCKER_PORT:3306}/${MYSQL_DATABASE:test}
      spring.datasource.username=root
      spring.datasource.password=${MYSQL_ROOT_PASSWORD:tooroott}
      spring.sql.init.mode=always

     ```
</p>
</details>

