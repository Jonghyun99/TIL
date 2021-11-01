// Promise 객체의 생성
const promise = new Promise((resolve, reject) => {
  // 비동기 작업을 수행한다.

var a=1;

  if (a==2) {
    reject('failure reason');
  }
  else { /* 비동기 작업 수행 실패 */
    resolve(1);
  }
});

// promise.then(function (text) {
// 	console.log(text);
// }, function (text) {
// 	console.error(text);
// });


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