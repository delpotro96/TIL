# Stack, Queue

### Stack

- 스택은 Last in First out

- push
- pop
- peek -> 스택을 꺼내지는 않고 확인만 함

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



## 크레인 인형뽑기

- [코딩테스트 연습 - 크레인 인형뽑기 게임 | 프로그래머스 (programmers.co.kr)](https://programmers.co.kr/learn/courses/30/lessons/64061)



```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class PickDoll {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    int[][] array = new int[N][N];

    for (int i = 0; i < N; i++) {
      String[] temp = br.readLine().split("\\s+");
      for (int j = 0; j < N; j++) {
        array[i][j] = Integer.parseInt(temp[j]);
      }
    }

    int M = Integer.parseInt(br.readLine());
    String[] temp2 = br.readLine().split("\\s+");
    int[] moves = new int[M];
    for (int i = 0; i < M; i++) {
      moves[i] = Integer.parseInt(temp2[i]);
    }
    System.out.println(solution(N, array, M, moves));
  }

  public static int solution(int N, int array[][], int M, int moves[]) {
    int answer = 0;
    Stack<Integer> stack = new Stack<>();

    for (int pos : moves) {
      for (int i = 0; i < array.length; i++) {
        if (array[i][pos - 1] != 0) {
          int tmp = array[i][pos - 1];
          array[i][pos - 1] = 0;
          if (!stack.isEmpty() && tmp == stack.peek()) {
            answer += 2;
            stack.pop();
          } else {
            stack.push(tmp);
          }
          break;
        }
      }
    }

    return answer;
  }
}

```



## 후위식 연산

- 후위연산식이 주어지면 해결하는 코드를 작성하세요
- 5 + 3   ->  53+
- 352+*9-  ->  (5+2) * 3 - 9 



```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class PostFix {
  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String s = br.readLine();
    System.out.println(solution(s));
  }

  public static int solution(String s) {
    int answer = 0;

    Stack<Integer> stack = new Stack<>();

    for (char x : s.toCharArray()) {

        /**
        Character.isDigit  - > 캐릭터가 숫자인지
        **/
      if (Character.isDigit(x)) {
        stack.push(x - 48);
      } else {
        int rt = stack.pop();
        int lt = stack.pop();
        if(x == '+'){
          stack.push(lt+rt);
        } else if ( x == '-'){
          stack.push(lt-rt);
        } else if ( x == '*'){
          stack.push(lt*rt);
        } else if ( x == '/'){
          stack.push(lt/rt);
        }
      }
    }
    answer=stack.pop();

    return answer;
  }
}
```

