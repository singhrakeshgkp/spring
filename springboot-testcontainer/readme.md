# Swagger Integration 
 - Add following dependency in pom.xml
 -   ```
     <dependency>
     <groupId>org.springdoc</groupId>
     <artifactId>springdoc-openapi-ui</artifactId>
     <version>1.6.4</version>
     </dependency>
     ```
 - Restart the server and try to access below urls
   -  ``` localhost:<port>/<application context>/v3/api-docs  ``` -> to access api docs ex. ``` http://localhost:8888/v3/api-docs  ```
   -  ``` localhost:<port>/<application context>/swagger-ui/index.html ``` -> to access swagger ui ex. ``` http://localhost:8888/swagger-ui/index.html  ```

# Sonar Qube + Jacoco Integration 
- Add sonar qube and jacoco plugins in your pom.xml file
- Download and install Sonar Qube Server from official website(community version)
- Unzip the downloaded Sonar Qube and start the server(ools\sonarqube-9.8.0.63668\bin\windows-x86-64\StartSonar.bat)
- By Default Sonalr qube will be started on 9000 port ``` http://localhost:9000```
- Default cred for sonar qube running on local is admin admin, it will force to update after first login change it to any thing such as admin123 etc
- Use below command to generate code coverage by jacoco
  ```
  clean org.jacoco:jacoco-maven-plugin:prepare-agent install
  ```
- Generate new token from sonar qube dashboard ``` my account -> security-> generate token(if already generated then ignore it)```
- Run below command to link the report with sonar.
  ``` sonar:sonar -Dsonar.login=sqa_94810502f0092cd0c1254ebc8a18b8dc1011a4c6<generated token>```


# LogBack Integration with SpringBoot
 - Create ``` logback-spring.xml ``` file in your spring boot application class path.
 - Do the required changes in the ``` logback-spring.xml  ``` configuration file for example i have modified file name to ``` sbwtc.log ```
 -  ```
         <?xml version="1.0" encoding="UTF-8"?>
    <configuration>

        <property name="LOGS" value="./logs" />

        <appender name="Console"
            class="ch.qos.logback.core.ConsoleAppender">
            <layout class="ch.qos.logback.classic.PatternLayout">
                <Pattern>
                    %black(%d{ISO8601}) %highlight(%-5level) [%blue(%t)] %yellow(%C{1}): %msg%n%throwable
                </Pattern>
            </layout>
        </appender>

        <appender name="RollingFile"
            class="ch.qos.logback.core.rolling.RollingFileAppender">
            <file>${LOGS}/sbwtc.log</file>
            <encoder
                class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">
                <Pattern>%d %p %C{1} [%t] %m%n</Pattern>
            </encoder>

            <rollingPolicy
                class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
                <!-- rollover daily and when the file reaches 10 MegaBytes -->
                <fileNamePattern>${LOGS}/archived/sbwtc-%d{yyyy-MM-dd}.%i.log
                </fileNamePattern>
                <timeBasedFileNamingAndTriggeringPolicy
                    class="ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP">
                    <maxFileSize>10MB</maxFileSize>
                </timeBasedFileNamingAndTriggeringPolicy>
            </rollingPolicy>
        </appender>

        <!-- LOG everything at INFO level -->
        <root level="info">
            <appender-ref ref="RollingFile" />
            <appender-ref ref="Console" />
        </root>

        <!-- LOG "com.baeldung*" at TRACE level -->
        <logger name="com.baeldung" level="trace" additivity="false">
            <appender-ref ref="RollingFile" />
            <appender-ref ref="Console" />
        </logger>

    </configuration> 
    ```
 - Follow below steps to log the entry exit using custome annotation
   - Create a custom annotation named ``` LogEntryExit  ```
   - Add following dependency in pom.xml file
     ```
     <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-aop</artifactId>
     </dependency>
     ```
   - Define an aspect with an advice ``` LogEntryExitAspect ```
 - Setting up unique id per request using MDC(Mapped Diagnostic Context)
   - Create a class ``` SLF4JMDCFilterConfig.java ```
   - kdfjs
   - kdf
 - 

