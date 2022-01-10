# Stack, Queue

### Stack

- 스택은 Last in First out

- push
- pop
- peek -> 가장 위에 있는 값을 확인만 함

### Queue

- 큐는 First in First out
- offer
- poll
- peek -> 가장 앞에 있는 값을 확인만 함



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



## 쇠 막대기

- [10799번: 쇠막대기 (acmicpc.net)



```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class MetalStick {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String s = br.readLine();
    System.out.println(solution(s));
  }

  public static int solution(String s) {
    int answer = 0;
    Stack<Character> stack = new Stack<>();
    for(int i = 0; i < s.length(); i++){
      if(s.charAt(i)=='('){
        stack.push(s.charAt(i));
      }else if(s.charAt(i)==')'){
        if(s.charAt(i-1)=='('){
          stack.pop();
          answer += stack.size();
        } else if( s.charAt(i-1)==')'){
          stack.pop();
          answer += 1;
        }
      }

    }

    return answer;
  }
}
```



## 공주 구하기

- input으로 N과 M이 주어짐
- 1번부터 N번까지 계속 순환하며 번호를 외치고 M을 외치면 구조팀에서 제외됨
- 다음 사람이 1부터 다시 외침 
- 한명이 남을 때 까지 계속 하다가 마지막 한명 리턴



```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class ResquePrincess {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] temp = br.readLine().split("\\s+");

    int N = Integer.parseInt(temp[0]);
    int M = Integer.parseInt(temp[1]);
    System.out.println(solution(N, M));
  }

  public static int solution(int N, int M) {
    int answer = 0;

    Queue<Integer> q = new LinkedList<>();

    for (int i = 1; i <= N; i++) {
      q.offer(i);
    }

    while (!q.isEmpty()) {
      for (int i = 1; i < M; i++) {
        q.offer(q.poll());
      }
      q.poll();
      if (q.size() == 1) {
        answer = q.poll();
      }
    }

    return answer;
  }
}
```



## 교육과정 설계

- input으로 필수과목과 누군가의 수강계획이 String으로 입력됨
- 누군가의 수강계획에 필수과목이 입력되 있으며 순서가 올바르게 되어있다면 return "YES" else "NO"



### 내가 작성한 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class SetUpStudy {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String s1 = br.readLine();
    String s2 = br.readLine();

    System.out.println(solution(s1, s2));
  }

  public static String solution(String s1, String s2) {
    String answer = "";
    ArrayList<Character> list = new ArrayList<>();

    for (char x : s1.toCharArray()) {
      list.add(x);
    }

    for (char x : s2.toCharArray()) {
      if (list.contains(x)) {
        answer += x;
      }
    }

    if(answer.equals(s1)){
      return "YES";
    } else {
      return "NO";
    }
  }
}

```



### Queue 를 사용한 코드

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class SetUpStudy {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String s1 = br.readLine();
    String s2 = br.readLine();

    System.out.println(solution(s1, s2));
  }

  public static String solution(String s1, String s2) {
    String answer = "";

    Queue<Character> queue = new LinkedList<>();

    for (char x : s1.toCharArray()) {
      queue.offer(x);
    }
    for (char x : s2.toCharArray()) {
      if (queue.contains(x)) {
        if (queue.poll() != x) {
          return "NO";
        }
      }
    }
    if(queue.isEmpty()){
      return "YES";
    }else{
      return "NO";
    }
  }
}
```



## 응급실

- input으로 환자 수 N과 특정환자의 인덱스 M, 환자 위험도 리스트 array가 주어진다
- 환자 순서 리스트의 순서대로 호명이 된다. 만약 리스트에 위험도가 더 높은 환자가 존재할 시 순서는 가장 뒤로 밀려난다.
- M 환자는 몇 번째로 치료 될지 리턴

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class EmergencyRoom {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] temp = br.readLine().split("\\s+");
    int N = Integer.parseInt(temp[0]);
    int M = Integer.parseInt(temp[1]);

    String[] temp2 = br.readLine().split("\\s+");
    int[] array = new int[N];

    for (int i = 0; i < N; i++) {
      array[i] = Integer.parseInt(temp2[i]);
    }

    System.out.println(solution(N, M, array));
  }

  public static int solution(int N, int M, int[] array) {
    int answer = 1;

    Queue<Person> queue = new LinkedList<>();
    for (int i = 0; i < N; i++) {
      queue.offer(new Person(i, array[i]));
    }

    while (!queue.isEmpty()) {
      Person temp = queue.poll();
      for (Person x : queue) {
        if (x.priority > temp.priority) {
          queue.offer(temp);
          temp = null;
          break;
        }
      }
      if (temp != null) {
        if (temp.id == M) {
          return answer;
        } else {
          answer++;
        }
      }
    }

    return answer;
  }
}

class Person {
  int id;
  int priority;

  public Person(int id, int priority) {
    this.id = id;
    this.priority = priority;
  }
}

```



 
