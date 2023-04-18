## Diagram
- [Request per thread](/thread-per-req.jpg)
- [Request per thread](/ms-spring-cloud-gateway.jpg)
- [Event Loop](/event-loop.jpg)

## Book Service part1 - Accessing book endpoint directly
- Crate new Spring boot applicatio with following dependencies
  - Observability --> actuator, Prometheus, openelementry agent
  - Security ---> Oauth2 resource server
  - Testing ---> keycloak test container
- Create controller and write the required code to return the books
- Now test the book endpoint using ```localhost:8181/books``` url

## Edge Service part 1 - accessing book endpoint via gateway
- Crate new Spring boot applicatio with following dependencies
  - Gateway ---> spring cloud gateway,reactive web
  - Observability --> actuator, Prometheus, openelementry agent
  - Circuit Breaker--> resilience4j, resilience4j micrometer
  - Security ---> Oauth2 client 
  - Redis ---> Redis Reactive
- configure routes filter and predicates in application.yml file
- now try to access the book endpoint via gateway ```http://localhost:6000/books```, it should produce same response with additional header 

  
