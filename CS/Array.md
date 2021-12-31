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

