### security1 + JWT

this is a projet to test and make an experience about **spring security** in the first Level and Jwt
 security at the second

JSON Web Token or JWT, as it is more commonly called, is an open Internet standard (RFC 7519) 
for securely transmitting trusted information between parties in a compact way. The tokens contain 
claims that are encoded as a JSON object and are digitally signed using a private secret or a public
key/private key pair. They are self-contained and verifiable as they are digitally signed. JWT’s can
be signed and/or encrypted. The signed tokens verify the integrity of the claims contained in the token,
while the encrypted ones hide the claims from other parties.

<!-- TOC -->
* [security1 + JWT](#security1--jwt)
<!-- TOC -->

```
JWT’s can also be used for the exchange of information though
they more commonly used for authorization as they offer a lot 
of advantages over session management using in-memory random tokens.
The biggest of them being the enabling the delegation of authentication
logic to a third-party server like AuthO etc.
```

A JWT token is divided into 3 parts namely – header, payload, and signature in the format of
The JWT includes a secret which we will define in our application.properties file as given below. 

`secret=somerandomsecret`
Now let’s create a package called jwtutils. This package is going to contain all classes and interface
related to JWT operations, which will include.

1. _Generating token_
2. _Validating token_
3. _Checking the signature_
4. _Verifying claims and permissions_

In this package, we create our first class called Token Manager. This class will be responsible for the
creation and validation of tokens using io.jsonwebtoken.Jwts.
