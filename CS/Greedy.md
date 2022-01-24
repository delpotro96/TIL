# Greedy



## 씨름 선수

- 씨름선수 지원자의 수와 각 지원자의 키, 몸무게가 주어진다
- 지원자가 다른 모든 지원자와 일대일 비교해서 키와 몸무게가 모두 높은 지원자가 존재하면 탈락한다
- 몇 명의 선수를 선발할 수 있는가

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class WrestlingPlayer {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    int[][] array = new int[N][2];
      
    for (int i = 0; i < N; i++) {
      String[] tmp = br.readLine().split("\\s+");
      array[i][0] = Integer.parseInt(tmp[0]);
      array[i][1] = Integer.parseInt(tmp[1]);
    }
      
    System.out.println(solution(N, array));
  }

  public static int solution(int N, int[][] array) {
    int answer = 0;
      
    for (int i = 0; i < N; i++) {
      boolean check = true;
      for (int j = 0; j < N; j++) {
        if (array[i][0] < array[j][0] && array[i][1] < array[j][1]) {
          check = false;
          break;
        }
      }
      if (check) {
        answer++;
      }
    }
    return answer;
  }
}

```



## 회의실 배정

- N개의 회의들에 대해 시작 시간과 끝나는 시간이 주어짐
- 각 회의가 겹치지 않게 하면서 회의실을 사용할 수 있는 최대수의 회의를 리턴

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class ConferenceRoom {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    int[][] array = new int[N][2];
    for (int i = 0; i < N; i++) {
      String[] tmp = br.readLine().split("\\s+");
      array[i][0] = Integer.parseInt(tmp[0]);
      array[i][1] = Integer.parseInt(tmp[1]);
    }
    System.out.println(solution(N, array));
  }

  public static int solution(int N, int[][] array) {
    int answer = 1;

    Arrays.sort(
        array,
        new Comparator<int[]>() {
          @Override
          public int compare(int[] o1, int[] o2) {
            if (o1[1] == o2[1]) {
              return o1[0] - o2[0];
            } else {
              return o1[1] - o2[1];
            }
          }
        });
    int pre = array[0][1];
    for (int i = 1; i < N; i++) {
      if (pre <= array[i][0]) {
        answer++;
        pre = array[i][1];
      }
    }
    return answer;
  }
}
```

