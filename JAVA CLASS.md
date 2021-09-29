# JAVA CLASS

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

