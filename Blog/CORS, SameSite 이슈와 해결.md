# CORS, SameSite 이슈와 해결

## CORS (Cross-origin resource sharing)

브라우저는 보안상의 이유로 프로토콜, 호스트, 포트 등 출처가 다른 URL간의 통신을 제한한다.

예를들어 [`http://abcd.com`에서](http://abcd.com에서) [`http://1234.com`](http://1234.com) 을 요청하는 경우 발생한다.

이를 해결하기 위해서는 서버에서 Access-Control-Allow-Origin 헤더에 프론트 주소를 적어주어야 에러가 나지 않는다.

## SameSite

이전에는 프로토콜이 달라도 도메인이 같으면 같은 사이트로 취급을 했다.(samesite)

그렇기에 http에서 쿠키를 만들고 https를 사용하는 식의 취약점 문제로 브라우저는 http와 https간 통신을 제한했다.

form의 post메소드 요청으로 http에서 https로 쿠키를 보낼 수 없게되었다.

## 시도한 방법들

나는 ajax로 https서버에 http url로 요청을 했기 때문에 Cross origin과 SameSite에러가 발생했다.

이 문제로 반나절 넘게 삽질했다.

분명 서버의 컨트롤러에 CrossOrigin 헤더를 추가해주지만 계속 에러가 발생했다.

구글링으로 알아낸 방법으로 크롬브라우저의 secure속성을 제거한 뒤 실행시켰다.

CrossOrigin문제는 해결됐지만 SameSite에러는 해결되지 않았다.

DevTools의 Network의 Header를 살펴보니 http로 요청된 URL이 300번대 응답코드인 리다이렉션의 헤더에는 HTTP였지만 오류가 난 시점(400)의 Header를 보니 https의 url을 요청하고 있었다.

이는 https 서버에 http로 요청을 시도하여 발생한 에러로 요청 url의 http에 s하나를 더 붙여주어 간단하게 해결하였다.

신기하게도 뇌에서 안받아들여지던 CORS 관련 글들을 몸으로 겪고 나니 술술 읽힌다.