### Order MS - Step7

- Order Orchestration Saga [Diagram](/spring-microservices/ms-sagapattern/order_saga_orchestration.jpg)
- Create a new class ```OrderSagaOrchestration.java``` 
- create and common dependency (refer section Setup New Proj  for common classes )
#### Product MS -Step 8

- create and common dependency (refer section Setup New Proj  for common classes )
- Create a ```ReserveProductCommand``` Handler
  - Add core dependency in pom.xml
  - Add handler ReserveproductCommand Handler in ```ProductAggregate.java``` class
  - Publish the event using ```AggregateLifecycle.apply(event);```
- Add Event sourcing method

### Step 9 - Handle the product reserved event in OrderSagaClass- OrderService
- Add following method in OrderSagaClass 
  ```
  
	@SagaEventHandler(associationProperty = "orderId")
	public void handle(ProductReservedEvent productReservedEvent){
  //Business logic 
  }
  ```
- Before running the application perform below steps.
  - add following properties in axon-server/config/axonserver.properties
    ```
    server.port=8024
    axoniq.axonserver.name=testaxon-server
    axoniq.axonserver.hostname=localhost
    axoniq.axonserver.devmode.enabled=true
    axoniq.axonserver.accesscontrol.token=welcome123
    ```
  - Config serializer and token in MS application.propertie or yml file
    ```
      axon.axonserver.token= welcome123
      # Axon serializer config
      axon.serializer.general=jackson
      axon.serializer.events=jackson
      axon.serializer.messages=jackson

    ```
  
### Setup New Proj  for common classes and include this as dependency in order and product MS
- Create new spring starter project
- Add Lombok and Axon starter dependency.
- Remove build section from pom.xml
- Remove spring boot main class and test class
- Add it in order and product MS

### Method will be executed in the following order.
```
Create Product Sequence
---------------------------------------
1. ProductController-> commandGateway.sendAndWait(command)
2. CommandInterceptor
3. ProductAggregate# ProductAggregate(constructor annotated with  @CommandHandler annotation)-> AggregateLifecycle.apply(someEvent)
4. ProductAggregate# on(someMethod annotated with @EventSourcingHandler) 
5. ProductEventHandler#on(someMethod annotated with @EventHandler) 
--------------------------------------------------------------------------

Create Order Sequence
--------------------------------------------------------- 
1. OrderController-> commandGateway.sendAndWait(command)
2. CommandInterceptor
3. OrderAggregate# OrderAggregate(constructor annotated with  @CommandHandler annotation)-> AggregateLifecycle.apply(someEvent)
4. OrderAggregate# on(someMethod annotated with @EventSourcingHandler) 
5. OrderEventHandler#on(someMethod annotated with @EventHandler)
6. OrderSagaOrchestration.java Method # handle(OrderCreatedEvent orderCreatedEvent)
7. ProductAggregate#public void handle(ReserveProductCommand reserveProductCommand)
8. ProductAggregate#on(ProductReservedEvent productReservedEvent)
9. ProductEventHandler#on(ProductReservedEvent productReservedEvent)
10. OrderSagaOrchestration.java Method # public void handle(ProductReservedEvent productReservedEvent) 
```
