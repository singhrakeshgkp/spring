# Set 1
0. What is spring boot?
   It is spring module that offers Rapid application development to spring framework. Spring module is used to create an application based on spring framework with few configurations.
1. What is actuator in spring boot?
   With the help of an actuator, you can monitor what is happening inside the running application.You can identify beans, the health status of your application, CPU usage, and many more with the actuator. By using @Endpoint annotation, you can create a custom endpoint.
2. What is auto configuration in spring boot?
   Spring Boot auto-configuration attempts to automatically configure your Spring application based on the jar dependencies that you have added.

3. how to disable specific autoconfiguration class?
   If you find that specific auto-configuration classes that you do not want are being applied, you can use the exclude attribute of ```@EnableAutoConfiguration``` annotation to disable them, as shown in the following example: 
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
5. Name the feature of spring boot?<br/>
   a. Starter dependency<br/>
   b. Auto-Configuraton<br/>
   c. Spring initializer<br/>
   
6. What is spring boot starter? how is it useful?<br/>
   Spring boot has many starters. They are set of convenient dependency descriptors. Starters allows you to include these descriptors in your pom.xml file
7. What is devtool in spring boot?<br/>
   it helps you to increase the productvity, if you have it, you don't need to redeploy your application every time you make any changes.
8. What are the essential component of spring boot?<br/>
   a. Spring boot starter<br/>
   b. Spring boot autoconfiguration<br/>
   c. Spring boot actuator<br/>
   d. Spring boot CLI<br/>
9. What is spring boot starter parent?<br/>
   Its a special starter which makes Gradle or maven dependency-management easy by adding jars to your classpath
10. How can u access a value defined in the application.properties file?
    ```
    @value ("${spring.custom.property}")
    String valueStr;
    ```

11. Difference between spring boot and spring ?<br/>
    Spring is a web application development framework based on java. On the other hand Spring boot is the extension of spring framework which eliminates the boilerplates code required for setup a spring boot application.
    
12. Explain spring boot admin?<br/>
    Spring boot admin is a community project which helps you to manage and monitor your spring boot applications.
13. what is spring initializer?<br/>
    Spring initializer is a web application which helps you to create initial project 
14. Spring CLI ?<br/>-----------------------
15. what are the embedded container supported by spring ? <br/>
   a) Tomcat <br/>
   b) Jetty<br/>
   c) Undertow <br/>
   By default, it uses tomcat as an embedded container.
16. Explaine thymleaf in spring boot?<br/>
    Thymleaf is a server side java template engine for a web application. It helps u to bring elegant natural templates to your web application.<br/>
17. df
18. 
   
 
