# Java Exceptionx



## 	try { } catch( ) {}

- try  내부에 에러가 있을 시 catch 예외처리를 시도한다 

- 만약 try 내부에 에러가 없을 시 catch 는 무시한다.

  

```java
package test2.exception;

public class DivideTest {
	
	public static void main(String[] args) {
	int x = 100; 
	int y = 2;
	try {
		int z = x / y;
		System.out.println(z);
	}catch (ArithmeticException e) {
		System.out.println("0으로 나누지 마세요 ");
	}
	System.out.println("더 중요한 일");
	}
}

```





- 중첩캐치, 멀티캐치  중첩해서 사용할 수도 있다 (2개 초과로도 계속 가능)

```java
package test2.exception;

public class DivideTest {
	
	public static void main(String[] args) {

	try {
		int x = 100; 
		int y = Integer.parseInt(args[0]);
		int z = x / y;
		System.out.println(z);
	}catch (ArithmeticException e) {
		System.out.println("0으로 나누지 마세요 ");
	}catch (IndexOutOfBoundsException e) {
		System.out.println("args로 숫자를 넣으세요");
	}
	System.out.println("더 중요한 일");
	}
}

```



- Exception 을 사용하면 모든 에러를 잡을 수 있다

```java
package test2.exception;

public class DivideTest {
	
	public static void main(String[] args) {

	try {
		int x = 100; 
		int y = Integer.parseInt(args[0]);
		int z = x / y;
		System.out.println(z);
	}catch (ArithmeticException e) {
		System.out.println("0으로 나누지 마세요 ");
	}catch (IndexOutOfBoundsException e) {
		System.out.println("args로 숫자를 넣으세요");
	}catch (Exception e) {
		System.out.println("알 수 없는 오류 발생");
	}
	System.out.println("더 중요한 일");
	}
}
```





## 	throws Exception

- throws Exception은 예외처리를 다른 쪽으로 넘긴다
- 서버에서 클라이언트로 넘기는 경우는 어떤 상황인지 알려주기 위해 하는 것이고 보통의 예외처리는 서버에서 처리한다.



```java
package test2.exception;

public class DivideTest {
	
	public static void main(String[] args) {
		
		Divider d = new Divider();
		try {
			d.divide(100, 0);
		} catch (Exception e) {
			System.out.println(e.getMessage()+"123");
		}
		System.out.println("더 중요한 일");
	}
}
```



```java
package test2.exception;

public class Divider  {
	
	public void divide(int x, int y) throws Exception {
		try {
			int z = x / y ;
			System.out.println(z);
		}catch(Exception e) {
			throw new MyException("0으로 나누지 마세요");
		}
	}
}

```



```java
package test2.exception;

public class MyException extends Exception {
	public MyException(String msg) {
		super(msg);
	}
}

```



- 멀티

