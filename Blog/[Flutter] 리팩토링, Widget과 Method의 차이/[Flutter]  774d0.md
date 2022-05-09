# [Flutter] 리팩토링, Widget과 Method의 차이

<div class=”markdown-body”>

기존 코드

```dart
	Padding(
      padding: const EdgeInsets.symmetric(vertical: 16.0),
      child: Material(
        elevation: 5.0,
        color: Colors.lightBlueAccent,
        borderRadius: BorderRadius.circular(30.0),
        child: MaterialButton(
          onPressed: () {
            //Go to login screen.
            Navigator.pushNamed(context, LoginScreen.id);
          },
          minWidth: 200.0,
          height: 42.0,
          child: const Text(
            'Log In',
          ),
        ),
      ),
    ),
    Padding(
      padding: const EdgeInsets.symmetric(vertical: 16.0),
      child: Material(
        color: Colors.blueAccent,
        borderRadius: BorderRadius.circular(30.0),
        elevation: 5.0,
        child: MaterialButton(
          onPressed: () {
            //Go to registration screen.
            Navigator.pushNamed(context, RegistrationScreen.id);
          },
          minWidth: 200.0,
          height: 42.0,
          child: const Text(
            'Register',
          ),
        ),
      ),
    ),
```

위 소스에서 로그인과 가입 버튼은 같은 UI를 지니고 있다.

여기서 공통된 위젯을 추출하여 리팩토링을 할 수 있을 것이다.

<br/>

1. Class로 추출

```dart
RoundedButton(
  colour: Colors.lightBlueAccent,
  title: 'Log in',
  onPressed: () {Navigator.pushNamed(context, LoginScreen.id);},),
RoundedButton(
  colour: Colors.blueAccent,
  title: 'Register',
  onPressed: () {
    Navigator.pushNamed(context, RegistrationScreen.id);
  },
)
     

class RoundedButton extends StatelessWidget {
  RoundedButton(
      {Key? key,
      required this.colour,
      required this.title,
      required this.onPressed})
      : super(key: key);

  final Color colour;
  final String title;
  final VoidCallback onPressed;

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.symmetric(vertical: 16.0),
      child: Material(
        elevation: 5.0,
        color: colour,
        borderRadius: BorderRadius.circular(30.0),
        child: MaterialButton(
          onPressed: onPressed,
          minWidth: 200.0,
          height: 42.0,
          child: Text(
            title,
          ),
        ),
      ),
    );
  }
}
```

위와 같이 Padding위젯의 공통된 부분을 추출하여 별도의클래스(위젯)로 만들어 구현할 수 있다.

<br/>

1. Method로 추출

```dart
buildPadding(
	context, 
	'test1', 
	Colors.black87,
	 () {Navigator.pushNamed(context, RegistrationScreen.id)}
),
buildPadding(
	context, 
	'test2', 
	Colors.blueGrey, 
	() {Navigator.pushNamed(context, RegistrationScreen.id);}
),

  Padding buildPadding(BuildContext context, String title, Color colour,
      VoidCallback onPressed) {
    return Padding(
      padding: const EdgeInsets.symmetric(vertical: 16.0),
      child: Material(
        color: colour,
        borderRadius: BorderRadius.circular(30.0),
        elevation: 5.0,
        child: MaterialButton(
          onPressed: onPressed,
          minWidth: 200.0,
          height: 42.0,
          child: Text(
            title,
          ),
        ),
      ),
    );
  }
```

위는 클래스가 아닌 메소드로 분리한 소스이다.

메소드 방식의 경우 메소드가 직접 호출되고, 위젯을 직접 반환하는 간단한 구조를 가진다. 

클래스 방식은 클래스 생성자를 호출하는 방식이고 생성자는 반환값이 없는 메소드다 그러므로 반드시 build 메소드를 재정의해서 위젯이 리턴될 수 있도록 구현해야 한다.

나는 메소드 방식이 직관적이고 깔끔해보여서 선호했지만 여러가지 이유로 클래스로 리팩토링 하는 것을 권장한다고 한다.

<br/>

## 위젯 분리에 Class가 유리한 이유

클래스를 통한 리팩토링은 다음과 같은 이점들을 가진다.

- 성능의 최적화
- context api 사용
- hot reload 동작 확인
- widget tree 통합
- 에러 메세지의 구체화
- 서로 다른 두 widget간에 resources 처리 확인
- const 사용 유무 (main thread의 성능에 영향)

<br/>

### Method 방식은 Widget Tree가 인식하지 못한다.

![클래스 방식으로 작성한 위젯트리](%5BFlutter%5D%20%20774d0/0002.jpg)

클래스 방식으로 작성한 위젯트리

<br/>

![메소드 방식으로 작성한 위젯트리](%5BFlutter%5D%20%20774d0/0001.jpg)

메소드 방식으로 작성한 위젯트리

상단에 클래스 방식은 RoundedButton 클래스에서 연결되어 있음을 알 수 있으나

하단 메소드 방식은 리팩토링 하기 전과 같은 위젯트리를 가지고 있다.

만약 저 위젯을 수정해야할 경우가 생겼을 때 더 직관적으로 접근할 수 있다.

<br/>

### 더 안전하다.

Class로 분리하였을 때 StatelessWidget 속성을 사용하여 다른 부분에서 작은 변경사항이 생겨도 메소드 방식은 전체를 다시 호출하게 되지만, 클래스 방식은 그 외 부분들에 변화를 일으키지 않아 관계없는 위젯들이 다시 렌더링 되는 일이 없다.  즉, 수정한 부분만 Rebuild되어 개발 성능과 안정성에서 우월하다. 이를 근거로 불변하는 요소에 const 속성 적극적으로 활용해주는 것이 더 좋은 성능을 만들 수 있다고 한다.

<br/>

<br/>

이런 이유로 중복 위젯을 리팩토링할 때에는 클래스를 사용하며 다음 두가지를 유의하자.

1. StatelessWidget 일 것 (변화가 없는 위젯 이어야 한다.)
2. const 키워드를 적극적으로 활용한다. (바뀔 가능성이 없는 곳에 사용한다.)

자료참고

[https://changjoopark.medium.com/flutter-위젯-리팩토링-긴-위젯-코드는-어떻게-나누어야-할까-23950ff583ee](https://changjoopark.medium.com/flutter-%EC%9C%84%EC%A0%AF-%EB%A6%AC%ED%8C%A9%ED%86%A0%EB%A7%81-%EA%B8%B4-%EC%9C%84%EC%A0%AF-%EC%BD%94%EB%93%9C%EB%8A%94-%EC%96%B4%EB%96%BB%EA%B2%8C-%EB%82%98%EB%88%84%EC%96%B4%EC%95%BC-%ED%95%A0%EA%B9%8C-23950ff583ee)

[https://velog.io/@leeeeeoy/Flutter-Widget-Helper-Method](https://velog.io/@leeeeeoy/Flutter-Widget-Helper-Method)

</div>