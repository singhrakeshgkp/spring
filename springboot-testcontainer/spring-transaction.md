# Spring Transactions
- Blogs and URLS <br/>
https://www.marcobehler.com/guides/spring-transaction-management-transactional-in-depth  <br/>
https://docs.spring.io/spring-framework/docs/3.0.x/spring-framework-reference/html/transaction.html#tx-propagation

### Declarative Transaction
- Rollback does not happens for checked exception. We can modify this behaviour using ```@Transaction(rollBackFor=Excepiton.class)``` for refernce refer ```BookServiceTxnTest``` class.
  - Throwing unchecked exception from BookService explicitly-> transaction should be rolled back
  - Throwing Checked exception-> Transction bill not be roll back.
  - Modifying default behaviour using 
    ```@Transactional(rollbackOn={Exception.class})``` properties. Now transaction will be rolled back for all the Exceptions and its child classes.<br/>
    for above scenario refer test cases ```saveBookTxnEx1, saveBookTxnEx2, saveBookTxnEx3 and saveBookTxnEx4``` written in ```BookServiceTxnTest``` class.
#### Transaction Propagation
- Required -> support a current transaction, create new one if none exists. This is default behavior hence non need to speciy(or we can drop this properties).
- Required_New -> Create a new transaction, and suspend the current transaction if one exists.
- Not_Supported ->Execute non-transactionally, suspend the current transaction if one exists.
- Supports -> Support a current transaction, execute non-transactionally if none exists.
- Mandatory -> upport a current transaction, throw an exception if none exists.
- Never -> Execute non-transactionally, throw an exception if a transaction exists.
- Nested -> Execute within a nested transaction if a current transaction exists, behave like REQUIRED otherwise.

##### Propagating Reqired to Required New
- Method involved ---> class ```BookServiceTxnTest``` Methods ```saveBookTxnEx5, saveBookTxnEx6 ```
```
 case 1->(m1->m2) Propagation Required and Require_New in the same class(data will be rolled back)
  
Here only one transaction will be created as spring creates Transactionl BookService Proxy but you are inside BookService class and calling an inner method so no more transaction proxy involved.

Case 2->(m1->m2) Propagation Required and Required_New in different class(data will not be rolled back as transaction happening in different txn)
       
```

### Programmatic Transaction
