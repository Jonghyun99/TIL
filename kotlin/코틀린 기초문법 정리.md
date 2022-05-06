# 코틀린 기초문법 정리

미루고 미루던 코틀린 공부를 시작하였습니다.
사실 자바에 익숙한 사람이면 금방 배울 수 있을테지만, 기초를 다시 한번 복습하는 의미에서 포스팅합니다.

# 목차

1. 구문 (Syntax)
2. 출력 (Output)
3. 주석 (Comments)
4. 변수 (Variables)
5. 데이터 타입 (Data Types)
6. 연산자 (Operators)
7. 조건문 (If, Else)
8. When
9. While Loop
10. Break/Continue
11. 배열 (Arrays)
12. 반복문 (For Loop)
13. Ranges
14. 함수 (Functions)
15. 객체지향프로그래밍 (OOP)
16. 클래스/객체 (Classes/Objects)
17. 생성자 (Constrctors)

# 1. 구문 (Syntax)

“Hello World”를 출력해보자

```kotlin
fun main() {
  println("Hello World")
}
```

1. **`fun`** 키워드는 함수를 정의할 때 사용합니다.

함수는 특정 작업을 수행하기 위해  설계된 코드블럭입니다.

1. **`main()`** 함수는 코틀린을 실행시키기 위한 함수입니다.

**`main()`** 함수 안 중괄호로 이루어진 내용이 실행됩니다. 예시로, **`println()`** 함수는 `main()` 함수 안에 있으므로 `println()` 함수가 실행된다는 뜻 입니다.

1. `println()` 함수는 텍스트를 출력하는데 사용되며 위 예시에서는 “Hello World”를 출력합니다.
2. 코틀린은 **코드 끝에** **세미콜론 ( ; )를 붙일 필요가 없습니다**. (요즘 언어 특징)

### 추가) main() 함수의 파라미터

코틀린 1.3 버전 이전에서는 `main()` 함수 안에 꼭 파라미터를 함께 써주어야 했습니다. 
e.g. `fun main(args : Array<String>)`
(현 시점 사용중인 버전은 1.6)
따라서 지금은 파라미터 넣는것이 필요 없어졌으나, 사용해서 문제될 것은 없습니다.

※main() 함수의 파라미터는 프로그램이 실행될 때 임의로 사용할 추가 변수를 받기 위함 입니다.

# 2. **출력 (Output)**

## println() 함수

**`println()`** 함수는 변수나 텍스트를 출력하기 위해 사용됩니다.

```kotlin
fun main() {
  println("Hello World!")
  println("I am learning Kotlin.")
  println("It is awesome!")
}
// 여러줄 출력 예시

fun main() {
  println(3 + 3)
}
// 숫자 계산 예시
```

## print() 함수

```kotlin
fun main() {
  print("Hello World! ")
  print("I am learning Kotlin. ")
  print("It is awesome!")
}
```

위에서 다룬 `println()` 함수는 출력 후 개행(줄바꿈)을 해주지만 `print()` 함수는 개행을 해주지 않고 한 줄로 출력해줍니다.

# 3. 코틀린 주석 (Comments)

## 한줄 주석

한 라인의 주석이 필요한 경우 주석하고자 하는 문장 맨 앞에 ( `//` ) 를 사용합니다.

```kotlin
// 주석입니다.
println("Hello World")

println("Hello World") // 이 또한 주석 입니다.
```

## 여러 줄 주석

여러줄의 주석은 `/*  */` 입니다.

/*와 */ 사이의 글자들은 전부 무시됩니다.

```kotlin
/*
여러줄
주석
입니다
*/
println("Hello World")
```

# 4. 변수 (V**ariables)**

변수는 데이터를 저장하기 위한 그릇 입니다.

**`var`** 또는 **`val`** 키워드를 사용하여 변수를 선언하고, ( = ) 를 이용해 데이터를 할당합니다.

```kotlin
var variableName = value
val variableName = value
```

예시

```kotlin
var name = "종현"
val birthyear = 1999

println(name)          // 종현
println(birthyear)     // 1999
```

- var은 데이터 수정이 가능!
- val은 데이터 수정이 불가능! (final과 같으며 상수를 선언할 때 주로 사용)

## 변수 타입 (**Variable Type)**

코틀린에서는 변수 타입 선언이 필요 없습니다. (자바에서의 String이나 int 등)

하지만 한번 선언한 변수 타입에서 다른 타입의 변수를 대입할 수 없습니다.

```kotlin
var name = "종현"
name = 1234 // 런타임에러 발생!
```

원한다면 타입을 함께 선언할 수 있습니다.

```kotlin
var name: String = "종현" //String
val birthYear: Int = 1975 // Int
```

## 변수 네이밍 규칙(Display Variables)

