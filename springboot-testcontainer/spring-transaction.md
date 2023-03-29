
### Declarative Transaction
- Rollback does not happens for checked exception. We can modify this behaviour using ```@Transaction(rollBackFor=Excepiton.class)``` for refernce refer ```BookServiceTransactionTest``` class.
  - Throwing unchecked exception from BookService explicitly-> transaction should be rolled back
  - Throwing Checked exception-> Transction bill not be roll back.
  - Modifying default behaviour using 
    ```@Transactional(rollbackOn={Exception.class})``` properties. Now transaction will be rolled back for all the Exceptions and its child classes.
#### Transaction Propagation


### Programmatic Transaction
