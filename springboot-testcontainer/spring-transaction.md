
### Declarative Transaction
- Rollback does not happens for checked exception. We can modify this behaviour using ```@Transaction(rollBackFor=Excepiton.class)``` for refernce refer ```BookServiceTransactionTest``` class.
  - Throwing Checked exception-> Transction bill not be roll back.
  - Throwing unchecked exception from BookService explicitly-> transaction should be rolled back

### Programmatic Transaction
