### Integrating swagger 
 - Add following dependency in pom.xml
 -   ```
     <dependency>
     <groupId>org.springdoc</groupId>
     <artifactId>springdoc-openapi-ui</artifactId>
     <version>1.6.4</version>
     </dependency>
     ```
 - 
 - Restart the server and try to access below urls
   -  ``` localhost:<port>/<application context>/v3/api-docs  ``` -> to access api docs ex. ``` http://localhost:8888/v3/api-docs  ```
   -  ``` localhost:<port>/<application context>/swagger-ui/index.html ``` -> to access swagger ui ex. ``` http://localhost:8888/swagger-ui/index.html  ```
