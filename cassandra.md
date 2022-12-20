# Cassandra 
<details> <summary><b>Cassandra Setup</b></summary>
  <details><summary><b>Cassandra 3.11 setup in windows as windows is not supporting version 4 for that refer docker setup</b></summary>
  <p>
  
   - Go to cassandra bin directory
   - kdsf
   - fdsfkdsf
  </p>
  </details>
  
  <details><summary><b>Cassandra setup in windows using docker</b></summary>
  <p>
  
   - Make sure docker desktop is installed in your machine
   - Open command prompt and run below command
   ```
    docker pull cassandra:latest
   ``` 
   - Go to docker desktop, you will be able to see doker container running, go to docker terminal form docker desktop and run cqlsh
   - fdsfkdsf
    
    
  </p>
    
  </details>
 </p>
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
       - simple create table statement
       ```
        CREATE TABLE student_by_id (id int primary key, name text, addressLine1 text, country text);
       ```
       - Creating table with composite key(we can specy n number of columns in primary key)
       ```
         CREATE TABLE student_by_address (addressLine1 text, id int , name text, country text, primary key(addressLine1,id));
       ```
       - create table with clustering key
       ```
        CREATE TABLE student_by_addressln1_and_city (addressLine1 text, id int , name text, country text, city text, primary key((addressLine1,city)id));
       ```
      - below command can be used to describe tables
      ```
       describe tables
      ```
      ```
      describe <table name>
      ```
   - Consistency, insert and selects  for more details click [here](https://github.com/singhrakeshgkp/spring/blob/master/cqlshqueries.md)
    
</p>
</details>
 
 <details><summary><b>Cassandra Advance</b></summary>
  <p>
  
   - Peer to Peer Architecture
     - In peer to peer architecture every nodes in the cluster are considered equal(it does not follow leader and follower approach)
     - Replica is independent on each other and dont relly on each other. 
     - Node who receives the request form client called 'co-ordinatior' it is respinsible to sync the darta in in other partition/node.
  
  </p>
  </details> 
