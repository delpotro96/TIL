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



## 로프

- N(1 ≤ N ≤ 100,000)개의 로프가 있다. 이 로프를 이용하여 이런 저런 물체를 들어올릴 수 있다. 각각의 로프는 그 굵기나 길이가 다르기 때문에 들 수 있는 물체의 중량이 서로 다를 수도 있다.

  하지만 여러 개의 로프를 병렬로 연결하면 각각의 로프에 걸리는 중량을 나눌 수 있다. k개의 로프를 사용하여 중량이 w인 물체를 들어올릴 때, 각각의 로프에는 모두 고르게 w/k 만큼의 중량이 걸리게 된다.

  각 로프들에 대한 정보가 주어졌을 때, 이 로프들을 이용하여 들어올릴 수 있는 물체의 최대 중량을 구해내는 프로그램을 작성하시오. 모든 로프를 사용해야 할 필요는 없으며, 임의로 몇 개의 로프를 골라서 사용해도 된다.



```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Baekjoon2217 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    int[] array = new int[N];
    for (int i = 0; i < N; i++) {
      array[i] = Integer.parseInt(br.readLine());
    }
    System.out.println(solution(N, array));
  }

  public static int solution(int N, int[] array) {
    Arrays.sort(array);
    int max = 0;
    for (int i = 0; i < N; i++) {
      int tmp = 0;
      tmp = array[i] * (N - i);
      max = Math.max(tmp, max);
    }
    return max;
  }
}

```



## 게임을 만든 동준이

- 학교에서 그래픽스 수업을 들은 동준이는 수업시간에 들은 내용을 바탕으로 스마트폰 게임을 만들었다. 게임에는 총 N개의 레벨이 있고, 각 레벨을 클리어할 때 마다 점수가 주어진다. 플레이어의 점수는 레벨을 클리어하면서 얻은 점수의 합으로, 이 점수를 바탕으로 온라인 순위를 매긴다. 동준이는 레벨을 난이도 순으로 배치했다. 하지만, 실수로 쉬운 레벨이 어려운 레벨보다 점수를 많이 받는 경우를 만들었다.

  이 문제를 해결하기 위해 동준이는 특정 레벨의 점수를 감소시키려고 한다. 이렇게해서 각 레벨을 클리어할 때 주는 점수가 증가하게 만들려고 한다.

  각 레벨을 클리어할 때 얻는 점수가 주어졌을 때, 몇 번 감소시키면 되는지 구하는 프로그램을 작성하시오. 점수는 항상 양수이어야 하고, 1만큼 감소시키는 것이 1번이다. 항상 답이 존재하는 경우만 주어진다. 정답이 여러 가지인 경우에는 점수를 내리는 것을 최소한으로 하는 방법을 찾아야 한다.

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon2847 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    int[] array = new int[N];
    for (int i = 0; i < N; i++) {
      array[i] = Integer.parseInt(br.readLine());
    }
    System.out.println(solution(N, array));
  }

  public static int solution(int N, int[] array) {
    int answer = 0;

    for (int i = N - 1; 0 < i; i--) {
      if (array[i] <= array[i - 1]) {
        while (array[i] <= array[i - 1]) {
          answer++;
          array[i - 1]--;
        }
      }
    }

    return answer;
  }
}

```



## 카드 합체 놀이



- 석환이는 아기다. 아기 석환이는 자연수가 쓰여져있는 카드를 갖고 다양한 놀이를 하며 노는 것을 좋아한다. 오늘 아기 석환이는 무슨 놀이를 하고 있을까? 바로 카드 합체 놀이이다!

  아기 석환이는 자연수가 쓰여진 카드를 n장 갖고 있다. 처음에 i번 카드엔 ai가 쓰여있다. 카드 합체 놀이는 이 카드들을 합체하며 노는 놀이이다. 카드 합체는 다음과 같은 과정으로 이루어진다.

  1. x번 카드와 y번 카드를 골라 그 두 장에 쓰여진 수를 더한 값을 계산한다. (x ≠ y)
  2. 계산한 값을 x번 카드와 y번 카드 두 장 모두에 덮어 쓴다.

  이 카드 합체를 총 m번 하면 놀이가 끝난다. m번의 합체를 모두 끝낸 뒤, n장의 카드에 쓰여있는 수를 모두 더한 값이 이 놀이의 점수가 된다. 이 점수를 가장 작게 만드는 것이 놀이의 목표이다.

  아기 석환이는 수학을 좋아하긴 하지만, 아직 아기이기 때문에 점수를 얼마나 작게 만들 수 있는지를 알 수는 없었다(어른 석환이는 당연히 쉽게 알 수 있다). 그래서 문제 해결 능력이 뛰어난 여러분에게 도움을 요청했다. 만들 수 있는 가장 작은 점수를 계산하는 프로그램을 만들어보자.

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baekjoon15903 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine(), " ");
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());
    long[] array = new long[N];
    st = new StringTokenizer(br.readLine(), " ");
    for (int i = 0; i < N; i++) {
      array[i] = Integer.parseInt(st.nextToken());
    }
    System.out.println(solution(N, M, array));
  }

  public static long solution(int N, int M, long[] array) {
    long answer = 0;

    for (int i = 0; i < M; i++) {
      Arrays.sort(array);
      long temp =array[0] + array[1];
      array[0] = temp;
      array[1] = temp;
    }

    for (long x : array) {
      answer += x;
    }

    return answer;
  }
}
```

