TASK DESCRIPTION

Implement REST endpoints for methods adding/editing users and getting list of users 
(get user by id, all users sorted in ascending or descending order, with a limitation on 
the number of users in the result set). When implementing controllers, use REST design principles.
Implement HttpMessageConverter, which can write User objects into application/pdf MIME type. 
Read support is not needed (canRead method should just return false).
Configure ContentNegotiationViewResolver via MVC namespace.
Add two types of message converters:
Newly implemented application/pdf http message converter.
Jackson JSON message converter.
Implement a test client using RestTemplate. By default, the client should use JSON representation 
to communicate with REST service.
Create test HTTP requests that have an Accept header value as application/pdf; test that content 
negotiation works properly and that PDF representation is returned on any getting user information
request that is supposed to return User object or a list of User objects.

To use UserRestTemplateTest for testing the cofiguration:
1. Use data.sql for create some users
2 Invoke the testing

