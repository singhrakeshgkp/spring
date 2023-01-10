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

### LogBack Integration with SpringBoot
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
 - Setting up unique id per request
   - dkfdjk
   - kdfjs
   - kdf
 - 

