1. With Argument
This will return Article by id 
Request
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

Response
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


2. With Argument and Alias

here writer and writerName are alias. actually here we are calling  author as writer and username as writerName and same way we are getting the field in the responase.
so whatever you want to call a field u can specify in the query and same way u will get the response.
Request
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

Response
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

3.@include keyword in the query
one can include or exclude the field using @include keyword in the query.in below case writer will be excluded from response
Request
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

Response

{
    "data": {
        "article": {
            "id": 1,
            "title": "Hello wold"
        }
    }
}

4. @include keyworkd can be used with Variable as weill. see the below query with variable
Variable 
{
"withWriter":true
}

Request
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

Response
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

5.Union Command(returns combination of multiple types) - define below type in your graphql







