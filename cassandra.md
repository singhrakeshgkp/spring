# Cassandra 
<details> <summary><b>Cassandra Setup</b></summary>
  <details><summary><b>Cassandra setup in windows</b></summary>
  <p>
  
   - Go to cassandra bin directory
   - kdsf
   - fdsfkdsf
  </p>
  </details>
  
  <details><summary><b>Cassandra setup in windows using docker</b></summary>
  <p>
  
   - Go to cassandra bin directory
   - kdsf
   - fdsfkdsf
    
    
  </p>
    
  </details>
  
  
  <details><summary><b>Cassandra setup in linux</b></summary>
  <p>
  
   - Go to cassandra bin directory
   - kdsf
   - fdsfkdsf
  </p>
  </details>

</details>



<details> <summary><b>Cassandra Core</b></summary>
<p>
  Go to cassandra bin drectory and run below command form terminal to start cassandra server</br>
  
   cassandra.bat sqlsh

</p>
<p>
  
   - Replication, data centers and Racks
   
     - Open new command prompt and run ```  nodetool status ``` it will give the following output which means this machine or this instance of cassandra is running on rack 1
     
     ```
     Status=Up/Down
      |/ State=Normal/Leaving/Joining/Moving
      --  Address    Load       Tokens       Owns (effective)  Host ID                               Rack
      UN  127.0.0.1  181.21 KiB  256          100.0%            bc840d7e-ca5c-4c10-9c9b-79283ab2a0f1  rack1
     ```
   - CQL- Keyspaces and Tables
     - we write the cassandra query on cassandra query shell for that run ```  cassandra.bat sqlsh ``` command
     - Creating keyspaces
       - use below command to create keyspaces
       ```
        CREATE KEYSPACE test_keyspace WITH replication = {'class': 'SimpleStrategy', 'replication_factor': '1'} AND durable_writes='true';
       ```
       - Use ``` describe <keyspace name> ```  command to check the created keyspace
       - Use ``` describe keyspaces ``` command to list down all the keyspaces
       - ``` drop keyspace <keyspace name> ``` will drop the specified keyspace
       - ``` use <keyspace name> ``` will move u on the create keyspace
     - Creating table
       - 
       - 
      ```
       CREATE TABLE student_by_id (id int primary key, name text, addressLine1 text, country text);
      ```
   - dsfdsf
   - sdfjskf
   - dvj
    
</p>
</details>
  
