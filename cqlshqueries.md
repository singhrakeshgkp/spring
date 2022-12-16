# Consistency
  - Quorum = (Partition+1)%2
  - Run ``` consistency ``` see currrent consistency level
  - Command ``` consistency <consistency level> ``` will set specified consistency
# Inserts and Selects

<details><summary>Insert and select queries</summary>
<p>
  
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
  - 
   
</p>
</details>
