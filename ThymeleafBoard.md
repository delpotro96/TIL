# thymeleaf 게시판



## 플러그인

- Spring boot DevTools
- Lombok
- Spring Configuration Processor
- Spring Data JPA
- MyBatis Framework
- MariaDB Drivaer
- Thymeleaf
- Spring Web



## dependencies 에 Bootstrap 추가

``` xml
runtimeOnly 'org.webjars:bootstrap:4.5.0'
```



## 데이터 소스 설정

- application.properties
  - spring.jpa.hibernate.ddl-auto 는  JAVA의 Entity를 참고하여, Spring Boot 실행 시점에 자동으로 필요한 데이터베이스의 테이블 설정을 자동으로 해줍니다.

```properties
spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mariadb://localhost:????/board
spring.datasource.username= ???
spring.datasource.password= ???
spring.datasource.driver-class-name=org.mariadb.jdbc.Driver
```



## 게시판 목록 및 작성 페이지 만들기

- templates에 board & common 폴더 만들기

  - board 에는 게시판과 관련된 html 파일이 저장
  - common 에는 board에서 사용하는 공통적인 파일을 관리

  ### common/header.html 생성

```html
<div class="navbar navbar-dark bg-dark shadow-sm mb-3">
    <div class="container d-flex justify-content-between">
        <a href="/" class="navbar-brand d-flex align-items-center">
            <strong>게시판</strong>
        </a>
    </div>
</div>
```

	### 	board/list.html 생성

```html
<!DOCTYPE html>
<html lang="ko" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>게시판 - 목록</title>
    <link rel='stylesheet' href='/webjars/bootstrap/4.5.0/css/bootstrap.min.css'>
</head>
<body>
<header th:insert="common/header.html"></header>
<div class="container">
    <table class="table">
        <thead class="thead-light">
        <tr class="text-center">
            <th scope="col">#</th>
            <th scope="col">제목</th>
            <th scope="col">작성자</th>
            <th scope="col">작성일</th>
        </tr>
        </thead>
        <tbody>
        <tr class="text-center" th:each="post : ${postList}">
            <th scope="row">
                <span th:text="${post.id}"></span>
            </th>
            <td>
                <a th:href="@{'/post/' + ${post.id}}">
                    <span th:text="${post.title}"></span>
                </a>
            </td>
            <td>
                <span th:text="${post.author}"></span>
            </td>
            <td>
                <span th:text="${#temporals.format(post.createdDate, 'yyyy-MM-dd HH:mm')}"></span>
            </td>
        </tr>
        </tbody>
    </table>
    <div class="row">
        <div class="col-auto mr-auto"></div>
        <div class="col-auto">
            <a class="btn btn-primary" th:href="@{/post}" role="button">글쓰기</a>
        </div>
    </div>
</div>
<script src="/webjars/jquery/3.5.1/jquery.min.js"></script>
<script src="/webjars/bootstrap/4.5.0/js/bootstrap.min.js"></script>
</body>
</html>
```

### 	board/post.html 생성

```html
<!DOCTYPE html>
<html lang="ko" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>게시판 - 글쓰기</title>
    <link rel='stylesheet' href='/webjars/bootstrap/4.5.0/css/bootstrap.min.css'>
</head>
<body>
<header th:insert="common/header.html"></header>
<div class="container">
    <form action="/post" method="post">
        <div class="form-group row">
            <label for="inputTitle" class="col-sm-2 col-form-label"><strong>제목</strong></label>
            <div class="col-sm-10">
                <input type="text" name="title" class="form-control" id="inputTitle">
            </div>
        </div>
        <div class="form-group row">
            <label for="inputAuthor" class="col-sm-2 col-form-label"><strong>작성자</strong></label>
            <div class="col-sm-10">
                <input type="text" name="author" class="form-control" id="inputAuthor">
            </div>
        </div>
        <div class="form-group row">
            <label for="inputContent" class="col-sm-2 col-form-label"><strong>내용</strong></label>
            <div class="col-sm-10">
                <textarea type="text" name="content" class="form-control" id="inputContent"></textarea>
            </div>
        </div>
        <div class="row">
            <div class="col-auto mr-auto"></div>
            <div class="col-auto">
                <input class="btn btn-primary" type="submit" role="button" value="글쓰기">
            </div>
        </div>
    </form>
</div>
<script src="/webjars/jquery/3.5.1/jquery.min.js"></script>
<script src="/webjars/bootstrap/4.5.0/js/bootstrap.min.js"></script>
</body>
</html>
```



