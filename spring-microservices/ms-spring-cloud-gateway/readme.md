# Diagram
- [Request per thread](/thread-per-req.jpg)
- [Request per thread](/ms-spring-cloud-gateway.jpg)
- [Event Loop](/event-loop.jpg)

# Part 1
<p>Configure book service and edge service access the books service via gateway.
for distributed tracing configure the graphana</p>

## Book Service - Accessing book endpoint directly
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
- now try to access the book endpoint via gateway ```http://localhost:8080/books```, it should produce same response with additional header 

## Configure Grafana and Prometheus
### Configure Prometheus
- Configure below properties in application.yml file, this will register all the endpoints with actuator
  ```
    management:
  endpoints:
    web:
      exposure:
        include:
          - "*"
  ```
- now access ```http://localhost:8080/actuator``` this will provide all the endpoints (including prometheus)
- Now pull prometheus image ```docker pull prom/prometheus```
- Create ```prometheus.yml``` file and run prometheus using command ```run -p 9090:9090 -v C:\workspace\proj\Spring\spring-microservices\ms-spring-cloud-gateway\edge-service\src\main\resources\prometheus.yml prom/prometheus```
- Prometheus is up and running on given port, in my case it is ```9090``` prometheus is accessible on ```http://localhost:9090``` or ```http://ip:9090``` url.  
 
### Configure Grafana
- pull the docker grafana image ```docker pull grafana/grafana```
- run Grafana ```docker run -d -p 3000:3000 grafana/grafana```
- now access the url ```localhost:3000```, it will ask password which is admin/admin, it will ask to reset it. provided ```12345```
- add prometheus as data source ```http://ip<where prometheus running localhost>:9090<port>``` save and test
- Create a Dasshboard for metrics
# configure Loki with Grafana
- [installation guide](https://grafana.com/docs/loki/latest/installation/docker/)
- run the command given on the above installation guide link
- now loki and promtail is running, try to access loki using ```http://192.168.56.1<localhost ip or localhost>:3100/metrics``` url
- Go to grafana, setting--> dataSource-> add loki data source
  
