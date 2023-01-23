# Configure Eureka Discovery Server.
 - Create new Spring starter project, add required dependency
 - If multiple instance of Eureka running in that case it me play role of client and server, here we will be running single instance so included only server repo in pom
 - Annotate main class with ``` @EnableEurekaServer ``` annotation
 - Add following properties in application.properties
  
  ```
   server.port=8761
   eureka.client.register-with-eureka=false 
   eureka.client.fetch-registry=false
   eureka.instance.prefer-ip-address=true
   eureka.client.service-url.defaultZone=localhost//8761/eureka
   ```
   
# Configure Product Micorservice

- Create new spring boot application and add spring web, lombok and eureka client dependencies
- Create a controller class and define get, post and put method
- Annotate main class with ``` @EnableDiscoveryClient ``` annotation
- specify the following properties in application.properties file
  ```
  eureka.client.service-url.defaultZone=http://localhost:8761/eureka
  ```
  
# Configure gateway service
 - Create new spring boot application, add eureka client , spring cloud gateway and reactive web dependency
 - Annotate mail class with ``` @EnableDiscoveryClient ``` annotation
 - Add the following properties in application.properties file.
   ```
     eureka.client.service-url.defaultZone=http://localhost:8761/eureka
     spring.application.name=gateway-service
     server.port=5052
     #enable automatic routing with resource locator. it will locate the service by its name
     spring.cloud.gateway.discovery.locator.enabled=true

     #by default service name in eureka will appear in capital letter, and if we try to acccess the resource with
     #service name(service name in lower case) it will fail to handle this i have used below properties.
     spring.cloud.gateway.discovery.locator.lower-case-service-id=true
   ```
