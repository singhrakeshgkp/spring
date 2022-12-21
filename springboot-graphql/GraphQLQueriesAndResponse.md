# Union 
1. create a union in .graphqls file for example **union ProfileAndComment = Profile | Comment** 
2. create a query in query type profileAndCmmentDetails: [ProfileAndComment]
3. define method for above union in GraphQLQueryResolver implementation class

Note __typename keyword is used to identify the data if it belongs to Profile type or Comment type
### Sample Request

```json
{
    profileAndCmmentDetails{
        __typename
        ...on Profile{
            id
            id
        }
    ...on Comment{
        id
    }
    }
}
```
### Sample Response
```json
{
    "data": {
        "profileAndCmmentDetails": [
            {
                "__typename": "Profile",
                "id": 1
            },
            {
                "__typename": "Profile",
                "id": 2
            },
            {
                "__typename": "Comment",
                "id": 1
            },
            {
                "__typename": "Comment",
                "id": 2
            },
            {
                "__typename": "Comment",
                "id": 3
            }
        ]
    }
}
```
# Interface
* Deifne new interface type
* Add a method in query.
* Define new interface in java class and also one resolver method 

### Sample Request

```json
{
  getAllEntity{
    __typename
    id
    ... on Profile{
     
      userName
    }
    ... on Article{
     
      text
    }
  }
}
```
### Sample Response

```json
{
  "data": {
    "getAllEntity": [
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
        "id": 1,
        "text": "This is a hello world"
      },
      {
        "__typename": "Article",
        "id": 2,
        "text": "Bar"
      }
    ]
  }
}
```
