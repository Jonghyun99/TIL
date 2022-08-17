# 스프링- HTTP 요청메시지

스프링에서 HTTP요청을 받는 방법은 여러가지가 있다.

폼 데이터의 인자로 넘어올 때는 RequestParam, ModelAttribute를 사용할 수 있지만

Http 바디를 통해 직접 넘어올 때는 별도의 파싱이 필요하다.

## **텍스트 파싱**

### 1. InputStream (V1)

```java
@PostMapping("/request-body-string-v1")
public void requestBodyString(HttpServletRequest request,
HttpServletResponse response) throws IOException {
	ServletInputStream inputStream = request.getInputStream();

	String messageBody = StreamUtils.copyToString(inputStream,
	StandardCharsets.UTF_8);

	log.info("messageBody={}", messageBody);

	response.getWriter().write("ok");
}
```

Http 메시지 바디의 데이터를 InputStream으로 읽는다.

### 2. InputStream (V2)

```java
@PostMapping("/request-body-string-v1")
public void requestBodyString(InputStream inputStream, Writer responseWriter) throws IOException {
	String messageBody = StreamUtils.copyToString(inputStream,
	StandardCharsets.UTF_8);

	log.info("messageBody={}", messageBody);

	responseWriter.writer("ok");
}
```

스프링 MVC는 InputSream, OutputStream(Writer)을 파라미터로 지원하여 이를 이용해 더 간결한 컨트롤러를 작성할 수 있다.

### 3. HttpEntity (V3)

```java
@PostMapping("/request-body-string-v3")
public HttpEntity<String> requestBodyStringV3(HttpEntity<String> httpEntity) {
	String messageBody = httpEntity.getBody();
	log.info("messageBody={}", messageBody);
	return new HttpEntity<>("ok");
}
```

스프링 Mvc에서 제공하는 HttpEntity는 HTTP 요청의 header, body 정보를 간편하게 조회할 수 있다.

응답에도 사용할 수 있어 메시지 바디와 헤더 정보를 직접 반환할 수 있다.

아래는 HttpEntity를 상속받은 객체이다.

**RequestEntity**

- HttpMethod, Url 정보가 추가됨 (요청에 사용)

ResponseEntity

- Http 상태코드를 설정할 수 있음 (응답에 사용)

※참고

스프링 MVC에서 Http 메시지 바디를 읽거나 문자, 객체로 변환해 줄 때 HTTP 메시지 컨버터(HttpMessageConverter)를 사용한다

### 4. RequestBody (V4)

```java
@ResponseBody
@PostMapping("/request-body-string-v4")
public String requestBodyStringV4(@RequestBody String messageBody) {
	log.info("messageBody={}", messageBody);
	return "ok";
}
```

RequestBody를 사용하면 더 간편하게 조회할 수 있다.

Body정보만을 파싱하여 헤더 정보가 필요하면 HttpEntity나 @RequestHeader 를 사용하자.

- 요청 파라미터 조회: RequestParam, ModelAttribute
- HTTP 메시지 바디 직접조회: RequestBody

### @ResponseBody

응답 결과를 Http 메시지 바디에 직접 담아 전달할 수 있다.

(view를 사용하지 않음)

## Json 파싱

V1,V2 생략

### 1. RequestBody (V3)

```java
@ResponseBody
@PostMapping("/request-body-json-v3")
public String requestBodyJsonV3(@RequestBody HelloData data) {
	log.info("username={}, age={}", data.getUsername(), data.getAge());
	return "ok";
}
```

HttpEntity, RequestBody를 사용하면 HTTP 메시지 컨버터가 우리가 원하는 문자나 객체로 변환해준다.

@RequestBody 를 생략하면 ModelAttribute가 적용되어 body타입이 아닌 요청 타입을 처리하게 되어 생략하면 안된다.

### 2. HttpEntity (V4)

```java
@ResponseBody
@PostMapping("/request-body-json-v4")
public String requestBodyJsonV4(HttpEntity<HelloData> httpEntity) {
	HelloData data = httpEntity.getBody();
	log.info("username={}, age={}", data.getUsername(), data.getAge());
	return "ok";
}
```

마찬가지로 HttpEntity를 사용할 수 있다.

```java
@ResponseBody
@PostMapping("/request-body-json-v5")
public HelloData requestBodyJsonV5(@RequestBody HelloData data) {
	log.info("username={}, age={}", data.getUsername(), data.getAge());
	return data;
}
```

@ResponseBody

응답 또한 객체를 직접 바디에 넣어줄 수 있다.

HttpEntity를 사용해도 된다.

- @RequestBody
    - JSON 요청 → HTTP 메시지 컨버터 → 객체
- @ResponseBody 응답
    - 객체 → HTTP 메시지 컨버터 → JSON응답