<details><summary>Redis Setup-Docker</summary>
<p>
 
 - pull redis image using ``` docker pull redis ``` command
 - Run redis container ``` docker run -it --name redis-container-ex -d redis ```
 - ``` docker logs redis-container-ex<container name> ``` can be used to check redis container logs
 - ``` docker exec -it redis-container-ex bash ``` used to connect with redis container
 - use command ``` redis-cli ``` to connect with redis command line interface
 - 
</p>
</details>

### Configure Spring Boot Proj
 - create new spring boot application
 - add ``` @EnableCaching ``` annotation on main application.java class
 - Configure Redis factory and redis template


