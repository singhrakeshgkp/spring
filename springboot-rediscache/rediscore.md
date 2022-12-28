# Redis Core
 
 - Redis stores the data in key value format
 - connect to redis CLI 
 ### Perform set, get, and delete operation using CLI
-  ``` set name<key> rakesh<value> ``` -> set the key value pair
-  ``` set name<key> rakesh<value> keepttl ``` -> while updating the value if we do not provide keepttl keyword it will set the ttl to nill to preserve the ttl               value use keepttl keyword
- ``` get name<key name> ``` -> get the value stored against the given key
- ``` get keys *<pattern>  ``` -> get all the key for the given pattern. ``` get keys name* <pattern>  ``` -> get all the key starting with name
- ``` scan 0 ``` -> return data available in page number 0. ``` scan 0 count 1 ``` -> will return 1 record from page 0
- ```  scan 0 match *name* ```  -> return key having name keyword inn it.
- ``` del <keys name> ``` -> delete the specified key, we can pass multiple keys separated by space.
- ``` flushdb ``` -> delete all the key
 ### Set expiry values
- ``` set employee:1:name rakesh ex 5 ``` key pair will be available for 5 seconds only
- ``` ttl employee:1:name<key name> ``` -> to check the time to leave (remaining expirty time)
- ```  expire employee:1:name<key name> 60<time in sec> ``` -> extends the expiry time

