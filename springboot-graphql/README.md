# GraphQLWithSpringBoot
GraphQL is an specification or query language for an api <br/>

To see how to create docker image and deploy the docker image navigate to this [link](/springboot-graphql/docker-deploy-steps.md)

### GraphQL Consist three main parts
 * Query
 * Mutation
 * Subscription

### Building your first GraphQL Application.
  - Create a spring boot application and Add following dependencies in pom.xml file\
  ```
	    <dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-graphql</artifactId>
	</dependency>

	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-web</artifactId>
	</dependency>

	<dependency>
		<groupId>org.springframework.graphql</groupId>
		<artifactId>spring-graphql-test</artifactId>
		<scope>test</scope>
	</dependency>
  ```
  - apart from the above dependency u can add other dependency as per your need such as mysql connector, spring data jpa, lombok etc..
  - Add following properties in application.yml file <br/>
   ``` server:
   http2:
     enabled: true
   port: 8787

  ```
 - Create GraphQL Schema.
 - Create GraphQL Resolvers
 - Run and test your application from postman
	URL- ``` http://localhost:8787/graphql  ``` (can be used in postman for testing)<br/>
	``` http://localhost:8080/graphiql?path=/graphql ```(can be used for testing from browser)<br/>

### Different Type of Queries
for more details click [here](/springboot-graphql/GraphQLQueriesAndResponse.md)<br/>

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
