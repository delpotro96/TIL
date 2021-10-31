# JAVA Collection



## List



```java
public class ListTest {
	public static void main(String[] args) {
		ArrayList list = new ArrayList();
		list.add("Hello");
		list.add("Hi");
		list.add("Hola");
		list.add("Hello");
		System.out.println(list);
		System.out.println(list.size());
		System.out.println(list.get(2));
		
		for(int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
}
```

- List는 중복이 가능하며 순서를 유지하고 저장한다.

## Set

```java
```



- Set은 중복 저장이 안되며 순서를 유지하지 않고 저장한다.