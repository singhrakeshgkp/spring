#### Docker and Docker compose
<details>
<summary>Docker file details</summary>
<p>
   
   - FROM openjdk:11    
     - base image which will contain jdk and OS
   - EXPOSE 8181
     - Port exposed to outside container
     
   - ADD target/*.jar app.jar
     - will take jar file from given path and add that to docker image and file name will be app.jar
  - ENTRYPOINT ["java","-jar","/app.jar"]
    - Entry point for the application   
</p>
</details>


<details>
<summary>Docker compose file description</summary>
<p>

   - fsdf
   - sdfs
   - fsdfsd
   
</p>
