# DI를 굳이 쓰는 이유

생성일: 2021년 4월 26일 오후 6:04
속성: https://velog.io/@wlsdud2194/what-is-di
해결: Yes

종속성 때문에? 그걸 해결하기 위해 인터페이스와 추상화가 있는게 아닌가

→ 기존의 자바 프로그래밍 방식은 코드에 의존성이 강하다.

```java
// Programmer.java
class Programmer {
    private Coffee coffee;

    public Programmer() {
    	this.coffee = new Coffee();
    }
    
    public startProgramming() {
    	this.coffee.drink(); // 일단 마시고 시작하자
        ...
    }
}
```

위 startProgramming 메소드를 호출하기 위해선 Coffee 클래스를 사용하여야 한다. 이 때 Coffee 클래스가 수정되면 Programmer 클래스도 수정되어야 한다. 즉 결합도(coupling)가 높다.

이점은?

1. Unit Test가 용이해진다
2. 코드의 재활용성이 높아진다.
3. 의존성, 종속성을 줄이거나 없앨 수 있다.
4. 결합도를 낮춘 유연한 코드 작성  가능

---

### To Quickly Recap:

1. DI는 필요한 객체를 직접 생산하는 것이 아닌 필요한 객체를 외부에서 받아서 사용하는 것
2. 이를 통해 결합도를 줄이고 재활용성을 높여준다.