- 변수 이름은 문자, 숫자, 밑줄, 달러 등을 포함할 수 있습니다
- 이름은 문자로 시작해야 합니다.
- 이름은 $ 및 _로 시작할 수 있습니다.
- 대소문자를 구분합니다.
- 보통 소문자로 시작하며 공백을 포함할 수 없습니다. (카멜케이스 권장)
- 예약어 (var, String) 등을 사용할 수 없습니다.

# 5. 데이터 타입 (Data Types)

```kotlin
val number = 5 // Int
val doubleNumber = 5.99 // Double
val letter = 'D' // Char
val myBoolean = true // Boolean
val myText = "Hello" // String

// 변수 타입을 선언하고 싶다면?
val myNum: Int = 5                // Int
val myDoubleNum: Double = 5.99    // Double
val myLetter: Char = 'D'          // Char
val myBoolean: Boolean = true     // Boolean
val myText: String = "Hello"      // String
```

## 숫자형 (Numbers)

숫자 형은 정수형(Integer)과 실수형(Floating)으로 나눌 수 있습니다.
`정수형` 타입으로는 `Byte`, `Short`, `Int`, `Long`이 있습니다.
`실수형` 타입으로는 `Float`와 `Double`이 있습니다.

타입을 선언하지 않을 시 정수에는 `Int`, 실수에는 `Double`이 자동으로 대입됩니다.

## 정수형 (Integer Types)

### **Byte (8비트 = 1바이트)**

`Byte` 타입은 숫자 -128 부터 127까지 저장할 수 있습니다.
변수에 작은 숫자가 대입될 것이라고 확신할 때 사용합니다.

### **Short (16비트 = 2바이트)**

`Short` 타입은 -32768 부터 32767 까지 저장할 수 있습니다.

### **Int (32비트= 4바이트)**

`Int` 타입은 -2147483648 to 2147483647 까지 저장할 수 있습니다.

### **Long (64비트 = 8바이트)**

`Long` 타입은 -9223372036854775807 to 9223372036854775807 까지 저장할 수 있으며 선택적으로 값을 L로 끝낼 수 있습니다.

```kotlin
val myNum1 = 2147483647  // Int
val myNum2 = 2147483648  // Long
```

`int` 타입의 최대 값 2147483647이 초과되면 자동으로 `Long` 타입으로 정의됩니다.

## 실수형 (**Floating Point Types)**

실수형은 소수점 아래 숫자를 저장할 때 사용됩니다. (9.99 , 3.14.. 등)

```kotlin
//Float Example
val myFloat: Float = 5.75F

//Double Example
val myNum: Double = 19.99
```

```kotlin
val myNum1: Float = 35E3F  // 35000 표기
val myNum2: Double = 12E4  // 120000 표기
```

실수형은 `e` 또는 `E`를 이용해 `지수표기법`을 사용할 수 있습니다.

>**Float와 Double 무엇을 사용할까요?**

>코틀린을 비롯한 대부분의 프로그램 언어의 실수자료형에는 부동소수점 방식을 사용하고 있어 실>수를 이진수로 표현할 때 순환소수가 발생하여 정확한 실수 표현을 할 수 없습니다.

>Float형은 이 오차를 소수점 아래 7자리 까지 표현하지만 Double형은 15자리 까지 표현하여, 더 정>확한 실수를 계산하고자 하면 Double형을 사용하는 것을 권장합니다.

## **논리자료형 (Booleans)**

`Boolean` 데이터 타입은 참과 거짓 값을 저장할 수 있습니다.

```kotlin
val isKotlinFun: Boolean = true
val isFishTasty: Boolean = false
println(isKotlinFun)   // Outputs true
println(isFishTasty)   // Outputs fales
```

## **문자형 (Characters)**

`Char` 데이터 타입은 문자 하나를 저장하며 `따옴표( ‘ )`를 사용해 저장합니다.

```kotlin
val myGrade: Char = 'B'
```

자바에서는 Char 타입에 ASCII 코드를 기반으로 숫자를 대입하여 문자를 출력할 수 있지만 코틀린에서는 작동하지 않습니다.

```kotlin
val myLetter: Char = 66 // 런타임 에러 발생!
```

## **문자열 (String)**

`String` 타입은 문자 시퀀스를 저장하는데 사용되며 `큰 따옴표( “ )`를 사용해 저장합니다.

```kotlin
val myText: String = "Hello World"
```

## 배열 (Arrays)

배열은 여러 값을 하나의 변수로 저장할 때 사용할 수 있습니다.
(나중 챕터에서 다시 등장합니다.)

## 타입변환 (Type Conversion)

타입변환은 데이터의 타입을 다른 타입으로 바꿀 때 사용합니다.

숫자변환에서의 자바와 코틀린의 차이

