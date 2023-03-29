
### Declarative Transaction
- Rollback does not happens for checked exception. We can modify this behaviour using ```@Transaction(rollBackFor=Excepiton.class)``` for refernce refer ```BookServiceTransactionTest``` class.
  - Throwing unchecked exception from BookService explicitly-> transaction should be rolled back
  - Throwing Checked exception-> Transction bill not be roll back.
  - Modifying default behaviour using 
    ```@Transactional(rollbackOn={Exception.class})``` properties. Now transaction will be rolled back for all the Exceptions and its child classes.<br/>
    for above scenario refer test cases ```saveBookTxnEx1, saveBookTxnEx2, saveBookTxnEx3 and saveBookTxnEx4``` written in ```BookServiceTransactionTest``` class.
#### Transaction Propagation
- Required -> support a current transaction, create new one if none exists. This is default behavior hence non need to speciy(or we can drop this properties).
- Required_New -> Create a new transaction, and suspend the current transaction if one exists.
- NOT_SUPPORTED ->Execute non-transactionally, suspend the current transaction if one exists.
- Supports -> Support a current transaction, execute non-transactionally if none exists.
- Mandatory -> upport a current transaction, throw an exception if none exists.
- Never -> Execute non-transactionally, throw an exception if a transaction exists.
- Nested -> Execute within a nested transaction if a current transaction exists, behave like REQUIRED otherwise.

### Programmatic Transaction
