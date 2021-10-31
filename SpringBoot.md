# SpringBoot



## 1. application.properties에 jsp 설정

```xml
spring.mvc.view.prefix=/WEB-INF/views/
spring.mvc.view.suffix=.jsp
```

## 2. WEB-INF, views 생성 

## 3. pom.xml에 JSP 라이브러리 요청

```xml
		<dependency>
			<groupId>javax.servlet</groupId>
			<artifactId>jstl</artifactId>
		</dependency>
				<dependency>
			<groupId>org.apache.tomcat.embed</groupId>
			<artifactId>tomcat-embed-jasper</artifactId>
			<scope>provided</scope>
		</dependency>
```

## 4. index.html, memberInser.html 생성

-  window.open 

```html
onclick='window.open("html/memberInsert.html", "_blank", "toolbar=yes,scrollbars=yes,resizable=yes,top=100,left=300,width=400,height=800");'
```

- jquery, jquery cookie 라이브러리 등록

```xml
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.min.js" integrity="sha512-3j3VU6WC5rPQB4Ld1jnLV7Kd5xr+cq9avvhwqzbH/taCRNURoeEpoPBK9pDyeukwSxwRPJ8fDgvYXd6SkaZ2TA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>

```

