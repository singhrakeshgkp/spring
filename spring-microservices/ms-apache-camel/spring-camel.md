# Apache Camel + Spring Boot Integration
### What is apache camel?
<p>
  Apache camel is an open source integration framework, sesigned to make integrating systems simple and easy.
  Enterprise applications are complex in nature.<br/>
  Apachel camel Inspired by **"Enterprise Integration patterns"** book written by Gregor Hope and Bobby Woolf
</p>

- Entriprises can have 100s of applications:
  - Complex communication pattern.
  - Varietly of Transport- Http, Queues etc.
  - Varietly of protocols - Http, JMS, AMQP.
- Evolution of cloud and microservices makes Enterprice application more complex.

# Configure Product Microservice.
- We perform mainly two operation transformation and processing
- Create new spring starter project with loback, spring web, actuator, apache camel repository.
- Create new class ```ProductTimerRouter.java``` implementing RoutingBuilder interface
  - Add the following code, this code will print nothing 
    ```
    from("timer:product-timer")
		.to("log:product-timer");
    ```
  - If we add following code it will perform transformation.
    ```
     from("timer:product-timer")
		.log("${body}")  // print null
		.transform().constant("test data")
		.log("${body}")//print test data
		.bean(systemTime)//in case of single method no need to specify method name
		.log("${body}")// print current time
		.to("log:product-timer");
    ```
  - Following code will perform both transformation and processing.
  
    ```
     from("timer:product-timer")
		.log("${body}")  // print null
		.transform().constant("test data")
		.log("${body}")//print test data
		.bean(systemTime)//Transformation operation using bean,in case of single method no need to specify method name
		.log("${body}")// print current time final Transformation
		.bean(loggingMsg)//Processing operation with bean -> msg will be printed same as in final transformation
		.to("log:product-timer");
    ```
 - Following code snippet will perform processing operation using Processor.
   ```
   from("timer:product-timer")
		.log("${body}")  // print null
		.transform().constant("test data")
		.log("${body}")//print test data
		.bean(systemTime)//Transformation operation using bean,in case of single method no need to specify method name
		.log("${body}")// print current time final Transformation
		.bean(loggingMsg)//Processing operation with bean -> msg will be printed same as in final transformation
		.process(new TestProcessor()) // processing operation using processor
		.to("log:product-timer");
   ```
### Copy file from source to destination
- Comment ```@Component``` annotation of class ProductTimerRouter.java
- Create new router class ```FileTransferRouter.java``
- Use the following code snippet to copy file from source to destination location.
  ```
  from("file:files/input")
		.log("${body}")//use this line if we want to print the file content
		.to("file:files/output");
  ```
