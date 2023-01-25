# Configure Eureka Discovery Server. step-1
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
   
# Configure Product Micorservice. step-2 
 - Create new spring boot application and add spring web, lombok and eureka client dependencies
 - Create a controller class and define get, post and put method
 - Annotate main class with ``` @EnableDiscoveryClient ``` annotation
 - specify the following properties in application.properties file
  ```
  eureka.client.service-url.defaultZone=http://localhost:8761/eureka
  ```
	
# Configure gateway service step-3
	
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
      
# Configure Axon Server. step -4

### Using Zip file on windows/Linux
- Go to ``` https://developer.axoniq.io/download ``` and download the latest zip file
-  Extract it on your machine and run the jar using java -jar command. Axon server will be started on default port 8024  ``` http://localhost:8024/ ```
-  Configure Action server properties. configuration details can be found [here](https://docs.axoniq.io/reference-guide/axon-server/administration/admin-configuration/configuration)
   - create new folder named ``` config ``` under axon folder
   - create new file named ``` actonserver.properties ``` and add the following properties
     ```
      server.port=4242
      axoniq.axonserver.name=testaxon-server
      axoniq.axonserver.hostname=localhost
      axoniq.axonserver.devmode.enabled=true
     ```
   - run the axon server, you will observe that server will be started on port 4242 and with the given configuration
### Running Axon server inside docker
  - Use the following command to run axon in docker
  
    ```
     docker run --name axonserver -p 8024:8024 -p 8124:8124 -v "C:/workspace/docker-data/data":/data -v "C:/workspace/docker-data/data/eventdata":/eventdata -v "C:/workspace/docker-data/config":/config axoniq/axonserver

      -p -> stand for publish
      axoniq/axonserver -> we havn't specified the version so by default it will pull latest version

    ```
 - Configure axon srver properties
   - copy the axonserver.properties file (earlier we created) inside ``` docker-data/config ``` directory
   - run the application again. you will observe action server will be started with the properties given in config
   - try to access ``` localhost:8024 ```
    
# CQRS Pattern- step 5

<details><summary><b>Persisting product event in event store</b></summary>
	
- Add Axon framework dependency in pom.xml file
  ```
  <dependency>
   <groupId>org.axonframework</groupId>
    <artifactId>axon-spring-boot-starter</artifactId>
    <version>4.6.3</version>
  </dependency>
  ```
- Create new Command java class. Naming convenstion should be like (verb)+(noun)+Command. i.e, CreateProductCommmand.
- Autowire Environment and CommandGateway. Command Gateway will send the command to ``` command bus ``` diagram for same is [here](/spring-microservices/ms-sagapattern/CQRS_gtw.jpg)	 
- Create aggreagate class named ``` ProductAggregate  ``` for more details see the [diagram](/spring-microservices/ms-sagapattern/product_aggregate.jpg)
- Annotate the aggregate class with ``` @Aggregate ``` annotation
- Create new class ``` ProductCreatedEvent  ``` (noun)+(verb)+Event
- Create new method ``` on() in ProductAggregate class ``` annotate this method with ```@EventSourcingHandler``` annotation.
- Run Axon server first and then run your spring boot application, you should be able to see the event details on Axon server dashboard
	
</details>

### sdfdsf	
	
