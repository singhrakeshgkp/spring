#### Docker and Docker compose
<details>
<summary><b>Docker file details</b></summary>
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
   - ``` docker rm app<image name> ``` -> to remove image
   - ```
     docker run -p 9090:9090 --name app<image name> --net test-net<network name> -e MYSQL_HOST=mysqldb -e MYSQL_USER=root -e MYSQL_PASSWORD=tooroot -e MYSQL_PORT=3306 app<application image name i.e spring boot>
     ```
</p>
</details>


<details>
<summary>Docker compose file description</summary>
<p>

   - fsdf
   - sdfs
   - fsdfsd
   
</p>
