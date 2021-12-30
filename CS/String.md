# java



## 대문자는 소문자로, 소문자는 대문자로 

```java
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    String word = sc.next();
    System.out.println(solution(word));
  }

  public static String solution(String word) {

    String answer = "";
    for (char x : word.toCharArray()) {
      if (Character.isLowerCase(x)) {
        answer += Character.toUpperCase(x);
      } else {
        answer += Character.toLowerCase(x);
      }
    }

    return answer;
  }
}
```



## 문장 중 가장 긴 단어

```java
public static String solution(String some) {

    String[] splited = some.split("\\s+");
    String answer = "";
    for (String x : splited) {
      if (answer.length() < x.length()) {
        answer = x;
      }
    }
```





## 단어 뒤집기

### 내가 쓴 답

```java
public class ReverseWord {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int N = sc.nextInt();
    for (int i = 0; i < N; i++) {
      String word = sc.next();
      System.out.println(solution(word));
    }
  }

  public static String solution(String word) {
    String answer = "";

    for (int i = 0; i < word.length(); i++) {
      answer += word.charAt(word.length() - i - 1);
    }

    return answer;
  }
}
```



### StringBuilder 사용

```java
public class ReverseWord {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int N = sc.nextInt();
    for (int i = 0; i < N; i++) {
      String word = sc.next();
      System.out.println(solution(word));
    }
  }

  public static String solution(String word) {
    String answer = "";
    for(int i = 0; i < word.length(); i++){
      answer = new StringBuilder(word).reverse().toString();
    }
    return answer;
  }
}

```



### 특정 문자 뒤집기

```java
public class ReverseSomeWord {
  public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);
      String word = sc.next();
      System.out.println(solution(word));
  }
    
  public String solution(String str){
		String answer;
		char[] s=str.toCharArray();
		int lt=0, rt=str.length()-1;
		while(lt<rt){
			if(!Character.isAlphabetic(s[lt])) lt++;
			else if(!Character.isAlphabetic(s[rt])) rt--;
			else{
				char tmp=s[lt];
				s[lt]=s[rt];
				s[rt]=tmp;
				lt++;
				rt--;
			}
		}
		answer=String.valueOf(s);
		return answer;
	}
}
```



## 중복 문자 제거

- 중복이 제거된 문자열의 각 문자는 원래 문자열의 순서를 유지한다 
- ex) input = ksstkt,    output = kst



### indexOf 사용

```java
public class DeleteDuplication {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    String word = sc.next();
    System.out.println(solution(word));
  }

  public static String solution(String word) {
    String answer = "";
	for(int i = 0; i < word.length(); i++){
        if(word.indexOf(word.charAt(i))==i){
            answer+=word.charAt(i);
        }
    }
    return answer;
  }
}
```





### Stream() 사용 

```java
public class DeleteDuplication {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    String word = sc.next();
    System.out.println(solution(word));
  }

  public static String solution(String word) {
    String answer = "";

    List<Character> list = new ArrayList<>();

    for (char x : word.toCharArray()) {
      list.add(x);
    }
    List<Character> newList = list.stream().distinct().collect(Collectors.toList());

    for (char x : newList) {
      answer += x;
    }

    return answer;
  }
}
```



## 대문자 알파벳만 남기기

```java
String word = "Hello, World!!!";

String newWord = word.toUpperCase().replaceAll("[^A-Z]", "");
// A-Z가 아닌 것들을 빈문자열로 replace
```



## 문자열 압축

- ex) input : KKHSSSSSSSE, output : K2HS7E

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CompressWord {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String word = br.readLine();
    System.out.println(solution(word));
  }

  public static String solution(String word) {

    String answer = "";
    word = word + " ";
    int cnt = 1;
    for (int i = 0; i < word.length() - 1; i++) {
      if (word.charAt(i) == word.charAt(i + 1)) {
        cnt++;
      } else {
        answer += word.charAt(i);
        if (cnt > 1) {
          answer += cnt;
          cnt = 1;
        }
      }
    }
    return answer;
  }
}
```



## 암호 해석하기 ( 2진수 > 10진수, ASCII > String)

### 내가 쓴 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class SomeCode {
    
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    String word = br.readLine();
    System.out.println(solution(word));
  }

  public static String solution(String word) {

    String answer = "";

    ArrayList<String> list = new ArrayList<>();

    for (char x : word.toCharArray()) {
      if (x == '#') {
        x = '1';
      } else {
        x = '0';
      }
      answer += x;
    }

    String temp = "";

    for (int i = 0; i < answer.length(); i++) {
      temp += answer.charAt(i);
      if (i % 7 == 6) {
        list.add(temp);
        temp = "";
      }
    }
    answer = "";
    for (String x : list) {
      int tempint = Integer.parseInt(x, 2);
      char tmp = (char) tempint;
      x = String.valueOf(tmp);
      answer += x;
    }

    return answer;
  }
}
```



### subString 이용

```java
public class SomeCode {
    
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    String word = br.readLine();
    System.out.println(solution(word));
  }

  public static String solution(String word) {
	String answer = "";
    for(int i = 0; i < n; i ++){
        String tmp = word.substring(0, 7).replace('#', '1').replace('*', '0');
        int num = Integer.parseInt(tmp, 2);
        answer += (char)num;
        word = word.substring(7);
    }
    return answer;   
  }
}
```

