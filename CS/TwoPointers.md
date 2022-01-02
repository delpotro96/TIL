# Two pointers, Sliding window[효율성 : O(n^2) --> O(n)]



## 두 배열 합치기(Two Pointers)

- 정렬 되어 주어지는 두개의 배열을 오름차순으로 합쳐 출력

### 내가 쓴 답

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class MergeArray {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N = Integer.parseInt(br.readLine());
    String[] tempN = br.readLine().split("\\s+");
    int[] arrayN = new int[N];
    for (int i = 0; i < N; i++) {
      arrayN[i] = Integer.parseInt(tempN[i]);
    }

    int M = Integer.parseInt(br.readLine());
    String[] tempM = br.readLine().split("\\s+");
    int[] arrayM = new int[M];
    for (int i = 0; i < M; i++) {
      arrayM[i] = Integer.parseInt(tempM[i]);
    }

    System.out.println(solution(N, M, arrayN, arrayM));
  }

  public static String solution(int N, int M, int[] arrayN, int[] arrayM) {
    String answer = "";

    ArrayList<Integer> list = new ArrayList<>();

    for (int x : arrayN) {
      list.add(x);
    }
    for (int x : arrayM) {
      list.add(x);
    }
    Collections.sort(list);

    for(int i = 0; i < list.size(); i++){
      answer += list.get(i)+" ";
    }

    return answer;
  }
}
```



### 투 포인터로 효율적이게

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MergeArray {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N = Integer.parseInt(br.readLine());
    String[] tempN = br.readLine().split("\\s+");
    int[] arrayN = new int[N];
    for (int i = 0; i < N; i++) {
      arrayN[i] = Integer.parseInt(tempN[i]);
    }

    int M = Integer.parseInt(br.readLine());
    String[] tempM = br.readLine().split("\\s+");
    int[] arrayM = new int[M];
    for (int i = 0; i < M; i++) {
      arrayM[i] = Integer.parseInt(tempM[i]);
    }

    System.out.println(solution(N, M, arrayN, arrayM));
  }

  public static String solution(int N, int M, int[] arrayN, int[] arrayM) {
    String answer = "";

    ArrayList<Integer> list = new ArrayList<>();
    int p1 = 0;
    int p2 = 0;

    while (p1 < N && p2 < M) {
      if(arrayN[p1]<arrayM[p2]){
        list.add(arrayN[p1++]);
      } else{
        list.add(arrayM[p2++]);
      }
    }
    while(p1<N){
      list.add(arrayN[p1++]);
    }
    while(p2<M){
      list.add(arrayM[p2++]);
    }

    for(int i = 0; i < list.size(); i++){
      answer += list.get(i)+" ";
    }

    return answer;
  }
}
```



## 공통원소 구하기(Two Pointers)

- 두 배열이 주어지면 두 배열의 공통원소를 오름차순으로 출력

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class GeneralInt {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N = Integer.parseInt(br.readLine());
    String[] tempN = br.readLine().split("\\s+");
    int[] arrayN = new int[N];
    for (int i = 0; i < N; i++) {
      arrayN[i] = Integer.parseInt(tempN[i]);
    }

    int M = Integer.parseInt(br.readLine());
    String[] tempM = br.readLine().split("\\s+");
    int[] arrayM = new int[M];
    for (int i = 0; i < M; i++) {
      arrayM[i] = Integer.parseInt(tempM[i]);
    }

    System.out.println(solution(N, M, arrayN, arrayM));
  }

  public static String solution(int N, int M, int[] arrayN, int[] arrayM) {
    String answer = "";
    Arrays.sort(arrayN);
    Arrays.sort(arrayM);
    int p1 = 0;
    int p2 = 0;

    while (p1 < N & p2 < M) {
      if(arrayN[p1] == arrayM[p2]){
        answer += arrayN[p1]+" ";
        p1++;
        p2++;
      } else if(arrayM[p2]<arrayN[p1]){
        p2++;
      } else if(arrayN[p1]<arrayM[p2]){
        p1++;
      }
    }

    return answer;
  }
}
```



## 최대 매출

- N일의 기간 중 연속된 M일 동안의 최대 매출액을 구하라

### 내가 쓴 답

- N x M

```java
public class MaxIncome {
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

    for (int i = 0; i < N - M; i++) {
      int sum = 0;
      for (int j = 0; j < M; j++) {
        sum += array[i + j];
      }
      if(answer<sum){
        answer = sum;
      }
    }

    return answer;
  }
}
```



### Sliding Window로 효율적이게

- 가장 첫 M일 동안의 매출액을 구하고 한칸씩 밀면서 M일 동안의 매출액을 구함
- 한칸씩 밀면서 ->   sum = sum + array[ i ] - array[ i - M ]

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MaxIncome {
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
    int sum = 0;
    for (int i = 0; i < M; i++) {
      sum += array[i];
    }
    answer = sum;

    for (int i = M; i < N; i++) {
      sum = sum + array[i] - array[i - M];
      answer = Math.max(answer, sum);
    }

    return answer;
  }
}
```



## 연속 부분수열

- N개의 수로 이루어진 수열이 주어짐
- 연속부분수열의 합이 M이 되는 경우 카운트해서 리턴



### 내가 쓴 답

- n^2

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SpecialSum {
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

    for (int i = 0; i < N; i++) {
      int sum = 0;
      for (int j = 0; j+i < N; j++) {
        sum += array[i + j];
        if (sum == M) {
          answer++;
          break;
        } else if (M < sum) {
          break;
        }
      }
    }

    return answer;
  }
}
```

 

### Two Pointers로 효율적이게

- n
- lt와 rt 두개의 포인터로 합을 구함
- lt ~ rt까지의 합을 rt++ 하며 계속 구하다가 합이 M보다 커질경우 lt를 하나씩 늘리는 것을 반복

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SpecialSum {
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
    int sum = 0;
    int lt = 0;
    for(int rt = 0; rt < N; rt++){
      sum+=array[rt];
      if(sum==M){
        answer++;
      }
      while(M<=sum){
        sum -= array[lt++];
        if(sum==M){
          answer++;
        }
      }
    }

    return answer;
  }
}
```

