# GraphQLWithSpringBoot
GraphQL is an specification or query language for api
### GraphQL Consist three main parts
 * Guery
 * Mutation
 * Subscription

### Building your first GraphQL Application.
1. Create a spring boot program and Add following dependencies in pom.xml file\
  a. graphql-java-tools (for more details click  [here](https://github.com/graphql-java-kickstart/graphql-java-tools/blob/master/README.md))\
  b.  graphql-spring-boot-starter (for more details click [here](https://github.com/graphql-java-kickstart/graphql-spring-boot)\
  apart from above dependencies we need to add lombok spring data jpa and sql connector dependencies if we want to communicate with database
2. Add following properties in application.yml file\
  server:
  http2:
    enabled: true
  port: 8787
3. Create GraphQL Schema.
4. Create GraphQL Resolvers
5. Run and test your application from postman
URL- http://localhost:8787/graphql
http://localhost:8080/graphiql?path=/graphql(if u want to test in browser)

### Different Type of Queries
for more details click [here](https://github.com/singhrakeshgkp/spring/blob/master/springboot-graphql/GraphQLQueriesAndResponse.md)\ 

### Resolvers
For Query we have GraphQLQueryResolver.</br>
Note:- GraphQLQueryResolver is the main resolver, used to resolve any properties or operations within **Query** type
to resolve a specific properties, the library will look for any methods matching the property name. For example, the articles properties within **Query** will be matched to a method called articles(), getArticles() or isArticles().
for any other type we use GraphQLResolver

### Running GraphQL Mockserver

### Union and Interface
for more details click [here](https://github.com/singhrakeshgkp/spring/blob/master/springboot-graphql/union_interface.md)\

### Fragments
Fragments are resuable things in GraphQL. We can assume fragments like a function in programming language.
for more details click [here](https://github.com/singhrakeshgkp/spring/blob/master/springboot-graphql/fragments.md)\

### Mutation
GraphQL is not just about queries, it also provides a way to manipulate the data. This is where GraphQL mutation comes into picture.
for more details click [here](https://github.com/singhrakeshgkp/spring/blob/master/springboot-graphql/mutation.md)\

### Subscription
 In case of query we send single request from client and get single response back from server. But in case of subscription its completely different. we send single request but then we get back stream of messages back.
