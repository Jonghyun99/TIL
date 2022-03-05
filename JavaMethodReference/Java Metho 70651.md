# Java Method Reference

## 메소드 레퍼런스(Method Reference)

메소드 레퍼런스는 람다 표현식을 더 간단하게 표현하는 방법이다.

```java
클래스이름::메소드이름
또는
참조변수이름::메소드이름
```

위와 같이 사용하여 불필요한 매개변수를 제거할 수 있다.

Ex) 람다식으로 Hello를 출력하는 코드이다.

```java
Consumer<String> func = text -> System.out.println(text);
func.accept("Hello");
// 실행 결과
// Hello
```

위 람다식은 다음과 같이 표현될 수 있다.

```java
Consumer<String> func = System.out::println;
func.accept("Hello");
// 실행 결과
// Hello
```

메소드 레퍼런스는 `ClassName::MethodName` 형식으로 입력하여 메소드를 호출하는 것이지만 괄호()는 써주지 않고 생략한다.

이렇게 메소드 레퍼런스에는 많은 코드들이 생략되기 때문에 사용하려는 메소드의 리턴과 인자 타입을 알고 있어야 한다.

또한, 특정 인터페이스의 메소드를 참조할 때에도 참조 변수를 통해 메소드 참조할 수 있다.

```java
MyClass obj = new MyClass;
Function<String, Boolean> func = (a) -> obj.equals(a); // 람다 표현식
Function<String, Boolean> func = obj::equals(a);       // 메소드 참조
```

※ 인터페이스 사용 시 타입에 맞는 Generic을 선언해주지 않으면 해당 타입을 사용할 때 Type error가 발생한다.

예제

```java
List<String> animals = new ArrayList<>();
        animals.add("monkey");
        animals.add("dog");
        animals.add("chiken");
        animals.add("elephent");

        animals.stream()
        .mapToInt(a -> a.length())
        .forEach((a) -> System.out.println());
```

람다식을 사용한 방법

```java
List<String> animals = new ArrayList<>();
        animals.add("monkey");
        animals.add("dog");
        animals.add("chiken");
        animals.add("elephent");

        animals.stream()
                .mapToInt(String::length)
                .forEach(System.out::println);
```

메소드 레퍼런스로 리팩토링

사실 위의 예시같은 경우는 아래와 같이 그냥 for문 돌리는게 훨씬 보기도 좋고 간단하다.

```java
List<String> animals = new ArrayList<>();
        animals.add("monkey");
        animals.add("dog");
        animals.add("chiken");
        animals.add("elephent");

				for(String animal:animals){
					System.out.println(animal.length);
				}
```

실제로 얼마나 활용될지 모르겠지만 복잡한 스트림 연산이 필요한 경우를 대비해서 숙지해두면 좋을 것 같다.