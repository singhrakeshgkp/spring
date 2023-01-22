# Unit Testing

# Integration Testing

### Writing Integration Test cases for Query 
- Create a test class and annotate it with ``` @SpringBootTest and @AutoConfigureGraphQlTester ``` annotation
- Autowire ``` GraphQlTester ``` class.
- Write the test method.
  - Approach 1- graphQlTester.document(document) -> graphql query will be sued as String
  - Approach 2- graphQlTester.documentName(documentName) -> Can refer ```*.gql or *.graphql ``` file created under resources/graphql-test directory
- Run the Test method
  ```
   We can have multiple query or mutation in single *.gql file. we can separate them by giving an operation name such as query getAllArticles{....}
  ````

### Writing Integration Test cases for Mutation
- Create a mutation test class annotate it with ``` @SpringBootTest and @AutoConfigureGraphQlTester ``` annotation
- Autowire ``` GraphQlTester ``` class. Write the test method
- Pass the input type argument by constructing a map as shown below.
  ```
  Map<String, Object> map = new HashMap<>();
		map.put("title", "this is test article");
		map.put("text", "tesfsjdkfdsff");
		map.put("authorId", 2);
   
 above is equivalent to below json
 
     {
			     "title":"additing article by input type",
			     "text":"article text will fdf here",
			    "authorId": 2
			 }
  ```
- Execute the test case.
