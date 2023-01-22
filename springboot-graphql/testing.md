# Unit Testing

# Integration Testing
- Create a test class and annotate it with ``` @SpringBootTest and @AutoConfigureGraphQlTester ``` annotation
- Autowire ``` GraphQlTester ``` class.
- Write the test method.
  - Approach 1- graphQlTester.document(document) -> graphql query will be sued as String
  - Approach 2- graphQlTester.documentName(documentName) -> Can refer ```*.gql or *.graphql ``` file created under resources/graphql-test directory
- Run the Test method
