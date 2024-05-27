# codebyteTast
please open the project is the IntelliJ
Do install the maven by skipping the test

or you can find the jar file in the target directory: you can directly run the jar from the given command

java -jar jwt-security-0.0.1-SNAPSHOT.jar

once project is started then you can import the collection "CoderByteTask.postman_collection" in the postman
once done there you will find login-getjwt request

without token you will not be able to hit any request

Kindly make sure that you first hit the login-getjwt
there will be user name and password in the payload just hit it you will get the token

this is the body
{
    "email":"nabeelx64@gmail.com",
    "password":"password"
}

once you get the token you can add the token in postman environments as i have used variables to add token to all other request: the variable name is: {{token}}

once done the you can make all other requests having the token added to it via postman env and will be authorized to hit the end point, if you dont have token added to requests then 403 forbidden will be the response

the list of apis you can access after getting JWT

1. fetch users
URL: http://localhost:8080/api/users (token required in authorization)

2. get all post request
URL: http://localhost:8080/api/posts (token required in authorization)

3. get post by user id 
URL: http://localhost:8080/api/posts/user?userId=6930072

4. fetch comments
URL: http://localhost:8080/api/comments

5. Fetch comments by postid
http://localhost:8080/api/users?postId=127133


