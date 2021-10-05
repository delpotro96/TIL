# JAVA Interface



- 인터페이스란 다중 상속을 위한 프로그램 단위를 의미한다.(Class 는 단일 상속만 가능)
- interface의 모든 메소드는 abstract이다.

```java
package test1.abstract_;

public interface Flyer {

}

```



```java
package test1.abstract_;

public class Bird extends Animal implements Flyer{

	@Override
	public void eat() {
		System.out.println("벌레를 먹고 산다.");
		
	}
	public void flying(){
		System.out.println("날개를 펄럭이며 난다.");
	}

}
```

- 인터페이스에 데이터는 상수만 올 수 있다.
- 인터페이스에는 생략 되어 있는 것이 많으며 상수는 대문자로 선언하는 것이 좋다

```java
package test1.abstract_;

public interface Flyer {
    int SPEED = 100; 
    // public static final int Speed 와 같다 
}
```





- interface 끼리의 상속은 extends를 사용한다