## 게시물 작성 구현하기

### 		Controller 구현

```java
@Controller
public class BoardController {

    @GetMapping("/")
    public String list(){
        return "board/list.html";
    }

    @GetMapping("/post")
    public String post(){
        return "board/post.html";
    }
}
```

- 글쓰기 페이지에서 글쓰기 버튼을 누르면 Post 요청이 옴
- Post 방식의 요청을 받아서, Service에서 처리되도록 할  것



### 	Entity 구현

#### 		domain/entity/Board 생성

<!-- 이 부분 공부 필요-->

```java 
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class) /* JPA에게 해당 Entity는 Auditiong 기능을 사용함을 알립니다. */
public class Board {

    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 10, nullable = false)
    private String author;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;

    @Builder
    public Board(Long id, String author, String title, String content) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.content = content;
    }
}
```



	### main 클래스에 @EnableJpaAuditing 

- JPA Auditing 기능 사용 위함

```java
@EnableJpaAuditing
```



### domain/repository/BoardRepository 구현

```java
public interface BoardRepository extends JpaRepository<Board, Long>{
    
}
```



### dto/BoardDTO 구현

<!-- 공부 필요 -->

- `toEntity()`는 DTO에서 필요한 부분을 빌더 패턴을 통해 Entity로 만드는 역할

```java
import com.example.boardpratice.domain.entity.Board;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoardDto {
    private Long id;
    private String author;
    private String title;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public Board toEntity() {
        Board build = Board.builder()
                .id(id)
                .author(author)
                .title(title)
                .content(content)
                .build();
        return build;
    }

    @Builder
    public BoardDto(Long id, String author, String title, String content, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }
}
```



### service/BoardService 구현

```java
import com.example.boardpratice.domain.Repository.BoardRepository;
import com.example.boardpratice.dto.BoardDto;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class BoardService {
    private BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository){
        this.boardRepository=boardRepository;
    }
    @Transactional
    public Long savePost(BoardDto boardDto) {
        return boardRepository.save(boardDto.toEntity()).getId();
    }
}
```



### Controller 수정

- 글쓰기 화면에서 버튼을 누르면 /post로 Post 요청이 옴

```java
import com.example.boardpratice.dto.BoardDto;
import com.example.boardpratice.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BoardController {

    private BoardService boardService;
    public BoardController(BoardService boardService){
        this.boardService=boardService;
    }

    @GetMapping("/")
    public String list(){
        return "board/list.html";
    }

    @GetMapping("/post")
    public String post(){
        return "board/post.html";
    }
    @PostMapping("/post")
    public String write(BoardDto boardDto){
        boardService.savePost(boardDto);
        return "redirect:/";
    }
}
```



## 게시물 목록 구현하기

### Service에 getBoardList() 구현

```java
@Transactional
    public List<BoardDto> getBoardList() {
        List<Board> boardList = boardRepository.findAll();
        List<BoardDto> boardDtoList = new ArrayList<>();
        for (Board board : boardList) {
            BoardDto boardDto = BoardDto.builder()
                    .id(board.getId())
                    .author(board.getAuthor())
                    .title(board.getTitle())
                    .content(board.getContent())
                    .createdDate(board.getCreatedDate())
                    .build();
            boardDtoList.add(boardDto);
        }
        return boardDtoList;
    }
```



### Controller list 수정

```java
 @GetMapping("/")
    public String list(Model model) {
        List<BoardDto> boardDtoList = boardService.getBoardList();
        model.addAttribute("postList", boardDtoList);
        return "board/list.html";
    }
```

​	

