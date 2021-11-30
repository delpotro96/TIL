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





### User Service API 구현



#### User 도메인 클래스 생성

```java
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
public class User {
    private Integer id;
    private String name;
    private Date joinDate;

}
```



#### DAO, Service 생성

```java
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// user 예제에서는 그냥 통합해서 사용하고 section 5에서 제대로 함
public class UserDaoService {
    private static List<User> users = new ArrayList<>();

    private static int usersCount=3;

    static {
        users.add(new User(1,"KHLEE",new Date()));
        users.add(new User(2,"SMPARK",new Date()));
        users.add(new User(3,"YSKIM",new Date()));
    }

    public List<User> findAll(){
        return users;
    }

    public User save(User user){
        if (user.getId() == null) {
            user.setId(++usersCount);
        }

        users.add(user);
        return user;
    }

    public User findOne(int id){
        for (User user : users){
            if (user.getId() == id){
                return user;
            }
        }
        return null;
    }
    public User deleteById(int id){
        Iterator<User> iterator = users.iterator();

        while(iterator.hasNext()) {
            User user = iterator.next();

            if (user.getId() == id){
                iterator.remove();
                return user;
            }
        }
        return null;
    }
}

```



#### Controller 생성

```java

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private UserDaoService service;

    public UserController(UserDaoService service){
        this.service = service;
    }

    @GetMapping("/users")
    public List<User> retrieveAllUsers(){
        return service.findAll();
    }

    //Get /users/1 or users/10
    @GetMapping("/users/{id}")
    public User retrieveUser(@PathVariable int id){
        return service.findOne(id);
    }

    @PostMapping("/users")
    public void createUser(@RequestBody User user){
        User savedUser = service.save(user);

    }
    
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id){
        User user = service.deleteById(id);
        
        if ( user == null){
            throw new UserNotFoundException("String.format(\"ID[%s] not found\", id");
        }
    }

}

```



### Status CODE 제어 방법

#### 유저 등록의 경우

```java
@PostMapping("/users")
    public ResponseEntity createUser(@RequestBody User user){
        User savedUser = service.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
```

#### HTTP STATUS CODE

[HTTP 상태 코드 - 위키백과, 우리 모두의 백과사전 (wikipedia.org)](https://ko.wikipedia.org/wiki/HTTP_상태_코드)



### 특정한 예외 처리

#### UserController

```java
//findOne 대고 C A v

@GetMapping("/users/{id}")
    public User retrieveUser(@PathVariable int id){
        User user = service.findOne(id);

        if ( user == null ){
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }

        return user;
    }
```



#### UserNotFoundException.java

```java
/*
*
* HTTP Status code
*
* 2XX -> OK
* 4XX -> Client Error
* 5XX -> Server Error
*
*
* */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
```



### 일반화된 예외 Class



#### exception/ExceptionResponse.java

```java
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse {
    private Date timestamp;
    private String message;
    private String details;
}
```



#### CustomizedResponseEntityExceptionHandler.java

```java

import com.example.restfulwebservice.user.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@RestController
//@ControllerAdvice 를 선언해두면 모든 컨트롤러가 실행될 때
//ControllerAdvice를 가진 BEAN을 거쳐감, 모든 예외 상황이 발생할
@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler {

    // 모든 예외를 처리하는 예외 처리
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request){
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // UserNotFoundException 예외 처리
    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundException(Exception ex, WebRequest request){
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
    }

}
```



### 유효성 체크

#### pom.xml 추가

```xml
<dependency>
   <groupId>org.springframework.boot</groupId>
   <artifactId>spring-boot-starter-validation</artifactId>
</dependency>
<!--javax.validation-->
<dependency>
   <groupId>javax.validation</groupId>
   <artifactId>validation-api</artifactId>
   <version>2.0.1.Final</version>
</dependency>
```



#### User.java

```java
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@AllArgsConstructor
public class User {

    private Integer id;

    @Size(min = 2, message = "Name은 2글자 이상 입력해주세요")
    private String name;

    @Past
    private Date joinDate;

}
```



#### UserController 수정

```java
@PostMapping("/users")
    public ResponseEntity createUser(@Valid @RequestBody User user) {
        User savedUser = service.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
```



#### CustomizedResponseEntityExceptionHandler 수정



```java
@Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), "Validation Fail", ex.getBindingResult().toString());

        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);

    }
```



### 다국어 처리

- 자동으로 번역되어 표시되지는 않는다.



#### Main클래스에 @Bean 추가

```java
@Bean
    public LocaleResolver localResolver(){
        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
        localeResolver.setDefaultLocale(Locale.KOREA);
        return localeResolver;
    }
```



#### application.yml 변경

```yml
spring:
  message:
    basename: messages
```



#### messages.properties 생성

```properties
greeting.message=안녕하세요
```

- 이후 각 국가 언어별로 프로퍼티 생성



#### Controller 수정

```java
@Autowired
    private MessageSource messageSource;
 @GetMapping(path = "/hello-world-internationalized")
    public String helloWorldInternationalized(@RequestHeader(name="Accept-Language", required = false) Locale locale){
        return messageSource.getMessage("greeting.message", null, locale);
    }
```

