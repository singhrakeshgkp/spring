1. With Argument
This will return Article by id .
Request
```json
 {

    article(id: 1){
        id
        title
        author{
            id
            userName
        }
    }
}
```

Response.
```json
{
    "data": {
        "article": {
            "id": 1,
            "title": "Hello wold",
            "author": {
                "id": 1,
                "userName": "g00glen00b"
            }
        }
    }
}

```
2. With Argument and Alias.

here writer and writerName are alias. actually here we are calling  author as writer and username as writerName and same way we are getting the field in the responase.
so whatever you want to call a field u can specify in the query and same way u will get the response.
Request.
```jason
{
    article(id: 1){
        id
        title
        writer:author{
            id
            writerName:userName
        }
    }
}
```

Response.
```json
{
    "data": {
        "article": {
            "id": 1,
            "title": "Hello wold",
            "writer": {
                "id": 1,
                "writerName": "g00glen00b"
            }
        }
    }
}
```
3.@include keyword in the query
one can include or exclude the field using @include keyword in the query.in below case writer will be excluded from response
Request.
```json
{
    article(id: 1){
        id
        title
        writer:author @include(if:false){
            id
            writerName:userName
        }
    }
}
```
Response.
```json
{
    "data": {
        "article": {
            "id": 1,
            "title": "Hello wold"
        }
    }
}
```
4. @include keyworkd can be used with Variable as weill. see the below query with variable
Variable .
```json
{
"withWriter":true
}
```
Request.
```json
query testFun($withWriter: Boolean!){
    article(id: 1){
        id
        title
        writer:author @include(if:$withWriter){
            id
            writerName:userName
        }
    }
}
```
Response.
```json
{
    "data": {
        "article": {
            "id": 1,
            "title": "Hello wold",
            "writer": {
                "id": 1,
                "writerName": "g00glen00b"
            }
        }
    }
}
```
5.Union Command(returns combination of multiple types) - define below type in your graphql.

Define below in your graphqls file.
```json
union AllArtileAndProfile = Article | Profile
```
```json
allArticleAndProfile :[AllArtileAndProfile]
```
Request.
```json
{
 
  allArticleAndProfile{
    __typename
    ... on Profile{
      id
      userName
    }
    ... on Article{
      id
    }
    
  }
}
```
Response.
```json
{
  "data": {
    "__typename": "Query",
    "allArticleAndProfile": [
      {
        "__typename": "Profile",
        "id": 1,
        "userName": "g00glen00b"
      },
      {
        "__typename": "Profile",
        "id": 2,
        "userName": "admin"
      },
      {
        "__typename": "Article",
        "id": 1
      },
      {
        "__typename": "Article",
        "id": 2
      }
    ]
  }
}
```


