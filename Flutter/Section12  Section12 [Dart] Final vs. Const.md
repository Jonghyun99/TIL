# Section12 [Dart] Final vs. Const

<div class=”markdown-body”>

Final과 Const는 둘 다 변경할 수 없는 두가지 속성을 뜻한다.

```dart
void main() {
	const	int myConst = 2; 
	final int myFinal = 3;

	myConst = 4; // error 발생, Const 변수에는 값을 할당할 수 없다.
	myFinal = 6; // error 발생, Final 변수에는 값을 할당할 수 없다.
} 
```

위와 같이 두 속성은 둘다 한번 값을 대입하면 변경할 수 없어 같은 일을 하지만 미묘한 차이점이 있다.  

const 속성은 컴파일 타임 상수인 반면 final 속성은 한 번만 설정할 수 있다.

다시 말해 final은 실행 중 값이 결정되지만 const는 컴파일 시 값이 결정된다.

예시 1)

- 일반 String은 언제든지 바꿀 수 있다.
- final은 코드가 실행되면서 값이 바뀔 수 있지만 결정되고 나서는 바꿀 수 없다.
- const는 코드 실행 전 부터 값이 정해져서 바꿀 수 없다.

```dart
final finalTime = DateTime.now(); // 실행 중 시간이 결정되므로 사용 가능
const constTime = DateTime.now(); // error 발생, 컴파일 시 시간을 담을 수가 없음
```

</div>