```kotlin
val x: Int = 5
val y: Long = x // Error: Type mismatch
```

특정 숫자 데이터 타입을 다른 숫자 데이터 타입으로 변환할 때는 다음과 같은 함수를 사용하여야 합니다.`toByte()`, `toShort()`, `toInt()`, `toLong()`, `toFloat()`, `toDouble()`, `toChar()`

```kotlin
val x: Int = 5
val y: Long = x.toLong() // OK!
```

# 6. 연산자 (Operators)

연산자는 변수와 값들을 다룹니다.

값을 `operand`(피연산자)라고 하며 이를 계산하는 것을 `operator`(연산자) 라고 합니다.

아래는 연산자 사용 예제입니다.

```kotlin
var x = 100 + 50

var sum1 = 100 + 50       // 150 (100 + 50)
var sum2 = sum1 + 250     // 400 (150 + 250)
var sum3 = sum2 + sum2    // 800 (400 + 400)
```

코틀린의 연산자는 다음 그룹으로 나눌 수 있습니다.

- 산술연산자(Arithmetic operators)
- 대입연산자(Assignment operators)
- 비교연산자(Comparison operators)
- 논리연산자(Logical operators)

## 산술연산자 (Arithmetic Operators)

- + : 덧셈, x + y
- - : 뺄셈, x - y
- * : 곱셈, x * y
- / : 나눗셈, x / y
- % : 나머지, x % y
- ++ : 증감 , ++x
- — : 가감, —x

>증감, 가감연산자는 표기 위치에 따라 선연산 후연산처리를 정할 수 있습니다.

>a++: 실행 후 증감, ++a: 증감 후 실행

## 대입연산자(Assignment operators)

- =
- +=
- -=
- *=
- /=
- %=

변수에 값을 할당(대입)할 때 사용하는 연산자입니다.

아래 예제와 같이 사용합니다.

```kotlin
var x = 10
```

변수에 값을 연산하면서 대입할 때는 += 와 같이 사용해줍니다.

```kotlin
var x = 10
x += 5 // x = 15
```

## 비교 연산자

값을 비교하여 참과 거짓을 반환시킬 때 사용합니다.

- == : 같을 때
- ≠ : 같지 않을 때
- `> : 클 때
- < : 작을 때
- ≥ : 크거나 같을 때
- ≤ : 작거나 같을 때

## 논리연산자

&& : AND 조건

|| : OR 조건

! : Not 조건

# 7. 조건문 (If ... Else)

조건에 따라 다른 동작을 실행시킬 수 있습니다.

## if문

```kotlin
if (20 > 18) {
  println("20 is greater than 18")
}
```

조건이 true일 경우 해당하는 코드블럭이 실행됩니다.

## else문

```kotlin
val time = 20
if (time < 18) {
  println("Good day.")
} else {
  println("Good evening.") 
}
// Good evening. 출력됨
```

else: 조건이 false일 경우 코드블럭이 실행됩니다, (time의 값이 18미만이라면 Good day가 실행됩니다.)

## else if

```kotlin
val time = 22
if (time < 10) {
  println("Good morning.")
} else if (time < 20) {
  println("Good day.")
} else {
  println("Good evening.")
}
```

else if: 새로운 조건문을 작성하나, 앞 서 있는 조건문이 false일 때 실행됩니다.

```kotlin
val time = 20
val greeting = if (time < 18) {
  "Good day."
} else {
  "Good evening."
}
println(greeting) // Good evening
```

코틀린에서의 if문은 자바와 다르게 변수에 값을 할당하는 용도로 사용할 수 있습니다.

## When

하나의 값에 여러 조건이 필요할 때 `if ... else` 표현식대신, `when` 을 사용하면 더 읽기 쉽습니다.

```kotlin
val day = 4

val result = when (day) {
  1 -> "Monday"
  2 -> "Tuesday"
  3 -> "Wednesday"
  4 -> "Thursday"
  5 -> "Friday"
  6 -> "Saturday"
  7 -> "Sunday"
  else -> "Invalid day."
}
println(result) // Thursday
```

`when` 표현식은 자바의 `switch case` 표현식과 비슷합니다.

break문과 default문이 들어가는 자바보다 더 가독성이 좋습니다.

# 8. While 반복문 (While Loop)

코드블럭 내 실행문을 조건에 따라 여러차례 실행시킬 수 있습니다.

```kotlin
var i = 0
while (i < 5) {
  println(i)
  i++
}
```

조건 값을 증감시켜주는 걸 까먹으면 무한루프에 빠질 수 있으니 주의하세요.

## Do..While 반복문

`do..while` 반복문은 조건이 거짓이더라도 반드시 한번은 실행시킵니다.

```kotlin
var i = 0
do {
	  println(i)
	  i++
  }
