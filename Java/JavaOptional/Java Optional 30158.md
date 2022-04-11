# Java Optional

[http://www.tcpschool.com/java/java_stream_optional](http://www.tcpschool.com/java/java_stream_optional)

## Java Optional이란?

자바 8에 추가된 (java.util.Optional<T>) 클래스이며 Integer이나 Double 클래스 처럼 ‘T’타입의 객체를 포장해주는 Wrapper class이다. 고로 모든 타입의 참조 변수를 저장할 수 있다.

(Wrapper Class는 객체 활용이 불가능한 기본타입의 데이터를 객체로 Boxing(변환)하기 위한 Class이다. Ex) int i = 1;  → Integer j = Integer.valueof(i);)

이러한 Optional 객체를 이용하여 NullPointException를 간단하게 방지할 수 있다.

## Optional 객체 생성

of() 메소드나 ofNuallable() 메소드를 사용하여 Optional 객체를 생성할 수 있다.

of() 메소드는 null이 아닌 값을 가지는 Optional 객체를 반환한다.

만약 of() 메소드를 통해 생성된 Optional 객체에 null이 저장되면 NullPointerException 예외가 발생한다.

따라서 만약 변수의 값이 null이 될 가능성이 있다면, ofNullable() 메소드로 Optional 객체를 생성하자.

ofNullable() 메소드는 null일 경우 빈 Optional 객체를 반환한다.

```java
Optional<String> opt = Optional.ofNullable("자바");
System.out.println(opt.get()); // 출력: 자바
```

## Optional 객체에 접근

get() 메소드를 통해 Optional 객체에 저장된 값에 접근할 수 있다.

이렇게 호출된 객체는 본래의 객체값을 갖고 있다.

```java
int i = 1;
Optional opt = Optional.of(i);

System.out.println(opt); // Optional[1]
System.out.println(opt.get()); // Optional[0]
System.out.println(opt.getClass()); // class java.util.Optional
System.out.println(opt.get().getClass()); // class java.lang.Integer
```

만약 null이 저장되어 있다면, NoSuchElementException 예외가 발생한다.

따라서 get() 메소드를 호출하기 전에 isPresent() 메소드를 사용하여 null인지 check하는 것이 좋다.

```java
Optional<String> opt = Optional.ofNullable("자바 Optional 객체");

if(opt.isPresent()) {
    System.out.println(opt.get());
}
```

또한, 다음과 같은 메소드로 null 대신에 대체할 값을 지정할 수 있다.

1. orElse() 메소드 : 저장된 값이 존재하면 그 값을 반환하고, 값이 존재하지 않으면 인수로 전달된 값을 반환함.
2. orElseGet() 메소드 : 저장된 값이 존재하면 그 값을 반환하고, 값이 존재하지 않으면 인수로 전달된 람다 표현식의 결괏값을 반환함.
3. orElseThrow() 메소드 : 저장된 값이 존재하면 그 값을 반환하고, 값이 존재하지 않으면 인수로 전달된 예외를 발생시킴.

```java
Optional<String> opt = Optional.empty(); // Optional를 null로 초기화함.
System.out.println(opt.orElse("빈 Optional 객체"));
System.out.println(opt.orElseGet(String::new));

//출력: 빈 Optional 객체
```

## 기본 타입의 Optional 클래스

자바에서는  IntStream 클래스와 같이 기본 타입 스트림을 위한 별도의 Optional 클래스를 제공하고 있다.

1. OptionalInt 클래스
2. OptionalLong 클래스
3. OptionalDouble 클래스

위 클래스는 반환 타입이 Optional<T> 타입이 아니라 기본 타입일 뿐이지 다른 면에서는 거의 같다.

또한 Optional객체에서 get() 메소드를 사용하여 클래스별 접근 메소드를 제공하고 있다.

Optional<T> : T get()

OptionalInt : int getAsInt()

OptionalLong : long getAsLong()

OptionalDouble double get AsDouble()

## Optional의 메소드

Optional<T> empty() : 빈 Optional 객체 반환

T get() : Optional 객체 안의 값을 반환

boolean isPresent(); 값이 있으면 true, null일시 false 반환

Optional<T> of(T value) : Optional 객체 반환함, null일 시 오류 발생

Optional<T> ofNullable(T value) : Optional 객체 반환함, null일 시 빈 Optional 객체 반환

T orElse(T other) : 값을 반환하나 Null일 시 인수 값을 반환

T orElseGet(Supplier<? extends T> other) : 값을 반환하나 Null일 시 인수로 전달된 람다 표현식의 값 반환

<X extends Throwable> T orElseThrow(Supplier<? extends X>  exceptionSupplier) : 값을 반환하나 Null일 시 인수로 전달한 예외 발생