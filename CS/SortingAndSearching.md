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



## 장난꾸러기

- 철수네 반에는 N명의 학생들이 있습니다.

  선생님은 반 학생들에게 반 번호를 정해 주기 위해 운동장에 반 학생들을 키가 가장 작은 학생부터 일렬로 키순으로 세웠습니다.

  제일 앞에 가장 작은 학생부터 반 번호를 1번부터 N번까지 부여합니다. 철수는 짝꿍보다 키가 큽니다.

  그런데 철수가 앞 번호를 받고 싶어 짝꿍과 자리를 바꿨습니다.

  선생님은 이 사실을 모르고 학생들에게 서있는 순서대로 번호를 부여했습니다.

  철수와 짝꿍이 자리를 바꾼 반 학생들의 일렬로 서있는 키 정보가 주어질 때 철수가 받은 번호와 철수 짝꿍이 받은 번호를

  차례로 출력하는 프로그램을 작성하세요.



```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Imp {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    String[] temp = br.readLine().split("\\s+");
    int[] array = new int[N];

    for (int i = 0; i < N; i++) {
      array[i] = Integer.parseInt(temp[i]);
    }
    for (int x : solution(N, array)) {
      System.out.print(x + " ");
    }
  }

  public static ArrayList<Integer> solution(int N, int[] array) {

    ArrayList<Integer> answer = new ArrayList<>();

    int[] tmp = array.clone();
    Arrays.sort(tmp);

    for (int i = 0; i < N; i++) {
      if (tmp[i] != array[i]) {
        answer.add(i + 1);
      }
    }

    return answer;
  }
}
```



## 좌표 정렬

- 좌표가 주어지면 정렬 선 x 후 y
- 이차원 배열 정렬의 예시

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class LocationSort {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N = Integer.parseInt(br.readLine());
    int[][] array = new int[N][2];
    for (int i = 0; i < N; i++) {
      String[] temp = br.readLine().split("\\s+");
      array[i][0] = Integer.parseInt(temp[0]);
      array[i][1] = Integer.parseInt(temp[1]);
    }
    for (int i = 0; i < N; i++) {
      System.out.println(solution(N, array)[i][0] + " " + solution(N, array)[i][1]);
    }
  }

  public static int[][] solution(int N, int[][] array) {

    Arrays.sort(
        array,
        new Comparator<int[]>() {
          @Override
          public int compare(int[] o1, int[] o2) {
            if (o1[0] == o2[0]) {
              return o1[1] - o2[1];
            } else {
              return o1[0] - o2[0];
            }
          }
        });
    return array;
  }
}
```



## 이분검색

- N개의 숫자가 주어지면 오름차순으로 정렬한 후 M이 몇 번째에 있는 지 리턴
- 이분탐색을 해보는 것에 의의

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BinarySearch {
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
    int answer = 0;
    Arrays.sort(array);

    int lt = 0;
    int rt = N - 1;

    while (lt <= rt) {
      int mid = (lt + rt) / 2;
      if (array[mid] == M) {
        answer = mid + 1;
        break;
      } else if (array[mid] < M) {
        lt = mid + 1;
      } else if (M < array[mid]) {
        rt = mid - 1;
      }
    }

    return answer;
  }
}
```



## 뮤직비디오(결정알고리즘)

- 지니레코드에서는 불세출의 가수 조영필의 라이브 동영상을 DVD로 만들어 판매하려 한다.

  DVD에는 총 N개의 곡이 들어가는데, DVD에 녹화할 때에는 라이브에서의 순서가 그대로 유지되어야 한다.

  순서가 바뀌는 것을 우리의 가수 조영필씨가 매우 싫어한다. 즉, 1번 노래와 5번 노래를 같은 DVD에 녹화하기 위해서는

  1번과 5번 사이의 모든 노래도 같은 DVD에 녹화해야 한다. 또한 한 노래를 쪼개서 두 개의 DVD에 녹화하면 안된다.

  지니레코드 입장에서는 이 DVD가 팔릴 것인지 확신할 수 없기 때문에 이 사업에 낭비되는 DVD를 가급적 줄이려고 한다.

  고민 끝에 지니레코드는 M개의 DVD에 모든 동영상을 녹화하기로 하였다. 이 때 DVD의 크기(녹화 가능한 길이)를 최소로 하려고 한다.

  그리고 M개의 DVD는 모두 같은 크기여야 제조원가가 적게 들기 때문에 꼭 같은 크기로 해야 한다.

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class MusicVideo {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] temp = br.readLine().split("\\s+");
    int N = Integer.parseInt(temp[0]);
    int M = Integer.parseInt(temp[1]);
    String[] tmp = br.readLine().split("\\s+");
    int[] array = new int[N];
    for (int i = 0; i < N; i++) {
      array[i] = Integer.parseInt(tmp[i]);
    }
    System.out.println(solution(N, M, array));
  }

  public static int solution(int N, int M, int[] array) {
    int answer = 0;
    int lt = Arrays.stream(array).max().getAsInt();
    int rt = Arrays.stream(array).sum();
    while (lt <= rt) {
      int mid = (lt + rt) / 2;
      if (count(array, mid) <= M) {
        answer = mid;
        rt = mid - 1;
      } else {
        lt = mid + 1;
      }
    }
    return answer;
  }

  public static int count(int[] array, int capacity) {
    int cnt = 1;
    int sum = 0;
    for (int x : array) {
      if (sum + x > capacity) {
        cnt++;
        sum = x;
      } else {
        sum += x;
      }
    }
    return cnt;
  }
}
```



## 마구간 정하기(결정알고리즘)

- N개의 마구간이 수직선상에 있습니다. 각 마구간은 x1, x2, x3, ......, xN의 좌표를 가지며, 마구간간에 좌표가 중복되는 일은 없습니다.

  현수는 C마리의 말을 가지고 있는데, 이 말들은 서로 가까이 있는 것을 좋아하지 않습니다. 각 마구간에는 한 마리의 말만 넣을 수 있고,

  가장 가까운 두 말의 거리가 최대가 되게 말을 마구간에 배치하고 싶습니다.

  C마리의 말을 N개의 마구간에 배치했을 때 가장 가까운 두 말의 거리가 최대가 되는 그 최대값을 출력하는 프로그램을 작성하세요.



```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class SelectHorseHouse {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] temp = br.readLine().split("\\s+");
    int N = Integer.parseInt(temp[0]);
    int M = Integer.parseInt(temp[1]);
    String[] tmp = br.readLine().split("\\s+");
    int[] array = new int[N];
    for (int i = 0; i < N; i++) {
      array[i] = Integer.parseInt(tmp[i]);
    }
    System.out.println(solution(N, M, array));
  }

  public static int solution(int N, int M, int[] array) {
    int answer = 0;
    Arrays.sort(array);
    // 말 사이 거리의 최소값
    int lt = 1;
    // 말 사이 거리의 최대값
    int rt = array[N - 1];

    while (lt <= rt) {
      int cnt = 0;
      int ep = array[0];
      int mid = (lt + rt) / 2;
      for (int i = 1; i < N; i++) {
        if (mid <= array[i] - ep) {
          cnt++;
          ep = array[i];
        }
      }
      if (M - 1 <= cnt) {
        answer = mid;
        lt = mid + 1;
      } else {
        rt = mid - 1;
      }
    }

    return answer;
  }
}

```

