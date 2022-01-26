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



## 결혼식

- 현수는 다음 달에 결혼을 합니다.

  현수는 결혼식 피로연을 장소를 빌려 3일간 쉬지 않고 하려고 합니다.

  피로연에 참석하는 친구들 N명의 참석하는 시간정보를 현수는 친구들에게 미리 요구했습니다.

  각 친구들은 자신이 몇 시에 도착해서 몇 시에 떠날 것인지 현수에게 알려주었습니다.

  현수는 이 정보를 바탕으로 피로연 장소에 동시에 존재하는 최대 인원수를 구하여 그 인원을 수용할 수 있는 장소를 빌리려고 합니다. 여러분이 현수를 도와주세요.

  만약 한 친구가 오는 시간 13, 가는시간 15라면 이 친구는 13시 정각에 피로연 장에 존재하는 것이고 15시 정각에는 존재하지 않는다고 가정합니다.

- 첫째 줄에 피로연에 참석할 인원수 N(5<=N<=100,000)이 주어집니다.

  두 번째 줄부터 N줄에 걸쳐 각 인원의 오는 시간과 가는 시간이 주어집니다.

  시간은 첫날 0시를 0으로 해서 마지막날 밤 12시를 72로 하는 타임라인으로 오는 시간과 가는 시간이 음이 아닌 정수로 표현됩니다.



```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class Wedding {
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

    Arrays.sort(
        array,
        new Comparator<>() {
          @Override
          public int compare(int[] o1, int[] o2) {
            if (o1[0] == o2[0]) {
              return o1[1] - o2[1];
            } else {
              return o1[0] - o2[0];
            }
          }
        });

    int max = 0;

    int time = 0;
    while (time <= 72) {
      for (int i = 0; i < N; i++) {
        if (array[i][0] <= time && time < array[i][1]) {
          answer++;
        }
      }
      max = Math.max(answer, max);
      answer = 0;
      time++;
    }
    return max;
  }
}
```

