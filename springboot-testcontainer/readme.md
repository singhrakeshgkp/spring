### Integrating swagger 
 - Add swagger starter dependency in pom.xml
 - Annotate spring boot main class with ``` @EnableSwagger2 ``` annotation(recommended to put in config class not root class)
 - Restart the server and try to access ``` localhost:<port>/<application context>/v2/api-docs  ```
