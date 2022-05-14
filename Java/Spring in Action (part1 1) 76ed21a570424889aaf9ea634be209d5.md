# Spring in Action (part1.1)

### 1.1 스프링이란?

스프링은 **스프링 애플리케이션 컨텍스트**라는 **컨테이너**에서 컴포넌트를 생성, 관리한다.

(컴포넌트 : 애플리케이션을 구성하는 단위)

컴포넌트, 또는 **빈**은 컨텍스트 내부에서 어우러져 애플리케이션을 만든다.

빈의 상호 연결은 **의존성 주입**(Dependency Injection, DI) 패턴으로 수행된다.

즉,  의존(사용)하는 다른 빈의 생성과 관리를 자체적으로 하는 대신 컨테이너가 해주며, 이 컨테이너는 모든 컴포넌트를 생성, 관리, 컴포넌트가 필요한 빈에 주입(연결)한다.

(생성자 인자, 속성 접근자 메서드를 통해 처리됨)

예를 들어 재고 서비스와 제품 서비스 컴포넌트가 있다고 하면, 제품 서비스는 제품의 완전한 정보 제공을 위해 재고 서비스에 의존한다. (컨테이너 안에서)

컨테이너 외에도 웹 프레임워크, 저장 옵션, 보안, 통합, 런타임 모니터링 등 다양한 기능들을 제공함

이전 스프링 버전으로 빈의 상호 연결은 XML을 사용하여 컨테이너에 알려주었다.

```xml
<bean id = "inventoryService"
			class = "com.example.InventoryService" />

<bean id = "productService"
			class = "com.example.ProductService" />
	<contructor-arg ref="inventoryService" />
</bean>
```

그러나 최신 버전에서는 XML보다는 자바 기반의 구성(configuration)이 더 많이 사용된다.

다음 코드는 위 XML 구성과 동일하다.

```java
@Configuration
public class ServiceConfiguration {
	@Bean
	public InventoryService inventoryService() {
		return new InventoryService();
	}
	@Bean
	public ProductService productService() {
		return new ProductService(inventoryService());
	}
}
```

여기서 @Configuration 애노테이션은 각 빈을 스프링 애플리케이션 컨텍스트에 제공하는 구성 클래스라는 걸 스프링에게 알림, 구성 클래스의 메서드는 @Bean 어노테이션이 지정되어 있고, 각 메서드에 반환되는 객체가 애플리케이션 컨텍스트의 빈으로 추가되야 하는 것을 나타낸다.

(기본적으로 빈의 ID는 빈을 정의하는 메서드 이름과 동일)