
### How to create fragments?
Below syntax can be used to create fragments.

```json
fragment commentFragment(fragment name) on Comment(tye name){
 text
 author
}
```
### How to refer fragments?
Using ... you can refer fragment.

### Sample Req
```json
{
    allParent{
        ... commentFragment
    }
}
```
```json
fragment commentFragment on Comment{
 text
 author{
     userName
     bio
 }
}
```
### Sample Response
```json
{
    "data": {
        "allParent": [
            {},
            {},
            {
                "text": "Do you like this article?",
                "author": {
                    "userName": "g00glen00b",
                    "bio": "The author of this blog"
                }
            },
            {
                "text": "This is a great article",
                "author": {
                    "userName": "admin",
                    "bio": "The administrator of this blog"
                }
            },
            {
                "text": "This is a comment",
                "author": {
                    "userName": "admin",
                    "bio": "The administrator of this blog"
                }
            }
        ]
    }
}

```
