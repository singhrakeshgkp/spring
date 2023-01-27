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

For product MS CQRS diagram click [here](/spring-microservices/ms-sagapattern/CQRS_productms.jpg)<br/>
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

<details><summary><b>Logging or Validation the command request using Message interceptor</b></summary>

	
- Create a interceptor class called ``` CommandInterceptor ``` (this calss should implement MessageDispatchInterceptor interface) annotate it with ```@component``` annotation.
- Register the ```CommandInterceptor``` on command bus to do so follow the below steps.
  - Go to your main spring boot class
  - add following code, which will get the command interceptor bean from contex and register it with bus.
    ```
     @Autowired
	public void registerCommandInterceptor(ApplicationContext context, CommandBus bus) {
		bus.registerDispatchInterceptor(context.getBean(CommandInterceptor.class));
	}
    ```
	
- 

</details>
<details><summary><b>Persisting product event in DB</b></summary>
	
- Add spring data jpa starter and h2 dependency in pom.xml
- Add required db properties in application.properties file, create persistent and repo classess
- Create event handler class ``` ProductEventHandler ``` annotate it with ``` @Component``` annotation
- Create new method in event handler calss (note method signature annotated with @EventSourceHandler should match), write the logic to persist the data in db
- Run your application, try to access h2 console via gateway url ``` http://localhost:5052/product-service/h2-console ``` 
- You will observe data would be present in the database

</details>

<details><summary><b>CQRS Querying data</b></summary>
For details how it works checkout the [diagram](/spring-microservices/ms-sagapattern/cqrs_querying.jpg)
	
- Create new ProductQueryController class provide the required dependency
- Create a query handler named ``` ProductQueryHandler ```, create a method and annotate it with ```@QueryHandler ``` annotation
- Run the application, you should be able to get the list of products

</details>


<details><summary><b>CQRS - Validation, Error Handling & Transaction Management</b></summary>

- <b>what is set based consistency problem? How does command module will check if record is already exists?</b>
- A.-> Command module can check if record is already exist using separate db called lookup table. To setup these things follow below steps.
  - Create a new entity class named ```ProductrLookup```, add ony product prop which is required for lookup such as unique id and product name
  - Create a  repository ```ProductLookupRepo``` for ProductLookup entity
  - Create a new handler class ```ProductLookupEventHandler``` annotate it with ```@Component and @ProcessingGroup("product-group") ```
  - Also annotate other product event handler class used in the project such as ```ProductEventHandler``` with annotation ```@ProcessingGroup("product-group")```
  - The ```@ProcessingGroup("product-group")``` will execute all the product related events in the same thread, so it would be easier to rollback in case of failure
  - Add following prop in application.properties file.
	```axon.eventhandling.processors.product-group.mode=subscribing```
  - Write the logic to check if entity exist in interceptor class.
  - CQRS [diagram](/spring-microservices/ms-sagapattern/CQRS_setbased.jpg) to check if record exist in db.
  - Sequence of the execution is as follow.
    ```
	Controller->Interceptor-> CommandHandler(@CommandHandler) or Aggregate-> EventHandler-> AggregateLifecycle.apply(event) -> Method annotated with @EventSourcingHandler annotation -> 

	Aggregate -> Class Annotated with @Aggregate(ProductAggregate - this class will have commandhanler method) annotation
	CommandHandler -> Method Annotated with @CommandHandler annotation
	EventHandler-> ProductEventHandler will have handler method annotated with @EventHandler annotation
    ```
- <b>CQRS Error Handling</b>
  - Please refer the given [diagram](/spring-microservices/ms-sagapattern/CQRS_error.jpg)
  - <b>CQRS Controller/Interceptor Error Handing</b>
    - Create a new Centralized Error Handler calss and annotate it with  ```@ControllerAdvice``` annotation
    - Now remove try catch from ProductCommandController class and try to run the application
    - You will observe controller advice code will be executed if any error occurs.
  - <b>CQRS Command Layer Error Handling</b>
    - Go to your command/AggregateClass in my case it is ```ProductAggregate``` and throw exception explicitly just after ```AggregateLifecycle.apply(event);```
    - You will observe that even if we throw the exception after aggrCycle.apply() ```@EventSourcingHandler``` method will not be executed this is because axon framework does not immediately persist the event first it state it, after stage if any exception thrown it will automatically rollback from same stage.
  - <b>CQRS Event Layer Error Handling</b>
    - Default behavior of axon framework is to handle the exception, log it and continue the execution.
    - Create an error handler method in ```ProductEventHandler``` class and annotate it with ```@ExceptionHandler``` annotation.
    - Again we have to propogate the exception,otherwise axon framework will handle, log the exception and continue the execution. To propagate further follow the below steps.
      - Create a new class ```ProductServiceEventHandlerException``` implementing ```ListenerInvocationErrorHandler``` interface
      - Now we have to register the above exception class to our processor group which is ```product-group```, code written in spring boot main class.
      - Now for testing  explicitly throw an exception from ProductEventHandler class.
      - Run the application and try to create product, expected result -> data should not be saved in Product, Product Lookup and event store even if we throw the exception after caling repo.save method.

</details>
# Configure Order Service Step-6	
	
