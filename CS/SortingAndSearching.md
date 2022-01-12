# SortingAndSearching



## 선택정렬

- 13, 5, 11, 7, 23, 15 라는 입력이 들어오고 정렬해야 할 때
- int i = 0; 
- 인덱스 i부터 하나하나 모든 입력을 탐색하여 정렬

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SelectSort {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N = Integer.parseInt(br.readLine());
    String[] temp = br.readLine().split("\\s+");
    int[] array = new int[N];

    for (int i = 0; i < N; i++) {
      array[i] = Integer.parseInt(temp[i]);
    }

    for (int i = 0; i < N; i++) {
      System.out.print(solution(N, array)[i] + " ");
    }
  }

  public static int[] solution(int N, int[] array) {

    for (int i = 0; i < N; i++) {
      int temp = array[i];
      int idx = i;
      for (int j = i + 1; j < N; j++) {
        if (array[j] < temp) {
          temp = array[j];
          idx = j;
        }
      }
      array[idx] = array[i];
      array[i] = temp;
    }

    return array;
  }
}
```



## 버블정렬

- 버블정렬은 이웃한 두 수를 비교해서 swap하는 방식

- 한번 정렬하면 가장 큰 수가 가장 뒤로 밀려남
- 정렬할 때마다 비교횟수가 1씩 줄어듬



```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BubbleSort {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    String[] temp = br.readLine().split("\\s+");
    int[] array = new int[N];

    for (int i = 0; i < N; i++) {
      array[i] = Integer.parseInt(temp[i]);
    }

    for (int i = 0; i < N; i++) {
      System.out.print(solution(N, array)[i] + " ");
    }
  }

  public static int[] solution(int N, int[] array) {

    int temp = N;
    int tmp = 0;
    for (int i = 0; i < N - 1; i++) {
      for (int j = 0; j < temp - 1; j++) {
        if (array[j + 1] < array[j]) {
          tmp = array[j];
          array[j] = array[j + 1];
          array[j + 1] = tmp;
        }
      }
      temp--;
    }

    return array;
  }
}
```



## 삽입정렬



```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InsertSort {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    String[] temp = br.readLine().split("\\s+");
    int[] array = new int[N];

    for (int i = 0; i < N; i++) {
      array[i] = Integer.parseInt(temp[i]);
    }

    for (int i = 0; i < N; i++) {
      System.out.print(solution(N, array)[i] + " ");
    }
  }

  public static int[] solution(int N, int[] array) {
    for (int i = 1; i < N; i++) {
      int tmp = array[i];
      int j;
      for (j = i - 1; j >= 0; j--) {
        if (array[j] > tmp) {
          array[j + 1] = array[j];
        } else {
          break;
        }
      }
      array[j + 1] = tmp;
    }

    return array;
  }
}
```



## Least Recently Used

- 캐시메모리는 CPU와 주기억장치(DRAM) 사이의 고속의 임시 메모리로서 CPU가 처리할 작업을 저장해 놓았다가

  필요할 바로 사용해서 처리속도를 높이는 장치이다. 워낙 비싸고 용량이 작아 효율적으로 사용해야 한다.

  철수의 컴퓨터는 캐시메모리 사용 규칙이 LRU 알고리즘을 따른다.

  LRU 알고리즘은 Least Recently Used 의 약자로 직역하자면 가장 최근에 사용되지 않은 것 정도의 의미를 가지고 있습니다.

![image-20220112234417540](C:\Users\Kyunghun Lee\AppData\Roaming\Typora\typora-user-images\image-20220112234417540.png)



```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LeastRecentlyUsed {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    String[] tmp = br.readLine().split("\\s+");
    int N = Integer.parseInt(tmp[0]);
    int S = Integer.parseInt(tmp[1]);

    String[] temp = br.readLine().split("\\s+");
    int[] array = new int[S];

    for (int i = 0; i < S; i++) {
      array[i] = Integer.parseInt(temp[i]);
    }

    for (int i = 0; i < N; i++) {
      System.out.print(solution(S, N, array)[i] + " ");
    }
  }

  public static int[] solution(int S, int N, int[] array) {

    int[] answer = new int[S];
    for (int x : array) {
      int pos = -1;
      for (int i = 0; i < S; i++) {
        if (x == answer[i]) {
          pos = i;
        }
      }
      if (pos == -1) {
        for (int i = S - 1; 0 < i; i--) {
          answer[i] = answer[i - 1];
        }
      } else {
        for (int i = pos; 0 < i; i--) {
          answer[i] = answer[i - 1];
        }
      }
      answer[0] = x;
    }

    return answer;
  }
}
```

