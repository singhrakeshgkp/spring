# Order MS - Step7

- Order Orchestration Saga [Diagram](/spring-microservices/ms-sagapattern/order_saga_orchestration.jpg)
- Create a new class ```OrderSagaOrchestration.java``` 
- create and common dependency (refer section Setup New Proj  for common classes )
# Product MS -Step 8

- create and common dependency (refer section Setup New Proj  for common classes )
- Create a ```ReserveProductCommand``` Handler
  - Add core dependency in pom.xml
  - Add handler ReserveproductCommand Handler in ```ProductAggregate.java``` class
  - Publish the event
- Add Event sourcing method

# Step 9 - Handle the product reserved event in OrderSagaClass- OrderService

# Setup New Proj  for common classes and include this as dependency in order and product MS
- Create new spring starter project
- Add Lombok and Axon starter dependency.
- Remove build section from pom.xml
- Remove spring boot main class and test class
- Add it in order and product MS
