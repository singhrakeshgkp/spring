
### Declarative Transaction
- Rollback does not happens for checked exception. We can modify this behaviour using ```@Transaction(rollBackFor=Excepiton.class)``` for refernce refer ```BookServiceTransactionTest``` class.
  - Throwing unchecked exception from BookService explicitly-> transaction should be rolled back
  - Throwing Checked exception-> Transction bill not be roll back.
  - Modifying default behaviour using following properties.
    ```@Transactional(rollbackOn={Exception.class})```

### Programmatic Transaction
