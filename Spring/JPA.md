# JPA



## application.yml 추가

```yaml
spring:
  datasource:
    url: jdbc:h2:tcp://localhost/~/jpashop;MVCC=TRUE
    username: sa
    password:
    driver-class-name: org.h2.Driver

  jpa:
    hibernate:
      ddl-auto: create
    properties:
      hibername:
        format_sql: true

logging:
  level: 
    org.hibernate.SQL: debug
```



## Query parameter log 남기기

Spring Boot DataSource Decorator

```xml
implementation 'com.github.gavlyukovskiy:p6spy-spring-boot-starter:1.7.1'
```



## 연관관계



- ManytoOne 일대다 관계에서는 다 가 외래키를 가지게 된다

- 외래 키가 있는 곳을 연관관계의 주인으로 정해라

-  연관관계의 주인은 단순히 외래 키를 누가 관리하냐의 문제이지 비즈니스상 우위에 있다고 주인으로 정하면 안된다.. 예를 들어서 자동차와 바퀴가 있으면, 일대다 관계에서 항상 다쪽에 외래 키가 있으므로 외래 키가 있는 바퀴를 연관관계의 주인으로 정하면 된다. 물론 자동차를 연관관계의 주인으로 정하는 것이 불가능 한 것은 아니지만, 자동차를 연관관계의 주인으로 정하면 자동차가 관리하지 않는 바퀴 테이블의 외래 키 값이 업데이트 되므로 관리와 유지보수가 어렵고, 추가적으로 별도의 업데이트 쿼리가 발생하는 성능 문제도 있다. 