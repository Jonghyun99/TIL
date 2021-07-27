### REST의 정의

- REpresentational State Transfer 의 약자
- 자원을 이름으로 구분해 상태를 주고받는 모든걸 의미
- 수행 대상이 되는 리소스는 URI로 정의된다. (JSON, XML, jpg, mp4 등 모든 것)
- URL도 URI의 일종이다.

### 메소드(Method, 행위(verb))

REST API에서는 리소스에 대한 행위는 HTTP Method로 CRUD와 매핑되어 다루진다.

URI와 Method로 서버에 대한 요청은 다음과 같다.

```java
GET www.example.co.kr/images/main
```

 HTTP 메소드 + 리소스 URI 형태를 보인다.

다음은 HTTP 메소드인 GET 메소드를 main.jpg 파일의 내용을 요청하는 REST API이다.

```java
GET www.example.co.kr/images/main HTTP/1.1 Accept:image/jpg
```

/image/jpg 라는 리소스 정보를 요청하는데 사용하는 프로토콜은 HTTP/1.1, 파일의 형태는 이미지로 jpg포맷을 띄고 있다는 것이다.

### 표현 (Representation)

HTTP 메소드는 URI로는 원하는 내용을 모두 표현할 수 없다.

예를들어 새로운 유저를 생성하는 REST API는 다음과 같다.

```java
POST http://www.example.co.kr/users/newuser
```

호스트에 있는 newuser 사용자를 추가하는 REST API인데. 특성에는 이름뿐만 아니라 직업, 나이 등 다양한 특성이 존재한다. 이는 XML, JSON, YAML 등과 같은 언어를 이용하게 된다

이를 바디(Body)혹은 Payload라고 한다. 이것을 사용할 때 HEADER부분에 

Content-Type:application/json과 같은 헤더를 추가해주어야 한다.

```java
POST http://www.example.co.kr/users/newuser COntent-Type:application/json
{
	"username" : "newusers",
	"age" : "20"
}
```

### REST API 특징

- Uniform 
리소스에 상관 없이 동일한 API 메소드를 갖는다. 즉 리소스 처리가 일관되게 요청됨
- Stateless
세션정보를 서버에 유지하지 않는다 (자원절약)
- Cacheable
기존 웹 인프라 사용 가능, 같은 여러 요청이 있을 때 클라이언트 캐시에서 가져온 정보를 반환할 수 있다.
- Self-descriptiveness
REST APIO 메시지는 이해하기 쉽다.
- Client-server architecture
독립된 클라이언트에게 API를 제공하기만 하여 클라이언트, 서버 간 역할이 명확히 분리된다.
- Layered System 
Multi layer로 구성될 수 있다. 클라이언트는 서버와 통신하는지 알 수 없다. 확장, 보안 향상 가능
- Code on demand
REST API는 서버에서 수행 스크립트를 바아 클라이언트 사이드 수행 할 수도 있다.

장점

- 사용이 쉽다
- 클라이언트 서버가 명확히 분리된다.
- 원하는 데이터 표현을 사용할 수 있다.

단점

- HTTP 한계에 묶인다.
- 표준이 없어서 관리하기 어렵다.
- RDBMS에 사용하기 어렵다.
