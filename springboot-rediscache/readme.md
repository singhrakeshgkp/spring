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
   -  ``` set name<key> rakesh<value> ``` -> set the key value pair
   - ``` get name<key name> ``` -> get the value stored against the given key
   - dfd
 
 - use command ``` redis-cli ``` to connect with redis command line interface
 - 
</p>
</details>
