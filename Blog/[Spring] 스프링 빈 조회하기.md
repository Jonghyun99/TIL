# [Spring] 스프링 빈 조회하기

### 모든 Bean 출력하기

> 메소드

```java
getBeanDefinitionNames() // 스프링의 모든 빈 이름으로 조회
ac.getBean(빈 이름 입력) // 빈 이름으로 빈 객체(인스턴스) 조회
```

> 코드

```java
AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    String[] beanDefinitionNames = ac.getBeanDefinitionNames();

    for (String beanDefinitionName : beanDefinitionNames) {
        Object bean = ac.getBean(beanDefinitionName);
        System.out.println("name = " + beanDefinitionName + " object = " + bean);
    }
}
```

> 출력값

```java
name = org.springframework.context.annotation.internalConfigurationAnnotationProcessor object = org.springframework.context.annotation.ConfigurationClassPostProcessor@71a8adcf
name = org.springframework.context.annotation.internalAutowiredAnnotationProcessor object = org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor@27462a88
name = org.springframework.context.annotation.internalCommonAnnotationProcessor object = org.springframework.context.annotation.CommonAnnotationBeanPostProcessor@82de64a
name = org.springframework.context.event.internalEventListenerProcessor object = org.springframework.context.event.EventListenerMethodProcessor@659499f1
name = org.springframework.context.event.internalEventListenerFactory object = org.springframework.context.event.DefaultEventListenerFactory@51e69659
name = appConfig object = hello.core.AppConfig$$EnhancerBySpringCGLIB$$564c7a08@47e2e487
name = memberService object = hello.core.member.MemberServiceImpl@201a4587
name = memberRepository object = hello.core.member.MemoryMemberRepository@61001b64
name = orderService object = hello.core.order.OrderServiceImpl@4310d43
name = discountPolicy object = hello.core.discount.RateDiscountPolicy@54a7079e
```

> 설명

getBeanDefinitionsNames의 값들을 배열 변수에 넣고 for문으로 getBean에 하나씩 대입한다.

---

### 직접 생성한 Bean만 출력하려면?

위 출력값은 직접 설정한 Bean 외 Spring에서 자동으로 등록하는 Bean도 함께 출력되고 있다.

본인이 직접 설정한 Bean만 출력하기 위해선 아래와 같이 사용한다.

> 코드

```java
AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    String[] beanDefinitionNames = ac.getBeanDefinitionNames();

    for (String beanDefinitionName : beanDefinitionNames) {

					BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

					if (beanDefinition.getRoel() == BeanDefinition.ROLE_APPLICATION) {
			        Object bean = ac.getBean(beanDefinitionName);
			        System.out.println("name = " + beanDefinitionName + " object = " + bean);
						}
		    }
		}
```

> 설명

```
BeanDefinition : Bean에 대한 메타데이터 정보를 다루는 클래스
Role ROLE_APPLICATION : 직접 등록한 애플리케이션 빈
Role ROLE_INFRASTRUCTURE : 스프링이 내부에서 사용하는 빈
```

BeanDefinition 클래스로 생성한 변수에  getBeanDefinition메소드의 인자값으로 Bean의 이름을 넣어은 값을 대입하여 Bean의 정보를 담는다.

그 다음 조건문을 이용하여 bean의 정보가 직접 만든 값일 때만 출력하게 한다.

---

### 단일 Bean 조회

모든 Bean이 아닌 특정 Bean을 확인하고 싶을 때 방법이다.

> 메소드

```java
ac.getBean("memberService", MemberServiceImpl.class) // getBean(빈이름, 타입)
ac.getBean("memberService") // getBean(빈이름)
ac.getBean(MemberServiceImpl.class) // getBean(타입)
```

이름으로 검색, 타입으로 검색, 둘다 사용한 검색 모두 쓸 수 있다.

> 코드

```java
AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
			System.out.println(ac.getBean("memberService", MemberServiceImpl.class));
      System.out.println(ac.getBean("memberService"));
      System.out.println(ac.getBean(MemberServiceImpl.class));
```

> 출력

```java
hello.core.member.MemberServiceImpl@5386659f
hello.core.member.MemberServiceImpl@5386659f
hello.core.member.MemberServiceImpl@5386659f
```

조회에 이름을 쓰거나 타입을 쓰거나 이름, 타입 둘다 사용해도 똑같은 결과가 나온다.

### 만약 조회할 스프링 빈이 없다면?

```java
NoSuchBeanDefinitionException: No bean named 'xxxxx' available // 에러 발생
```

NoSuchBeanDefinitionException 에러가 발생한다.

---

### 특정 타입의 빈을 모두 조회하기

> 메소드

```java
getBeansOfType(조회할 클래스);
```

> 코드

```java
AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SameBeanConfig.class);

Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);
    for(String key : beansOfType.keySet()) {
        System.out.println("key = " + key + " value = " + beansOfType.get(key));
        assertThat(beansOfType.size()).isEqualTo(2);
    }
```

> 출력

```java
Creating shared instance of singleton bean 'memberRepository1'
Creating shared instance of singleton bean 'memberRepository2'
```

> 설명

Bean 설정파일을 getBeansOfType으로 호출하면 인자값으로 받은 클래스를 모두 리턴해준다.

리턴 타입은 Map이고 제네릭은 <String, Type>이 된다.

iterator 등 반복문 출력 로직으로 조회할 수 있다.

---

### 상속관계의 빈 조회

상속관계의 빈들은 부모타입을 조회하면 자식타입도 모두 조회된다.

 최고 부모인 Object 타입으로 조회하면 모든 스프링 Bean을 조회한다.