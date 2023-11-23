# Mongo DB Core Concept
### Local Setup
- Download Mongo DB zip and mongos shell binary
- set the bin file location in your path variable(both MongoDB and mongos sh folder)
- create data/db directory in your c:/ drive
- open command prompt and run mongod command
- open new command prompt and run mongosh command.
##### customizing log and data file location
- create mongodb.conf file in parallel to bin directory(mongodb repo not mongos shell)
- configure the following properties
  ```
    noauth=true
    dbpath=..\data
    logpath=..\log\mongodb.log
  ```
- run the mongod server using command ```mongod -configdb mongodbconfig.conf```

### Mongo DB basic command

- create database ```use <db-name>```
- 

# Spring Data Mongo DB Concept

#### CRUD Operation
- Create new spring boot project with spring web, spring data mongo db starter, lombock, devtool dependency.
- Start your mongo db server, add the required properties in application.properties file to establish the connection
- Create Person model class. 
- Create PersonRepo and PersonController class.
- Perform the CRUD operation for person and write the Junit and Integration test cases.

#### Testing PersonController
- Create ```PersonControllerIntegrationTest1.java``` class
- Annotate class with ```@SpringBootTest``` annotation, this annotation will load the complete application context.
  ```
  @SpringBootTest
  public class PersonControllerIntegrationTest1 
  ```
- 
