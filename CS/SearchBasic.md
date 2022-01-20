# Recursive, Tree, Graph



## 재귀함수

- 자연수 N이 입력되면 재귀함수를 이용하여 1부터 N까지를 출력하시오

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Recursive {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N = Integer.parseInt(br.readLine());
    solution(N);
  }

  public static void solution(int N) {
    int tmp = N;

    if (N == 0) {
    } else {
      solution(N - 1);
      System.out.print(N + " ");
    }
  }
}
```



- 스택프레임

- `System.out.print(N+" ")`이 실행되지 않고 Stack에 저장되어 있다가

  한번에 호출되어 1부터 출력됨



## 재귀함수를 이용한 이진수 출력

- 10진수 N이 입력되면 2진수로 변환하여 출력하라(재귀함수를 이용하여)

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DFSTwoByte {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    solution(N);
  }

  public static void solution(int N) {
    if (N == 0) {

    } else {
      solution(N / 2);
      System.out.print(N % 2 + " ");
    }
  }
}
```



## 팩토리얼

- 자연수 N이 입력되면 N!을 구하는 프로그램을 작성하라(재귀함수를 이용하여)



```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Factorial {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    System.out.println(solution(N));
  }

  public static int solution(int N) {
    if (N == 1) {
      return 1;
    } else {
      return N * solution(N - 1);
    }
  }
}
```



## 피보나치 재귀

- 재귀함수를 사용하여 피보나치 출력

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FiboDFS {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    fibo = new int[N + 1];
    solution(N);
    for (int i = 1; i < N; i++) {
      System.out.print(fibo[i] + " ");
    }
  }

  public static int solution(int N) {
    if (N == 1) {
      return fibo[N] = 1;
    } else if (N == 2) {
      return fibo[N] = 1;
    } else {
      return fibo[N] = solution(N - 2) + solution(N - 1);
    }
  }

  static int[] fibo;
}
```



## 이진트리 순회(DFS)

![image-20220118222316315](C:\Users\Kyunghun Lee\AppData\Roaming\Typora\typora-user-images\image-20220118222316315.png)

- 전위순회는 부모>왼쪽>오른쪽
- 중위순회는 왼쪽>부모>오른쪽
- 후위순회는 왼쪽>오른쪽>부모

### 전위순회

```java
public class DFSPratice {
  Node root;

  public static void main(String[] args) {
    DFSPratice tree = new DFSPratice();
    tree.root = new Node(1);
    tree.root.lt = new Node(2);
    tree.root.rt = new Node(3);
    tree.root.lt.lt = new Node(4);
    tree.root.lt.rt = new Node(5);
    tree.root.rt.lt = new Node(6);
    tree.root.rt.rt = new Node(7);
    tree.solution(tree.root);
  }

  public void solution(Node root) {
    if (root == null) {
      return;
    } else {
      System.out.println(root.data);
      solution(root.lt);
      solution(root.rt);
    }
  }
}

class Node {
  int data;
  Node lt, rt;

  public Node(int val) {
    data = val;
    lt = rt = null;
  }
}
```



### 중위순회

```java
public class DFSPratice {
  Node root;

  public static void main(String[] args) {
    DFSPratice tree = new DFSPratice();
    tree.root = new Node(1);
    tree.root.lt = new Node(2);
    tree.root.rt = new Node(3);
    tree.root.lt.lt = new Node(4);
    tree.root.lt.rt = new Node(5);
    tree.root.rt.lt = new Node(6);
    tree.root.rt.rt = new Node(7);
    tree.solution(tree.root);
  }

  public void solution(Node root) {
    if (root == null) {
      return;
    } else {
      solution(root.lt);
      System.out.println(root.data);
      solution(root.rt);
    }
  }
}

class Node {
  int data;
  Node lt, rt;

  public Node(int val) {
    data = val;
    lt = rt = null;
  }
}
```



### 후위순회

```java
public class DFSPratice {
  Node root;

