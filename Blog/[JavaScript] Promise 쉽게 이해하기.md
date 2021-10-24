# [JavaScript] Promise 쉽게 이해하기

Promise란 자바스크립트에서 비동기 처리를 이용한 패턴이다.

기존 비동기 처리에 이용하는 콜백 함수는 `콜백 헬` 발생 시 가독성이 나쁘고, 예외처리가 힘든 단점이 있어 `프로미스`가 등장했다.

프로미스에 대해 구글의 설명을 보거나 강의 영상을 보면 설명이 꽤 난해하게 느껴진다.

그렇기에 쉽게 이해할 수 있도록 공부해보았다.

## 프로미스의 사용

프로미스는 일단 선언과 실행부로 나누어 사용한다.

각 부분의 구조를 살펴보면

### 선언부

```jsx
// Promise 객체의 생성
const promise = new Promise((resolve, reject) => {
  // 비동기 작업을 수행한다.

  if (/* 비동기 작업 수행 성공 */) {
    resolve('result');
  }
  else { /* 비동기 작업 수행 실패 */
    reject('failure reason');
  }
});
```

위와 같이 작성되어 있다.

선언부 구조가 **new Prmoise**라는 객체와 함수 인자로 resolve와 reject 함수를 받고있다는 것만 기억하자.

(해당 글에서는 기본적인 이해를 위한 글이므로 race나 all같은 함수는 다른 메뉴얼을 찾아보자.)

### 실행부

```jsx
promise.then(function (text) {
	console.log(text);
}, function (error) {
	console.error(error);
});
```

객체에 .then 메서드를 이용하여 응답 결과를 호출한다.

then(함수1, 함수2)라고 생각하자.

함수1에는 resolve의 매개변수가, 함수2에는 reject의 매개변수가 할당된다.

위 실행부는 promise.then(resolve의 매개변수, reject의 매개변수) 로 해석할 수 있다.

promise는 조건에 따라 성공, 실패 예외 반환 등을 할 수 있는 비동기 메소드이다.

Ajax와 비슷한 형태를 가지고 있다.

### 체인 구성

```jsx
//result의 값으로 1이 입력된다면

promise.then(function (result) {
	console.log(result); //출력: 1
	return result*2  //2
}).then(function(result) {
	//첫 번째 체인
	console.log(result); //출력: 2
	return result*2;  //4
}).then(function(result) {
	//두 번째 체인
	console.log(result); //출력: 4
	return result*2;  // 8
});
```

결과에 한번더 then메소드를 붙여 처리할 수 있다.

동작하는 구조를 쉽게 이해하기 위해 단순하게 다루었기 때문에 부족하거나 틀린게 있을 수 있다.

더 자세하고 깊이있는 내용은 다른 블로그나 스펙을 참고하길 바란다.