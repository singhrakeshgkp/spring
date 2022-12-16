# Consistency
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