  public static void main(String[] args) {
    DFSPratice tree = new DFSPratice();
    tree.root = new Node(1);
    tree.root.lt = new Node(2);
    tree.root.rt = new Node(3);
    tree.root.lt.lt = new Node(4);
    tree.root.lt.rt = new Node(5);
    tree.root.rt.lt = new Node(6);
    tree.root.rt.rt = new Node(7);
    tree.solution(tree.root);
  }

  public void solution(Node root) {
    if (root == null) {
      return;
    } else {
      solution(root.lt);
      solution(root.rt);        
      System.out.println(root.data);
    }
  }
}

class Node {
  int data;
  Node lt, rt;

  public Node(int val) {
    data = val;
    lt = rt = null;
  }
}
```



## 부분집합 구하기(DFS)

- 자연수 N이 주어지면 1부터 N까지의 원소를 갖는 집합의 부분집합을 모두 출력하라
- 공집합은 출력하지 않는다

```java
package algorithm;

import java.util.*;

class SubSetDFS {
  static int n;
  static int[] ch;

  public void DFS(int L) {
    if (L == n + 1) {
      String tmp = "";
      for (int i = 1; i <= n; i++) {
        if (ch[i] == 1) tmp += (i + " ");
      }
      if (tmp.length() > 0) System.out.println(tmp);
    } else {
      ch[L] = 1;
      DFS(L + 1);
      ch[L] = 0;
      DFS(L + 1);
    }
  }

  public static void main(String[] args) {
    SubSetDFS T = new SubSetDFS();
    n = 3;
    ch = new int[n + 1];
    T.DFS(1);
  }
}

```



## 송아지 찾기(BFS)

- 현수는 송아지를 찾아야 한다. 현수의 위치와 송아지의 위치가 주어진다 
- 현수는 앞으로 1, 뒤로 1, 앞으로 5를 이동할 수 있다
- 현수가 송아지의 위치까지 갈 수 있는 최소의 이동횟수를 구하시오



```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class FindCalf {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] tmp = br.readLine().split("\\s+");

    int S = Integer.parseInt(tmp[0]);
    int T = Integer.parseInt(tmp[1]);

    System.out.println(solution(S, T));
  }

  public static int solution(int S, int T) {
    int[] dis = {1, -1, 5};
    int[] ch;
    ch = new int[10001];
    ch[S] = 1;
    Queue<Integer> q = new LinkedList<>();
    q.offer(S);
    int L = 0;
    while (!q.isEmpty()) {
      int len = q.size();
      for (int i = 0; i < len; i++) {
        int x = q.poll();
        for (int j = 0; j < 3; j++) {
          int nx = x + dis[j];
          if (nx == T) {
            return L + 1;
          }
          if (1 <= nx && nx <= 10000 && ch[nx] == 0) {
            ch[nx] = 1;
            q.offer(nx);
          }
        }
      }
      L++;
    }

    return 0;
  }
}
```

 

## 합이 같은 부분집합(DFS)

- N개의 원소로 구성된 자연수 집합이 주어진다

- 집합을 두개의 부분집합으로 나누었을 때 두 부분집합의 원소의 합이 서로 같은 경우가 존재하면
  YES else NO 리턴하시오

  ```java
  import java.io.BufferedReader;
  import java.io.IOException;
  import java.io.InputStreamReader;
  
  public class SameSumSubset {
    static String answer = "NO";
    static int N, total = 0;
    static boolean flag = false;
  
    public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      N = Integer.parseInt(br.readLine());
      String[] tmp = br.readLine().split("\\s+");
      int[] array = new int[N];
      for (int i = 0; i < N; i++) {
        array[i] = Integer.parseInt(tmp[i]);
        total += array[i];
      }
      solution(0, 0, array);
      System.out.println(answer);
    }
  
    public static void solution(int L, int sum, int[] arr) {
      if (flag) return;
      if (sum > total / 2) return;
      if (L == N) {
        if ((total - sum) == sum) {
          answer = "YES";
          flag = true;
        }
      } else {
        solution(L + 1, sum + arr[L], arr);
        solution(L + 1, sum, arr);
      }
    }
  }
  ```

  

