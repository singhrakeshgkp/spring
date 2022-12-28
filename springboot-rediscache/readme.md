<details><summary>Redis Setup-Docker</summary>
<p>
 
 - pull redis image using ``` docker pull redis ``` command
 - Run redis container ``` docker run -it --name redis-container-ex -d redis ```
 - ``` docker logs redis-container-ex<container name> ``` can be used to check redis container logs
 - ``` docker exec -it redis-container-ex bash ``` used to connect with redis container
 - use command ``` redis-cli ``` to connect with redis command line interface
 - 
</p>
</details>

<details><summary>Redis Core</summary>
<p>
 
 - Redis stores the data in key value format
 - connect to redis CLI 
 - Perform set, get, and delete operation using CLI
   -  ``` set name<key> rakesh<value> ``` -> set the key value pair
   - ``` get name<key name> ``` -> get the value stored against the given key
   - ``` get keys *<pattern>  ``` -> get all the key for the given pattern. ``` get keys name* <pattern>  ``` -> get all the key starting with name
   - ``` scan 0 ``` -> return data available in page number 0. ``` scan 0 count 1 ``` -> will return 1 record from page 0
   - ```  scan 0 match *name* ```  -> return key having name keyword inn it.
   - ``` del <keys name> ``` -> delete the specified key, we can pass multiple keys separated by space.
   - ``` flushdb ``` -> delete all the key
 - Set expiry values
   - ``` set employee:1:name rakesh ex 5 ``` key pair will be available for 5 seconds only
   - 
 - 
</p>
</details>
