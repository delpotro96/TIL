# 싱글톤

- 생성자가 여러번 호출되더라도 실제로 생성되는 객체는 하나이고, 최초 생성 이후 호출된 생성자는 최초 생성자가 생성한 객체를 리턴하게 하는 패턴 (Spring 쓰면 알아서 해준다)

  - 클래스의 인스턴스가 1개만 생성되는 것을 보장하는 디자인 패턴으로 인스턴스를 2개 이상 생성하지 못하도록 막아야한다.

```java
public class SingletonService {

    private static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance() {
        return instance;
    }

    private SingletonService(){

    }
    public void logic(){
        System.out.println("싱글톤 객체 로직 호출");
    }
}
```

1. static 영역에 객체 instance를 미리 하나 생성해서 올려둔다.
2. 이 객체 인스턴스가 필요하면 오직 getInstance() 메서드를 통해서만 조회할 수 있다, 이 메서드를 호출하면 항상 같은 인스턴스를 반환한다.
3. 딱 1개의 객체 인스턴스만 존재해야 하므로, 생성자를 pricate로 막아서 외부에서 new 키워드로 생성하는 것을 막는다



```java
@Test
    @DisplayName("스프링 없는 순수 DI 컨테이너")
    void pureContainer(){
        AppConfig appConfig = new AppConfig();

        MemberService memberService1 = appConfig.memberService();

        MemberService memberService2 = appConfig.memberService();
        assertThat(memberService1).isSameAs(memberService2);
    }

    @Test
    @DisplayName("Singleton 패턴을 적용한 객체 사용")
    void singletonService(){
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        System.out.println("");
        assertThat(singletonService1).isSameAs(singletonService2);
    }
```



## 싱글톤 컨테이너

- 스프링 컨테이너는 싱글톤 패턴을 적용하지 않아도 객체 인스턴스를 싱글톤으로 관리한다.
