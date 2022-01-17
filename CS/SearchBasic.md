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

