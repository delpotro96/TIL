# JAVA로 구현하고 배우는 자료구조



## 시간복잡도

- input은 항상 0보다 크다고 가정한다.
- 함수는 많은 입력값이 있을 때 더 많은 작업을 한다.
- 시간 복잡도에서는 모든 상수를 무시할 것이다. ex) 3n =n ...
- 낮은 차수의 항은 무시한다 ex) n^3 + n^2 +n + 3 =n^3
- 2n = O(n) , 2n은 O(n)에 속한다



## Big-Oh 표기법

- o (리틀 오 복잡도) : 비교 대상인 그래프가 아래에 있을 때. 비교 대상인 다른 알고리즘보다 더 빠르다.
- O (빅 오 복잡도) : 비교 대상인 그래프가 일치 혹은 아래에 있을 때. 비교 대상인 다른 알고리즘과 같거나 더 빠르다.
- θ (세타 복잡도) : 비교 대상인 그래프가 일치할 때. 비교 대상인 다른 알고리즘과 같다.
-  Ω (빅 오메가 복잡도) : 비교 대상인 그래프가 일치 혹은 위에 있을 때. 비교 대상인 다른 알고리즘과 같거나 느리다.
- ω (리틀 오메가 복잡도) : 비교 대상인 그래프가 위에 있을 때. 비교 대상인 다른 알고리즘보다 느리다.



## 객체지향 프로그래밍

- Student s = new Student();
- 위와 같은 객체를 만들면 JVM은 코드를 읽어 메모리가 얼마나 필요한지 계산하고 그만큼의 공간을 힙에 할당한 후 힙을 가리키는 4바이트짜리 포인터를 만든다.



## Comparable 인터페이스

```java
a.compareTo(b)
if(a < b) -> return < 0
if(a == b) -> return = 0
if(a > b) -> return >0    
```



## AutoBoxing

| Primitives | Wrapper |
| ---------- | ------- |
| byte       | Byte    |
| short      | Short   |
| int        | Integer |
| long       | Long    |
| double     | Double  |
| char       | Char    |
| boolean    | Boolean |

- AutoBoxing 란 Primitive Type과 해당 Wrapper 객체 간 타입 변환을 컴파일러가 자동으로 해주는 것을 말한다.



## 연결리스트 (Linked Lists)

- 연결리스트는 배열의 크기를 직접 설정해야 하는 Array와 다르게 항상 맞는 크기로 만들어지도록 설계되어 있다.
- 연결리스트에는 노드라는 요소가 숨겨져있다. 
  - 노드에는 두 가지 정보가 들어있다. 첫째는 next라는 이름의 포인터 둘째는 우리가 노드에 넣은  데이터(포인터)
  - 노드 각각의 포인터(next)는 인접한 노드를 가리킨다.

​	![image-20211226132058776](C:\Users\Kyunghun Lee\AppData\Roaming\Typora\typora-user-images\image-20211226132058776.png)

- 연결 리스트에 접근할 수 있는 유일한 방법은 head이다. 



 ### Node

```java
public class LinkedList<E> implements ListI<E>{
    
    class Node<E> {
        E data;
        Node<E> next;
        
        public Node(E obj){
            data = obj;
            next = null;
        }
    }
    private Node<E> head;
    private int currentSize;
    
    public LinkedList(){
        head = null;
        currentSize = 0;
    }
}
```



### 경계조건(Boundary Conditions)

- 어떤 자료구조이든 신경 써야 할 경계 조건이 있다.
  - 자료 구조가 비어있는 경우
  - 자료 구조에 단 하나의 요소가 들어있을 경우
  - 자료 구조의 첫 번째 요소를 제거하거나 추가할 때
  - 자료 구조의 마지막 요소를 제거하거나 추가할 때
  - 자료 구조의 중간 부분을 처리할 때