while (i < 5)
```

# 9. 반복 제어문 (Break, Continue)

## Break

`break`는 반복문을 벗어납니다.

아래는 조건이 4가 되면 반복문을 벗어나는 예제입니다.

```kotlin
var i = 0
while (i < 10) {
  println(i)
  i++
  if (i == 4) {
    break
  }
}
```

## Continue

`continue` 는 조건에 따라 반복문을 한 차례 건너뜁니다.

아래 예제는 i가 4인 경우에만 반복문을 스킵합니다.

```kotlin
var i = 0
while (i < 10) {
  if (i == 4) {
    i++
    continue
  }
  println(i) // 0, 1,2,3,5,6,7,8,9
  i++
}
```

# 9. 배열 (Arrays)

배열은 하나의 변수에 여러 값을 넣을 때 사용합니다.

배열은 `arrayOf()` 함수를  이용하여 생성합니다.

```kotlin
val cars = arrayOf("Volvo", "BMW", "Ford", "Mazda")
```

## 배열에 접근하기

대괄호 기호를 이용해 배열의 요소에 접근할 수 있습니다.

```kotlin
val cars = arrayOf("Volvo", "BMW", "Ford", "Mazda")
println(cars[0])
// Volvo 출력
```

다음과 같이 특정 배열 인덱스의 값을 바꿀 수 있습니다.

```kotlin
cars[0] = "Opel"
```

## 배열의 크기

`size` 속성을 이용해 배열의 크기를 구할 수 있습니다.

```
val cars = arrayOf("Volvo", "BMW", "Ford", "Mazda")
println(cars.size)
// 4 출력
```

## 배열 요소 존재 확인

`in` 연산자를 이용해 배열의 요소 존재 여부를 구할 수 있습니다.

```kotlin
val cars = arrayOf("Volvo", "BMW", "Ford", "Mazda")
if ("Volvo" in cars) {
  println("It exists!")
} else {
  println("It does not exist.")
}
```

## 배열 반복문 사용

`for` 반복문을 이용해 배열의 요소에 반복문으로 접근할 수 있습니다.

```kotlin
val cars = arrayOf("Volvo", "BMW", "Ford", "Mazda")
for (x in cars) {
  println(x)
}
```

# 10. For 반복문 (For Loop)

코틀린의 `for문`은 자바의 for문과 형태가 다소 다릅니다.

자바처럼 전통적인 형태의 for문이 없으며, 배열이나 범위 등 값만을 반복할 수 있습니다.

```kotlin
val cars = arrayOf("Volvo", "BMW", "Ford", "Mazda")
for (x in cars) {
  println(x)
}

val nums = arrayOf(1, 5, 10, 15, 20)
for (x in nums) {
  println(x)
}
```

# 11. 범위 반복문 (Ranges)

`for loop` 와 함께 사용하며 `..` 기호와 함께 범위를 생성합니다.

문자의 범위를 지정할 수 있다.

```kotlin
for (chars in 'a'..'x') {
	print(chars)
}
```

당연히 숫자의 범위도 지정할 수 있다. (주로 사용)

```kotlin
for (nums in 5..15) {
  println(nums) // 5부터 15까지 출력
}
```

```kotlin
for (nums in 5..15) {
  if (nums == 10) {
    break
  }
  println(nums)
}

for (nums in 5..15) {
  if (nums == 10) {
    continue
  }
  println(nums)
}
```

`break`와 `continue` 또한 사용할 수 있다.

# 12. 함수 (Functions)

함수는 호출될 때만 실행되며, 메소드라고도 부릅니다.

## 정의된 함수

`println()`과 같이 미리 정의되어 있는 함수들

```kotlin
fun main() {
	println("Hello World")
}
```

## 직접 함수 만들고 호출하기

myFunction() 이라는 함수를 만들었습니다.

```kotlin
fun myFunction() {
  println("I just got executed!")
}
```

아래와 같이 호출할 수 있습니다.

```kotlin
fun main() {
  myFunction()
  myFunction()
  myFunction()
}

// I just got executed!
// I just got executed!
// I just got executed
```

## 함수 파라미터

코틀린에서는 함수의 파라미터에 타입을 지정해주어야 합니다.

```
fun myFunction(fname: String) {
  println("$fname Doe")
}

fun main() {
  myFunction("짱구")
  myFunction("철수")
  myFunction("훈이")
}
```

여러 파라미터를 사용할 땐 쉼표를 이용해 표기해줍니다.

```kotlin
fun myFunction(fname: String, age: Int) {
  println(fname + " is " + age)
}

fun main() {
  myFunction("John", 35)
  myFunction("Jane", 32)
  myFunction("George", 15)
}
```

학습 사이트

[https://www.w3schools.com/kotlin/index.php](https://www.w3schools.com/kotlin/index.php)