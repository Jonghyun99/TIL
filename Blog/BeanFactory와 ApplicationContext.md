# BeanFactory와 ApplicationContext

## BeanFactory

- 스프링 컨테이너의 최상위 인터페이스이다.
- 스프링 빈을 관리하고 조회하는 역할을 담당한다.
- getBean()을 제공한다.

### ApplicationContext

- BeanFactory 기능을 모두 상속받아 제공하며 다양한 부가기능을 제공한다.

    ```java
    public interface ApplicationContext extends EnvironmentCapable, ListableBeanFactory, HierarchicalBeanFactory,
    		MessageSource, ApplicationEventPublisher, ResourcePatternResolver

    // Application은 위와 같은 인터페이스들을 상속받아 BeanFactory에서 
    // 할 수 있는 단순 관리 조회를 넘어 더 다양한 작업을 할 수 있다.
    ```

- BeanFactory의 차이점

    ```java
    1. 메시지 소스를 활용한 국제화 기능 (각 나라 언어를 인식하여 그 언어에 맞게 처리해줌)
    2. 환경변수 (로컬, 개발, 운영을 구분하여 처리)
    3. 애플리케이션 이벤트 (이벤트를 발행하고 구독하는 모델을 편리하게 지원
    4. 편한 리소스 조회 (파일, 클래스패스, 외부 등 리소스 편리하게 조회)
    ```

### 정리

- ApplicationContext는 BeanFactory를 상속받는다.
- ApplicationContext는 빈 관리기능 + 편리한 부가기능을 제공한다.
- BeanFactory를 직접 사용하기보다는 ApplicationContext를 사용하며 이 두개를 통틀어 스프링 컨테이너라고 칭함