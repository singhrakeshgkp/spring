1. what is ```@autowired``` annotation used for?
   This is used to to inject the dependecy. when it sees @autowired spring will look for a class that matches the property in the application context.
2. what is difference between ```@autowired,@inject and @resource``` annotation
   ```
    @autowired is spring propriatary annotation, however @iject and @resource belongs to java family available under javax root package.
    @autowired and @inject are identical in behavior, both of these annotation uses AutowiredAnnotationBeanPostProcessor to inject dependencies
    @Resource annotation uses the CommonAnnotationBeanPostProcessor to inject the dependencies.
    Execution path of @autowired and @inject annotation
    1. Matches by Type
    2. Restricts by Qualifiers
    3. Matches by Name
    Execution path of @resource annotation
    1. Matches by Name
    2. Matches by Type
    3. Restricts by Qualifiers (ignored if match is found by name)
   ```
  
