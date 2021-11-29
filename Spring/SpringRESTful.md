# SOAP와 REST의 이해

<!--참고 **Spring Boot를 이용한 RESTful Web Services 개발**[Dowon Lee](https://www.inflearn.com/users/40572) -->



## SOAP (Simple Object Access Protocol)

- SOAP는 우리가 사용할 수 있는 HTTP 프로토콜 등의 프로토콜을 이용하여 xml 기반의 메세지를 컴퓨터 네트워크 상에서 전달할 수 있는 시스템을 의미한다
- SOAP은 Envelope, Header, Body로 이루어진다.



## REST(Representational State Transfer)

- REST는 Resource의 Representation에 의한 상태 전달, 자원의 상태를 표현
- HTTP Method를 통해 Resource를 처리하기 위한 아키텍쳐

### 	RESTful

- REST API를 제공하는 웹서비스
- HTTP Methods(GET, PUT, POST, DELETE), HTTP Status Codes(200, 404, ....)등으로 구성됨
- 모든 요청은 응답 코드와 함께 처리됨

#### 		Resource

- URI(Uniform Resource Identifier), 인터넷 자원을 나타내는 유일한 주소
- XML, HTML, JSON



### ex) Social Media Application

| Description                   | REST API                    | HTTP Method |
| ----------------------------- | --------------------------- | ----------- |
| Retrieve all Users            | /users                      | GET         |
| Create a User                 | /users                      | POST        |
| Retrieve one User             | /users/{id}                 | GET         |
| Delete a User                 | /users/{id}                 | DELETE      |
| Retrieve all posts for a User | /users/{id}                 | GET         |
| Create a posts for a User     | /users/{id}/posts           | POST        |
| Retrieve details of a User    | /users/{id}/posts/{post id} | GET         |



### RestController

- Spring4 부터 @RestController 지원
- @Controller + @ResponseBody
- View를 갖지 않는 REST Data(JSON/XML)를 반환
