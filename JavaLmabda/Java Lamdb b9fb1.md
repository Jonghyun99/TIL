# Java Lamdba Expressions

[https://www.w3schools.com/java/java_lambda.asp](https://www.w3schools.com/java/java_lambda.asp)

## 자바 람다표현식이란(Java Lamdba Expresions)

자바 람다표현식은 Java8에서 추가되었다.

람다식은 매개변수를 받아 반환하는 짧은 코드블럭이다.

메소드와 비슷하지만 이름을 가지지 않고, 메소드 본문만을 이용하여 구현을 한다.

문서에서는 Lamdba Expressions를 ‘람다식’, ‘람다표현식’으로 적어 표현한다.

## 문법

1. 가장 간단한 람다식 (단일 파라미터와 표현식만이 사용된다.)

```java
parameter -> expression
```

1. 더 많은 파라미터를 사용하려면 파라미터를 감싼다.

```java
(parameter1, parameter2) -> expression
```

1. 표현식이 제한적이다. 즉시 값을 반환할 수 있어야 하며 복잡한 코드를 작성하기 위해서는 중괄호를 사용하여야 한다.

```java
(parameter1, parameter2) -> { code block }
```

## 예제

### forEach

```java
collection.forEach(변수 -> 반복처리(변수))
```

collection에는 리스트, 배열 등을 지정한다.

```java
import java.util.ArrayList;

public class Main {
  public static void main(String[] args) {
	  List<Integer> numbers = new ArrayList<Integer>();
    numbers.add(5);
    numbers.add(9);
    numbers.add(8);
    numbers.add(1);
    numbers.forEach( (n) -> { System.out.println(n); } );
  }
}
```

forEach 메소드를 통해 리스트 안의 모든 요소를 출력한다.

(java 1.5에 추가된 향상된 반복문, for each문과는 다르다.)

### Consumer

Consumer는 람다함수를 저장할 수 있는 변수를 선언 가능한 인터페이스이다.

```java
public class Main {
  public static void main(String[] args) {
    ArrayList<Integer> numbers = new ArrayList<Integer>();
    numbers.add(5);
    numbers.add(9);
    numbers.add(8);
    numbers.add(1);
    Consumer<Integer> method = (n) -> { System.out.println(n); };
    numbers.forEach( method );
  }
}
```

람다식을 Consumer 타입 변수에 할당하여 위와 같이 사용할 수 있다.

## 람다식의 타입선언

잠시 다른 이야기로, 위 예제들에서 List 요소에 Generic으로 Integer를 선언하여 사용하였지만 람다표현식에서 파라미터의 타입은 생략 가능하다. (대부분 생략 가능함)

기본적으로 람다식에서 타입을 추론해주기 때문이다. 

그러므로 위와 같은 상황에 String 요소가 하나 들어가면 List 및 Consumer의 타입을 Integer로 선언하였기에 오류가 발생하겠지만 Generic 선언을 지워주거나 최상위 클래스인 Object 로 선언해주면 다른 타입이 들어와도 사용할 수 있다.

```java
public static void main(String[] args) {

        Consumer<Object> consumer;

        List list = new ArrayList<>();
        list.add(1);
        list.add("김");
        list.add("2");
        list.add(5);

        consumer = (n) -> { System.out.println(n+"," + n.getClass()); };
        list.forEach(consumer);
    }
```

> 1,class java.lang.Integer
김,class java.lang.String
2,class java.lang.String
5,class java.lang.Integer
> 

위와 같이 Int와 String를 추론하여 출력하여준다.

그럼에도 Generic를 사용하는 이유는 List에서 뽑아낸 요소를 사용할 때 타입검사 혹은 타입변환 등이 필요할 수 있는데 이를 미리 선언하여 간편하고 안전하게 코딩하기 위함으로 생각한다.)

```java
public static void main(String[] args) {

        Consumer<Integer> consumer; //해당 선언에서 Integer Gneric을 지우면 오류 발생

        List list = new ArrayList<>();
        list.add(1);
        list.add("김");
        list.add("2");
        list.add(5);

//      list.forEach(s-> System.out.println(s.getClass()));
        consumer = (n) -> { System.out.println(n+1); };
        list.forEach(consumer);
    }
```

위의 경우에서는 <Integer> Generic을 지워주면 n+1 부분에서 컴파일 에러가 발생한다.