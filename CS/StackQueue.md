# Stack, Queue

### Stack

- 스택은 Last in First out



### Queue

- 큐는 First in First out



## 올바른 괄호

- input으로 괄호가 입력됨
- 올바른 괄호이면 YES, 아니면 NO 출력

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class CorrectBracket {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String s = br.readLine();
    System.out.println(solution(s));
  }

  public static String solution(String s) {
    String answer = "";
    Stack<Character> stack = new Stack<>();
    for (char x : s.toCharArray()) {
      if (x == '(') {
        stack.push(x);
      } else {
        if (stack.isEmpty()) {
          return "NO";
        }
        stack.pop();
      }
    }
    if(stack.isEmpty()){
      answer = "YES";
    } else{
      answer = "NO";
    }

    return answer;
  }
}
```



## 괄호 문자 제거

- input으로 String이 입력됨
- 소괄호 사이에 존재하는 모든 문자는 제거하고 남은 문자만 출력



### 내가 쓴 답

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class RemoveBracketWord {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String s = br.readLine();
    System.out.println(solution(s));
  }

  public static String solution(String s) {
    String answer = "";
    Stack<Character> stack = new Stack<>();

    for (char x : s.toCharArray()) {
      if (x == '(') {
        stack.push(x);
      } else if (x == ')') {
        stack.pop();
      }

      if (stack.isEmpty()&&x!=')') {
        answer += x;
      }
    }

    return answer;
  }
}
```



### 다른 방식

- 닫는 괄호가 아닐시 모두 push 함
- 닫는 괄호를 만나면 여는 괄호를 만날 때 까지 pop

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class RemoveBracketWord {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String s = br.readLine();
    System.out.println(solution(s));
  }

  public static String solution(String s) {
    String answer = "";
    Stack<Character> stack = new Stack<>();

    for (char x : s.toCharArray()) {
        if(x==')'){
          while(stack.pop()!='(');
        } else {
          stack.push(x);
        }
    }

    for(int i = 0; i < stack.size(); i++){
      answer += stack.get(i);
    }

    return answer;
  }
}
```

