# URI와 웹브라우저 요청 흐름

## URI(Uniform Resource Identifier)

직역 : 자원을 식별하는 통합된 방법

URI는 URL인 로케이터(locator)와 URN인 이름(name) 또는 둘다 같이 분류될 수 있다.

- URL : 자원의 위치를 알려주는 주소 (http://naver.com/book/novel?name="1984")
- URN : 자원의 이름 (urn:naver://book:1984)

- 위치는 변할 수 있지만 이름은 변하지 않는다.
- URN은 리소스에 이름을 부여한다.
- URN만으로 실제 리소스를 찾을 수 있는 방법이 보편화 되지 않음
- URN은 다소 URL과 비슷하게 생겼지만 현재는 쓰이고 있지 않다.
- 그렇기에 보편적으로 URI와 URL을 동일한 의미로 이야기한다.

### URL 문법

- scheme://[userinfo@]host[:port][/path][?query][#fragment]
- https://www.google.com:443/search?q=hello&hl=ko

- **scheme** : 주로 프로토콜이 들어감(https) (어떤 방식으로 자원에 접근할 것인가 하는 약속) http, https, ftp 등이 있으며 이러한 프로토콜은 포트를 생략할 수 있다.
- **userinfo@** : 사용자 정보를 포함하여 인증, 거의 사용하지 않음
- host(www.google.com)
- port: 일반적으로 생략시 http는 80, https는 443
- path ****(/search) : 리소스 경로, 계층적 구조
- query(q=hello&hl=ko) : key=value 형태이며 ?로 시작하고 &로 추가함
- [#fragment] : html 내부 북마크 등에 사용, 서버에 전송하는 정보 아님 (스크롤 이동)

## 웹 브라우저 요청 흐름

URL에 대해 알아보았는데, 이 URL로 어떻게 웹 브라우저가 요청하여 사이트를 볼 수 있는지 알아야 할 필요가 있다.

1. 사용자가 `[https://www.google.com:443/search?q=hello&hl=ko](https://www.google.com:443/search?q=hello&hl=ko)` 해당 url를 입력
2. 웹 브라우저는 www.google.com:443를 가지고 DNS서버에서 google.com에서 200.200.200.2를 조회함  (https 프로토콜 요청시 port는 저절로 443이 요청됨)
3.  조회한 정보로 **HTTP 요청 메시지**를 생성한다.

```jsx
// HTTP 요청 메시지 예시
GET /search?q=hello&hl=ko HTTP/1.1
HOST: www.google.com
```

1. SOCKET 라이브러리를 통해 전달, IP와 PORT정보로 TCP/IP에 연결한다 (3 handshake)
2. TCP/IP 패킷 생성 (HTTP 메시지를 감싼다.)
3. 인터넷 망을 통해 요청패킷을 목적지로 전송
4. 구글 서버가 요청패킷을 받아서 tcp/ip를 벗기고 요청 메시지를 해석함 (search?q=hello&hl=ko)
5. 해석된 데이터의 결과를 DB에 조회하고 응답 메시지를 만들어서 위 과정과 똑같이 송신

```jsx
// HTTP 응답 메시지 예시
HTTP/1.1 200 OK
Content-Type: text/html;charset=UTF-8
Content-Length: 3423

<html>
	<body>..<body>
</html>
```

1. 웹브라우저에서 HTTP 메시지 수신하여 내부 데이터로 html 데이터 렌더링을 하여 결과를 보게 된다.