# CQL - Queries
### Consistency
  - Quorum = (Partition+1)%2
  - Run ``` consistency ``` see currrent consistency level
  - Command ``` consistency <consistency level> ``` will set specified consistency
# Inserts and Selects

<details><summary>Insert and select queries</summary>
<p>
  
   - Inserting values in ``` student_by_id ``` table  
     - Following command will insert the value in table ``` student_by_id ```
     ```
      insert into student_by_id (id,addressline1,country,name)values (1,'GKP','IND','RAKESH');  Note- it will insert the record in student_by_id table
      select * from student_by_id;  Note- it will give all the records
     ```
     - insert record in ``` student_by_id table ``` without primary key (id) will give following error
     ```
      insert into student_by_id (addressline1,country,name)values ('GKP','IND','RAKESH');
      InvalidRequest: Error from server: code=2200 [Invalid query] message="Some partition key parts are missing: id"
     ``` 
     - more
   - Inserting values in ``` student_by_address ``` table primary key is composite here
     - simple insert query
     ```
     insert into student_by_address (id,addressline1,country,name) values(1,'GKP','IN','Jitendra');
     ```
     - while running select query for ``` student_by_address ``` table if we provide only one column name in where clause it will give following error
       as here we need to provide all the column in where caluse which is part of composite key.
     ```
      select * from student_by_address where id=1 and addressline='GKP';
      InvalidRequest: Error from server: code=2200 [Invalid query] message="Undefined column name addressline"
      cqlsh:test_keyspace> select * from student_by_address where id=1 and addressline1='GKP';
     ```
     - if we insert the duplicate record in that case cassandra will update the record instead of inserting new one.
   
</p>
</details>

### Timestamps, TTLs, Collections and Secondary Indexes
  - Timestamps- we can check what was the last time when data was written in a specific column in cassandra
  ```
  select  country, name, addressline1, writetime(name) from student_by_address;
  ```
  - TTL(Time To Leave) -  for how long changes will be available, for exmaple below query update the name column value for 30 sec only, post that value become null.
    it's useful where data is valid for certain period of time only.
  ```
   ->updae command with TTL
   update student_by_address set name='Ranvijay Singh' where addressline1='GKP' and id=2; -> Simple update command
  ```
  - Collections
    - Collections queries
    ```
    alter table student_by_id add mob set<text>;
    update employee_by_id set mob={'983874334','5104537845'} where id=1;        -> this will add collection of mob number in the specified column
    update employee_by_id set mob=mob+{'983874334','5104537845'} where id=1;    -> will add the new value in existing collection
    update student_by_id set mob=mob-{'983874334','5104537845'} where id=1;     -> remove the mobile number from existing one
    update student_by_id set mob={} where id=1;                                 -> remove all mob number
    ```
  - Secondary Indexes
    - if we try to query something using column which is not part of primary key, we can not access the data
    - If we add ``` allow filtering  ``` in the query then cassandra will allow us to fetch the record. This is not recommended as its unperformant way
    ```
    select * from student_by_id where name='RAKESH' allow filtering;
    ```
    - Using secondary index we can query record using column which is not part of primary key.
    ```
    create index on student_by_id(name);
    select * from student_by_id where name='RAKESH' ;
    ```
### UUID Counters
  - UUID -  support two type of uuid
    - Normal UUID
      - Generate uniqu id
        ```
        create table student_by_uuid (id uuid, full_name text, email text);
        insert into student_by_uuid(id,full_name,email) values(uuid(),'rakesh sing','rk@gmail.com');

        ```
      - 
    - Time UUID 
      - time component of uuid to store the data in database chronologically 
        ```
        create table student_by_timeuuid (id timeuuid primary key, full_name text, email text);
        insert into student_by_timeuuid(id,full_name,email) values(now(),'raghadfdfddra sing','dfdfk@gmail.com');
        ```
  - Counters-> special kind of column in cassendra that stores integer which only changes increment or decrement. Keeping track of counter in distributed database
    is a challenge. There are some limitations on counter column in cassendra.
    - They only can be created on dedicated tables.
    - They can't be assign to column that serves as a primary key or partition key
    - we can also not index or delete counter column.
    - it has counter data type
    ```
       create table fee_by_student_id (id uuid primary key, noOfMFS counter);
       update  fee_by_student_id set noOfMFS = noOfMFS+1 where id= uuid(); -> insert is not allowed for counter column as it can only be incr or decremented
    ```
### CSV import and export
   - CSV Import rules
     - Make sure all column which is part of primary key is present in csv file
     - column which is not present in csv file will be set to null.
     - following command can be used to import csv file to cassandra database. if data is huge we should prefer batch 
     ```
      copy student_by_address(id,addressline1,name,country) from 'C:/Users/rakesh-sin/OneDrive - HCL Technologies Ltd/Desktop/test.csv' WITH DELIMITER = ',' AND            header=true;
     ```
   - 
