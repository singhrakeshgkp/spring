# Union 
1. create a union in .graphqls file for example **union ProfileAndComment = Profile | Comment** 
2. create a query in query type profileAndCmmentDetails: [ProfileAndComment]
3. define method for above union in GraphQLQueryResolver implementation class

Note __typename keyword is used to identify the data if it belongs to Profile type or Comment type
### Sample Request
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
### Sample Response

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
# Interface

### Sample Request

{
    allParent{
        __typename
        id
        ...on Profile{
        userName
        }
    ...on Comment{
      text
    }
    }
}

### Sample Response

{
    "data": {
        "allParent": [
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
                "__typename": "Comment",
                "id": 1,
                "text": "Do you like this article?"
            },
            {
                "__typename": "Comment",
                "id": 2,
                "text": "This is a great article"
            },
            {
                "__typename": "Comment",
                "id": 3,
                "text": "This is a comment"
            }
        ]
    }
}

