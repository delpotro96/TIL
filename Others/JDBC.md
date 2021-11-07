# JDBC

- JDBC를 이용하기 위해서는 특정 드라이버를 설치하여 Java Libraries와 연동해야한다.



## MariaDB의 경우

```java
// 1. 드라이버 등록
Class.forName("org.mariadb.jdbc.Driver");

// 2. 연결
Connection con = DriverManager.getConnection("...")
    
// 3. Statement 생성
Statement st = con.createStatement();

// 4. SQL 전송
ResultSet rs = st.executeQuery("...");
    
```

