# JAVA CLASS



## 	OOP의 3대 concept

### 		encapsulation

- Data 보호 = > Data는 private 

  ### 	inheritance

- 오직 하나의 SuperClass만 상속 가능하며 private member나 생성자는 상속 불가하다.

  ### 	polymorphism

- 다형적 변수 : 슈퍼 타입으로 선언된 변수는 모든 자식 객체를 사용할 수 있다.
- 오버라이딩 : 슈퍼의 메소드를 서브에서 재정의 하여 shadow effect를 없애는 방법으로 성능과 확장성이 향상된다
- 오버로딩 : 한 클래스에서 같은 이름의 메소드가 다수 존재하는 것을 의미한다.

```java
Car(String model){
    
}
Car(String model, String color, String maxSpeed){
    
}
```

- static 영역 안에서 super 는 사용할 수 없다.
- 모든 생성자는 자동으로 super(); 를 호출한다. this(); 생성자를 호출하기 위해서는 명시해야한다.



## 	JAVA 유효성 검사

- 데이터는 private로 한 후 set, get 메소드를 사용하는 것이 좋다. 
- Source >  Generate Getters and Setters
- set, get 메소드를 사용할 경우 메소드를 그대로 두는 것이 아닌 유효성 테스트를 거치는 것이 좋다.

```java
//사용자가 age에 0이하의 값을 넣을 수 없도록 방지
//사용자가 name에 null 값을 넣을 수 없도록 방지

public class Main {
	private int age;
	private String name;
	
	public void setAge(int newAge) {
		//유효성 검사
		if(newAge > 0) {
			age = newAge;
		} else {
			System.out.println("나이는 0보다 커야합니다.");
		}
	}
	public int getAge() {
		return age;
	}
    public void setName(String newName){
        if(newName==null){
            System.out.println("이름은 null이 될 수 없습니다");
        } else {
            name = newName;
        }
    }
    public String getName(){
        return name;
    }
}
```



## Java Method

### Method 매개변수를 모를 경우 배열로 처리

ex) Calc plus

```java
public class Calculator{

    /// ... 은 var args 라 불린다
	int plus(int ...i) {
		int sum = 0;
		for(int a = 0; a < i.length; a++) {
			sum += i[a];
		}
		return sum;
	}
}
```

