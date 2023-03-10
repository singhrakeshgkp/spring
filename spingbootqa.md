# Set 1
1. What is actuator in spring boot?
   With the help of an actuator, you can monitor what is happening inside the running application.You can identify beans, the health status of your application, CPU usage, and many more with the actuator. By using @Endpoint annotation, you can create a custom endpoint.
2. What is auto configuration in spring boot?
   Spring Boot auto-configuration attempts to automatically configure your Spring application based on the jar dependencies that you have added.

3. how to disable specific autoconfiguration class?
   If you find that specific auto-configuration classes that you do not want are being applied, you can use the exclude attribute of @EnableAutoConfiguration to disable them, as shown in the following example: 
   ```
    @Configuration
    @EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
    public class MyConfiguration {
    }
   ```
4. How to resolve whitelevel error in spring boot?
   we can do it by 3 ways which is given below<br/>
   <b>Custom Error Controller</b>– where you will be implementing ErrorController  interface which is provided by SpringFramework and then overriding its getErrorPath() so that you can return a custom path whenever such type of error is occurred.<br/>
   <b>By Displaying Custom error page</b>– All you have to do is create an error.html page and place it into the src/main/resources/templates path. The BasicErrorController of of springboot will automatically pick this file by default.<br/>
   <b>By disabling the whitelabel error page</b>– this is the easiest way where all you need to do is server.error.whitelabel.enabled property to false in the application.properties file to disable the whitelabel error page.
5. j
6.  
