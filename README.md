# user-role-authentication

The HOST can vary

Mwethod POST localhost:8090/auth/register
{
    "firstName":"test",
    "lastName":"Man",
    "email": "testerman@api.com",
    "password":"password"
}
example of the JSON payload the endpoint takes. the output 
payload.
{
    "firstName": "test",
    "lastName": "Man",
    "email": "testerman@api.com",
    "token": "<generated token>"
}
---------------------------------------------------------
method POST localhost:8090/auth/login
uses bearer token authentication

{
    "email": "testerman@api.com",
    "password":"password"
}

return the response/ output payload of
{
    "firstName": "test",
    "lastName": "Man",
    "email": "testerman@api.com",
    "token": "<token value>"
}
--------------------------------------------------------
method POST localhost:8090/admin/add_product
This enpoint allows the administrator, to add an itme to the DB
The input payload looks as follows.
{
    "productName": "Example Product",
    "SKU": 123456,
    "productDescription": "This is an example product description",
    "category": "Example Category"
}

The output payload looks as follows
{
    "productName": "Example Product",
    "productDescription": "This is an example product description",
    "category": {
        "categoryID": 2,
        "categoryName": "Example Category",
        "products": null
    },
    "sku": 123456
}

The SKU, is the stock unit number which serves as a unique identifier for the 
products.

