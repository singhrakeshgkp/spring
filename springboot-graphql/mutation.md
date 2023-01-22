
# Mutation
* Define below mutation in your query.
* Create Mutation controller, create two method for the above mutation and annotatate them with @MutationMapping or @SchemaMapping annotation.
* Run your application and use below mutation to store data.



```json 
    type Mutation{
addProfile(userName: String!, bio: String!): Profile!
addArticle(title: String!, text: String!, author: Profile!, comments: [Comment]):Article!

}
```

Request AddProfile Mutation.
```json
mutation{
  addProfile(
    userName: "rakesh singh",
    bio: "my first mutation application"
  ){
    id
    userName
  }
}
```
Response.

```json
{
  "data": {
    "addProfile": {
      "id": 5,
      "userName": "rakesh singh"
    }
  }
}
```
# Different type of mutation queries
* Implementing mutation with single fields/arguments
   Request.
   
```json
   mutation{
  addArticle(
    title:"amazon cloud"
    text:"today will start amazon cloud session"
    authorId:1
  ){
    id
    text
    title
    author {
      id
    }
  }
}

```
Response.

 ```json
      {
      "data": {
        "addArticle": {
          "id": 5,
          "text": "today will start amazon cloud session",
          "title": "amazon cloud",
          "author": {
            "id": 1
          }
        }
      }
    }
 ```
 
* Implementing mutation with input type.

```json
  input ArticleInput{
    id: Int
    title: String!
    text: String!
    authorId: Int
}

```
   Request.
   
```json
   mutation{
  createArticle(
  articleInput: {
    
    title:"additing article by input type"
    text:"article text will goes here"
   authorId: 2
  }){
    id
    text
    title
   
  }
}

```
  Response.
  
```json
  {
  "data": {
    "createArticle": {
      "id": 3,
      "text": "article text will goes here",
      "title": "additing article by input type"
    }
  }
}
 
```

* Mutation query with input as variable in request.<br/>
Request.

```json
mutation
  createArticle($input:ArticleInput){
  createArticle(articleInput: $input){
    id
    text
    title
   
  }
}


```
Variables.

```json

{ 
   "input":{
     "title":"additing article by input type",
     "text":"article text will goes here",
    "authorId": 3
  }
}

```
Response.

```json

{
  "data": {
    "createArticle": {
      "id": 8,
      "text": "article text will goes here",
      "title": "additing article by input type"
    }
  }
}

```
