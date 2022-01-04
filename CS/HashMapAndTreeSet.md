# HashMap, TreeSet



## 학급 회장 선출

- input은 투표수와 String 형태의 투표결과 ex) BACBACCACCBDEDE
- 최다 득표인 사람을 리턴



```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class CountChar {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    String s = br.readLine();

    System.out.println(solution(N, s));
  }

  public static char solution(int N, String s) {
    char answer = ' ';
    int max = 0;

    HashMap<Character, Integer> map = new HashMap<Character, Integer>();

    for (char x : s.toCharArray()) {
      map.put(x, map.getOrDefault(x, 0)+1);
    }

    for (char key : map.keySet()) {
      if(max < map.get(key)){
        max = map.get(key);
        answer = key;
      }
    }
    return answer;
  }
}
```





### HashMap으로 카운팅 하는 방법

- ```java
  for(char x : s.toCharArray()){
      map.put(x, map.getOrDefault(x, 0)+1);
  }
  ```

- getOrDefault를 사용하여 map.get의 결과가 없을 시 디폴트로 0을 부여하고 +1, 있을 시 value + 1



### 특정 키가 있는 지 확인하는 방법

- `map.containsKey()` -> true or false 를 리턴



### map.size()

- Key의 개수를 리턴



### map.remove()

- 특정 키를 삭제
- `map.remove('A');`





## 아나그램

- input으로 2개의 String이 주어짐
- 대소문자 구분해서 아나그램 판별 후 YES, NO 리턴



```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Anagram {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String s1 = br.readLine();
    String s2 = br.readLine();
    System.out.println(solution(s1, s2));
  }

  public static String solution(String s1, String s2) {
    String answer = "";

    HashMap<Character, Integer> map1 = new HashMap<>();
    HashMap<Character, Integer> map2 = new HashMap<>();

    for (char x : s1.toCharArray()) {
      map1.put(x, map1.getOrDefault(x, 0) + 1);
    }
    for (char x : s2.toCharArray()) {
      map2.put(x, map2.getOrDefault(x, 0) + 1);
    }

    if(map1.equals(map2)){
      answer = "YES";
    } else {
      answer = "NO";
    }

    return answer;
  }
}
```



## 매출액의 종류

- N일 동안의 매출 기록과 연속된 일인 K가 주어짐

- 연속된 K일 동안의 매출액의 종류를 각 구간별로 구하라

  - 7 4

  - 20 12 20 10 23 17 10



```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class IncomeCategory {
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
    for (int x : solution(N, M, array)) {
      System.out.print(x+" ");
    }
  }

  public static ArrayList<Integer> solution(int N, int M, int[] array) {
    ArrayList<Integer> list = new ArrayList<>();
    HashMap<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < M - 1; i++) {
      map.put(array[i], map.getOrDefault(array[i], 0) + 1);
    }

    int lt = 0;
    for (int rt = M - 1; rt < N; rt++) {
      map.put(array[rt], map.getOrDefault(array[rt], 0) + 1);
      list.add(map.size());
      map.put(array[lt], map.get(array[lt]) - 1);
      if (map.get(array[lt]) == 0) {
        map.remove(array[lt]);
      }
      lt++;
    }
    return list;
  }
}
```



## 모든 아나그램 찾기

- 두개의 String이 주어짐
- 첫째 String 과 둘쨰 String이 아나그램인 개수를 리턴
- 아나그램의 개수를 구할 때 부분문자열은 연속된 문자열이어야 함

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class FindAllAnagram {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String s1 = br.readLine();
    String s2 = br.readLine();

    System.out.println(solution(s1, s2));
  }

  public static int solution(String s1, String s2) {
    int answer = 0;
    HashMap<Character, Integer> map2 = new HashMap<>();
    HashMap<Character, Integer> map = new HashMap<>();
    int length = s2.length();

    for (char x : s2.toCharArray()) {
      map2.put(x, map2.getOrDefault(x, 0) + 1);
    }

    for (int i = 0; i < length - 1; i++) {
      map.put(s1.charAt(i), map.getOrDefault(s1.charAt(i), 0) + 1);
    }

    int lt = 0;
    for (int rt = length - 1; rt < s1.length(); rt++) {
      map.put(s1.charAt(rt), map.getOrDefault(s1.charAt(rt), 0) + 1);
      if(map.equals(map2)){
        answer++;
      }
      map.put(s1.charAt(lt), map.get(s1.charAt(lt))-1);
      if(map.get(s1.charAt(lt))==0){
        map.remove(s1.charAt(lt));
      }
      lt++;
    }

    return answer;
  }
}
```

 

## K번째 큰 수

- N개의 int와 int K가 주어짐
- 3장을 뽑을 수 있는 모든 경우 중 3장의 합 중 K번째로 큰 수 출력

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class KthBigger {
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
    ArrayList<Integer> list = new ArrayList<>();
    for (int i = 0; i < N - 2; i++) {
      for (int j = i + 1; j < N - 1; j++) {
        for (int k = j + 1; k < N; k++) {
          if (!list.contains(array[i] + array[j] + array[k])) {
            list.add(array[i] + array[j] + array[k]);
          }
        }
      }
    }
    list.sort(Collections.reverseOrder());
    if (M-1 < list.size()) {
      answer = list.get(M-1);
    } else {
      answer = -1;
    }
    return answer;
  }
}
```

