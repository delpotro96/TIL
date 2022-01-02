# Array



## 점수 계산

- 연속으로 문제가 맞는 경우 두 번째 문제는 2점 ... 열 번째 문제는 10점으로 계산한다.
- ![image-20211231154810706](C:\Users\Kyunghun Lee\AppData\Roaming\Typora\typora-user-images\image-20211231154810706.png)

```
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ScoreCal {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N = Integer.parseInt(br.readLine());
    String[] temp = br.readLine().split("\\s+");
    int[] array = new int[N];
    for (int i = 0; i < N; i++) {
      array[i] = Integer.parseInt(temp[i]);
    }
    System.out.println(solution(N, array));
  }

  public static int solution(int N, int[] array) {
    int answer = 0;
    int[] score = new int[N];
    int temp = 0;

    if (array[0] == 1) {
      score[0] = 1;
    } else {
      score[0] = 0;
    }

    for (int i = 1; i < N; i++) {
      if (array[i] == 1) {
        if (score[i - 1] == 0) {
          score[i] = 1;
        } else {
          score[i] = score[i - 1] + 1;
        }
      } else {
        score[i] = 0;
      }
    }

    return Arrays.stream(score).sum();
  }
}
```



## 등수 구하기



-  같은 점수인 경우 공동 x 등

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class RankCal {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader( System.in));

    int N = Integer.parseInt(br.readLine());
    String[] temp = br.readLine().split("\\s+");
    int[] array = new int[N];
    for (int i = 0; i < N; i++) {
      array[i] = Integer.parseInt(temp[i]);
    }

    System.out.println(solution(N, array));
  }

  public static String solution(int N, int[] array) {
    String answer = "";

    ArrayList<Integer> list = new ArrayList<>();
    for (int x : array) {
      list.add(x);
    }
    Collections.sort(list, Collections.reverseOrder());

    for(int i = 0; i < N; i++){
      for(int j = 0; j < N; j++){
        if(array[i] == list.get(j)){
          answer += (j+1) + " ";
          break;
        }
      }
    }

    return answer;
  }
}
```



## 바둑판 합



- 각 행의 합, 각 열의 합, 두 대각선의 합 중 가장 큰 값 리턴
- Math.max() 는 두 인자 중 큰 값 Math.min() 은 두 인자 중 작은 값 리턴

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MostBigLine {
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
    System.out.println(solution(N, array));
  }

  public static int solution(int N, int[][] array) {
    int answer = -1;
    int sum = 0;
    int sum2 = 0;

    /** 행, 열 합 구하는 반복문 */
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        sum += array[i][j];
        sum2 += array[j][i];
      }
      if (answer < sum || answer < sum2) {
        answer = Math.max(sum, sum2);
      }
      sum = 0;
      sum2 = 0;
    }

    /** 대각 배열 합 */
    for (int i = 0; i < N; i++) {
      sum += array[i][i];
      sum2 += array[N - 1 - i][i];
    }
    if (answer < sum || answer < sum2) {
      answer = Math.max(sum, sum2);
    }

    return answer;
  }
}
```



## 봉우리 계산

![image-20220101222709403](C:\Users\Kyunghun Lee\AppData\Roaming\Typora\typora-user-images\image-20220101222709403.png)

- 상하좌우보다 커야 봉우리이다. 
- 이차원 배열(가장자리는 0)이 주어지면 봉우리 개수 리턴



```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Bonguri {
  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    int[][] array = new int[N + 2][N + 2];

    /** array 배열 가장자리에 0 주입 */
    for (int i = 0; i < N + 2; i++) {
      array[0][i] = 0;
      array[N + 1][i] = 0;
      array[i][0] = 0;
      array[i][N + 1] = 0;
    }

    /** array에 값 주입 */
    for (int i = 1; i < N + 1; i++) {
      String[] temp = br.readLine().split("\\s+");
      for (int j = 1; j < N + 1; j++) {
        array[i][j] = Integer.parseInt(temp[j - 1]);
      }
    }

    System.out.println(solution(N,array));
  }

  public static int solution(int N, int[][] array) {
    int answer = 0;

    for (int i = 1; i < N + 1; i++) {
      for (int j = 1; j < N + 1; j++) {
        if (array[i - 1][j] < array[i][j]
            && array[i][j + 1] < array[i][j]
            && array[i + 1][j] < array[i][j]
            && array[i][j - 1] < array[i][j]) {
          answer++;
        }
      }
    }

    return answer;
  }
}
```





## 임시반장 정하기

- 6학년 반장을 정함
- 학생 중 1 ~ 5학년 까지 한번이라도 같은 반이었던 사람이 가장 많은 학생이 임시 반장



```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SelectLeader {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N = Integer.parseInt(br.readLine());
    int[][] array = new int[N][5];
    for (int i = 0; i < N; i++) {
      String[] temp = br.readLine().split("\\s+");
      for (int j = 0; j < 5; j++) {
        array[i][j] = Integer.parseInt(temp[j]);
      }
    }
    System.out.println(solution(N, array));
  }

  public static int solution(int N, int[][] array) {
    int answer = 0;
    int max = 0;

    for (int i = 0; i < N; i++) {
      int count = 0;
      for (int j = 0; j < N; j++) {
        for (int k = 0; k < 5; k++) {
          if (array[i][k] == array[j][k]) {
            count++;
            break;
          }
        }
      }
      if (max < count) {
        max = count;
        answer = i + 1;
      }
    }

    return answer;
  }
}
```



## 멘토 구하기

![image-20220102211627428](C:\Users\Kyunghun Lee\AppData\Roaming\Typora\typora-user-images\image-20220102211627428.png)

- 이러한 input이 주어지면 4는 학생 수, 3은 시험의 수
- 3, 4, 1, 2가 주어지면 3번 학생이 1등, 1번 학생이 3등
- 멘토와 멘티가 짝이 되는 경우의 수를 구해야 함
- 멘토는 멘티보다 모든 테스트에서 앞서야 함

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Mento {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] temp = br.readLine().split("\\s+");
    int N = Integer.parseInt(temp[0]);
    int M = Integer.parseInt(temp[1]);

    int[][] array = new int[M][N];
    for (int i = 0; i < M; i++) {
      String[] temp2 = br.readLine().split("\\s+");
      for (int j = 0; j < N; j++) {
        array[i][j] = Integer.parseInt(temp2[j]);
      }
    }
    System.out.println(solution(N, M, array));
  }

  public static int solution(int N, int M, int[][] array) {
    int answer = 0;
    /** N은 학생 수 M은 테스트 수 */
    for (int i = 1; i <= N; i++) {
      for (int j = 1; j <= N; j++) {
        int count = 0;
        for (int k = 0; k < M; k++) {
          int pi = 0, pj = 0;
          for (int s = 0; s < N; s++) {
            if (array[k][s] == i) {
              pi = s;
            }
            if (array[k][s] == j) {
              pj = s;
            }
          }
          if (pi < pj) {
            count++;
          }
        }
        if (count == M) {
          answer++;
        }
      }
    }

    return answer;
  }
}
```
