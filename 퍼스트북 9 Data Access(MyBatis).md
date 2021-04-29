Java에서 제공하는 SQL은 불필요하고 반복적인 코드 작성이 필요하다.

MyBatis를 이용해 간단하게 SQL를 실행할 수 있다.

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/043888f7-3774-4d39-a4ba-11f60a1454dc/Untitled.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/043888f7-3774-4d39-a4ba-11f60a1454dc/Untitled.png)

기존 자바에서의 JDBC 프로그래밍 단계

퍼스트북에는 JDBC에 대략적인 세팅과 실행방법도 나와있지만 여기선 생략

### 9.3 Data Source

```java
<bean id="dataSource"
				class="org.springframework.jdbc.datasource.DriverManagerDataSource">
		 <property name="driverClassName" value="${driver}" />
		 <property name="url" value="${dburl}" />
		 <property name="username" value="${username}" />
		 <property name="password" value="${password}" />
</bean>
```

DB에 연결하는 위 과정에서 매번 Connection 객체를 생성하는 것은 매우 번거롭다. Spring에서는 DataSource Bean을 독립적인 객체로 생성하여 DB연결하는 소스를 작성하는 것을 생략하여 작성할 수 있도록 지원한다. 위가 그 예시인데 DB에 맞는 드라이버 등 접속정보를 요구한다.

${driver}는 별도 properties 파일에서 값을 가져오는 것

Spring의 DB경우 연결이 필요한 경우 새롭게 세션을 생성하여 연결하게 된다. 이 때ㅇ 매번 새로운 연결을 할 때마다 TCP/IP에 의해 정보를 교환하게 되는데 결국에 SQL처리 시간보다 더 많은 시간이 소요될 수 있다. 이를 해결해주기 위해서는 DB connection pool을 활용한다.

오픈소스 DB connection pool 중 Apache의 DBCP가 많이 활용된다.(hikari CP 등 다양한 DBCP가 있다.)

```java
<bean id="dataSource" class="org.apache.commons.dbcp.BasicDataSource"
				 destroy-method="close">
<property name="driverClassName" value="${driver}" />
		 <property name="url" value="${dburl}" />
		 <property name="username" value="${username}" />
		 <property name="password" value="${password}" />
		 <property name="defaultAutoCommit" value="true"/>
		 <property name="initialSize" value="5"/>
		 <property name="maxActive" value="30"/>
		 <property name="maxIdle" value="5"/>
		 <property name="maxWait" value="30000"/>
		 <property name="validationQuery" value="SELECT 1 FROM DUAL"/>
		 <property name="testOnBorrow" value="true"/>
		 <property name="testOnReturn" value="false"/>
		 <property name="testWhileIdle" value="true"/>
		 <property name="timeBetweenEvictionRunsMillis" value="60000"/>
</bean>
```

DBCP를 활용하는 일반적인 DataSource Bean 설정 방법이다. 기존의 DataSource Bean설정보다 세팅할 수 있는 속성이 더 많다. 이는 DBCP를 제공하는 사이트의 doc을 보면 자세히 나와있다.

```java
@Resource(name = "dataSource")
DataSource dataSource;

@Test
public void testJdbcDataSource() throws Exception {
		Connection con = null;
 try {
		 con = dataSource.getConnection();
		stmt = con.createStatement();
		 rs = stmt.executeQuery("select 'x' as x from dual");
		 while (rs.next()) {
				 assertEquals("x", rs.getString(1)); …….
 }
}
```

설정한 Bean는 DI를 쉽게 활용할 수 있다.

### 9.4 Data Access (MyBatis)

데이터 엑세스를 할 때에도 connection 과정과 마찬가지로 connection 객체 생성, 연결, SQL준비, 실행, ResultSet의 결과처리 등 비슷한 형태의 소스코드를 작성해야 한다. 이를 관리해주는 프레임워크가 MyBatis이다. 프로그래머가 비즈니스 로직에만 집중할 수 있도록 자원 처리와 잡다한 연결과정,  매핑, 자원 반환까지 XML로 작성한 설정파일로 별도로 저장할 수 있게하는 프레임워크이다.

따라서 SQL문을 수정해야할 경우 소스파일은 그대로 XML파일만 수정하면 되므로 변경이 수월해지고 생산성이 올라간다.

만약 여러 DB를 활용해야할 경우 DB에 따라 별도 프로그램을 작성해서 관리해야하지만 MyBatis는 DB별로 별도 XML파일을 작성하면 되므로 쉽게 DB변경 실행도 가능하다. 소스코드가 특정 DB에 종속되지 않는다는 뜻, 호환성이 올라가고 관리도 쉽다.

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/148cc4d2-7f7d-40c2-b419-a621de9213de/Untitled.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/148cc4d2-7f7d-40c2-b419-a621de9213de/Untitled.png)

**MyBatis의 구성요소**

- Mapper파일 : SQL문을 XML로 관리한다.
- Mapper Config : MyBatis 설정파일
- Parameter Object  : SQL 조건절, 입력값을 받아오기 위한 Object이며 멤버 변수와 동일한 이름으로 SQL문에 활용한다. (멤버타입이나 맵 형태 모두 활용 가능)
- Result Object : SQL 실행결과를 DAO로 리턴하는 Object

MyBatis와 Spring가 연결하고 동작하기 위해선 Spring의 sqlSessionFactory 빈을 활용하고, 표준 프레임워크에서는 context-mybatis.xml 형태로 설정하여 연결에 필요한 datsource와 mapper config 파일 위치들을 지정한다.

**MyBatis를 활용하는 순서**

1) JDBC 드라이버 설치

```xml
<dependency>
		<groupId>mysql</groupId>
		<artifactId>mysql-connector-java</artifactId>
		<version>5.1.42</version>
</dependency>
```

2) SQL Mapper XML 파일 작성

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
"http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="customer">

<resultMap id="customerResult" type="customerVO"> //실행결과 값을 전달받을 클래스 매핑
		<id property="id" column="id" /> // SQL 실행결과 컬럼과 클래스 멤버변수 매핑
		<result property="name" column="name" />
		<result property="address" column="addr" />
		</resultMap>
													// Id : "insertCustomer", Parameter : customerVO
<insert id="insertCustomer" parameterType="customerVO"> 
				<![CDATA[
						insert into customer (id, name, addr) // Parameter 클래스의 멤버변수를
						values (#{id}, #{name}, #{address})   // 입력값으로 설정
				]]>
</insert>

<select id="selectCustomerList" parameterType="customerVO"
		resultMap="customerResult"> // 결과 값 Result Map id 명시
				<![CDATA[
				select id, name, addr
				from customer
				]]>
</select>
</mapper>
```

Mapper XML 파일에 작성되는 SQL은 고유한 id를 설정하고, DAO에서 SQL id를 호출하며 입력 값을  위한 Parameter 클래스를 전달하여 실행된다.

Prarmeter Object는 mapper config에서 설정한 alias를 활용하는 것이 편리하다.

입력값은 SQL안에 "#{id}" 형태로 설정되며 매핑을 위해서는 parameter Object의 멤버변수와 같게 이름을 적어야 한다.

"id", "name", "address"의 멤버변수를 가지는 customerVO 클래스를 parameter Object로 활용한다면, SQL안에 입력 값을 매핑할 때 #{id}, #{name}, #{address}와 같이 기술하면 된다.

결과 값은 ResultMap으로 설정된 Result Object를 통해 리턴되며, mapper파일 안에서 SQL 실행 결과 컬럼 값과 Result Object 멤버 변수와 매핑 정보가 기술된다. Parameter Object와 마찬가지로 ResultMap도 mapper config에서 설정한 alias를 사용한다.

3) SQL Mapper XML 파일

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE configuration PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
"http://mybatis.org/dtd/mybatis-3-config.dtd">

<configuration>
	 <typeAliases>
			 <typeAlias alias="customerVO" type="lab.CustomerVO" /> // Parameter Object, Result Object에
	 </typeAliases>                                            // 활용될 Object alias 지정

<mappers>
	 <mapper resource ="META-INF/sqlmap/mappers/lab-dao-class.xml" /> // Mapper 파일 위치 설정
</mappers>
</configuration>
```

MyBatis 설정에 필요한 파일이며, property, type aliase, mappers, setting과 같은 정보를 가진다.

위 예제에서는 type aliase와 mappers 관련 정보만 설정하여 활용한다.

Type alias는 mapper XML에서 처럼 Parameter Object 정보를 모두 기술하면 너무 길기 때문에 실제 클래스 정보는 Config 파일에만 기술하여 활용할 수 있도록 지원한다.

그리고 Mappers 파일의 위치를 등록한다. 파일의 위치는 *를 쓰지 말고 정확하게 기술해주어야 한다.

Sqlsession에서는 mappers 의 파일 위치를 * 등을 활용하여 설정할 수 있다.

```xml
<settings>
 <setting name="cacheEnabled" value="true"/>
 <setting name="lazyLoadingEnabled" value="false"/>
 <setting name="multipleResultSetsEnabled" value="true"/>
 <setting name="useColumnLabel" value="true"/>
 <setting name="useGeneratedKeys" value="false"/>
 <setting name="autoMappingBehavior" value="PARTIAL"/>
 <setting name="autoMappingUnknownColumnBehavior" value="NONE"/>
 <setting name="defaultExecutorType" value="SIMPLE"/>
 <setting name="defaultStatementTimeout" value="null"/>
 <setting name="defaultFetchSize" value="null"/>
 <setting name="safeRowBoundsEnabled" value="false"/>
 <setting name="mapUnderscoreToCamelCase" value="false"/>
 <setting name="localCacheScope" value="SESSION"/>
 <setting name="jdbcTypeForNull" value="OTHER"/>
 <setting name="lazyLoadTriggerMethods" value="equals,clone,hashCode,toString"/>
</settings>
```

Settings 정보는 위와 같이 MyBatis 설정을 위해 활용된다. 위에 기술된 value 값은 default 값이며, 설정이 생략되는 경우에는 자동으로 default 값을 가진다.

Mapper setting 속성에 자세한 내용은

[https://mybatis.org/mybatis-3/ko/configuration.html](https://mybatis.org/mybatis-3/ko/configuration.html) 문서를 참고하자.

4) Spring 설정

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns=http://www.springframework.org/schema/beans
xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/beans
http://www.springframework.org/schema/beans/spring-beans-4.0.xsd">

<bean id="sqlSession" class="org.mybatis.spring.SqlSessionFactoryBean">
	 <property name="dataSource" ref="dataSource" /> // DB 연결 설정 (DBCP 등)

	 <property name="configLocation"
			value="classpath:/META-INF/sqlmap/sql-mybatis-config.xml" /> // MyBatis 설정

		<property name="mapperLocations"
				value="classpath:/META-INF/sqlmap/mappers/lab-*.xml" /> // Mapper 파일 위치
</bean>                               // * 활용 가능하며 Matis의 위치설정과 중복 불가 

</beans>
```

Spring과 MyBatis의 연동을 위한 설정으로 sqlSession 객체 관리, DB연결에 필요한 DataSource, Mapper configuration 파일 위치를 설정한다. Mapper location는 * 기호로 일괄지정할 수 있다.
이 경우 Mapper configuration의 mapper 위치와는 중복지정할 수 없다.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns=http://www.springframework.org/schema/beans
 xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
xmlns:context="http://www.springframework.org/schema/context"
xsi:schemaLocation="http://www.springframework.org/schema/beans
http://www.springframework.org/schema/beans/spring-beans-4.0.xsd
http://www.springframework.org/schema/context
http://www.springframework.org/schema/context/spring-context-4.0.xsd">

		 <context:property-placeholder
					location="classpath:/META-INF/spring/jdbc.properties" />
		 <context:component-scan base-package="lab"/>
</beans>
```

Annotation 방식의 DI 설정을 위해 component-scan 설정을 하였고 DB properties 를 읽을 수 있도록 properties파일을 지정해 주었다.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns=http://www.springframework.org/schema/beans
	 xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:jdbc="http://www.springframework.org/schema/jdbc"
	xsi:schemaLocation="http://www.springframework.org/schema/beans
	http://www.springframework.org/schema/beans/spring-beans-4.0.xsd
	http://www.springframework.org/schema/jdbc
	http://www.springframework.org/schema/jdbc/spring-jdbc-4.0.xsd">

		 <bean id="dataSource" class="org.apache.commons.dbcp.BasicDataSource"
			destroy-method="close">
				<property name="driverClassName" value="${driver}" />
				<property name="url" value="${dburl}" />
				<property name="username" value="${user}" />
				<property name="password" value="${password}" />
		</bean>
</beans>
[
```

DB properties 설정은 위와 같다, 이는 앞서 소개한 9.3에 DataSource 축약화 과정에 소개된 방법과 동일하다.

여기서 value값에 들어가는 변수는 따로 속성파일을 생성하여 치환한다.

```xml
driver=com.mysql.jdbc.Driver
dburl=jdbc:mysql://127.0.0.1:3306/com
user=com
password=com01
```

만약 DB를 다르게 설정하거나 별도 DB를 활용하는 경우 사용한다.

5) DAO 작성

```java
@Repository("customerDAO")
public class CustomerDAO extends EgovAbstractMapper {

	public void insertCustomer(CustomerVO vo) {
	 insert("customer.insertCustomer", vo); // Mapper id와 parameter object 명시하여 
	 }                                      // mapper INSERT 실행

	 public List<CustomerVO> selectCustomerList(CustomerVO vo) {
	 return selectList("customer.selectCustomerList", vo);
	 }
}
```

EgovAbstractMapper 클래스를 상속받아 DAO 클래스를 작성한다. EgovAbstractMapper는 표준프레임워크에서 제공하는 SqlSessionDaoSupport의 하위 클래스로 sqlSession 설정과 메소드 호출 기능을 제공한다. 이를 쓰지 않으면 crud 호출문 앞에 getSqlSession().insert() 형태로 써주면 된다.

```java
public class CustomerVO {
		String id;
		String name;
		String address;
}
```

Parameter 및 Result Object로 활용되는 CustomerVO를 작성한다.

6) Service 및 Java 어플리케이션 작성

```java
@Service("customerService")
public class CustomerServiceImpl implements CustomerService {

		 @Resource(name = "customerDAO")
		 public CustomerDAO customerDAO;
		
				public void insertCustomer(CustomerVO customerVO) throws Exception {
						customerDAO.insertCustomer(customerVO);
		}

				public List<CustomerVO> selectCustomerList(CustomerVO customerVO)
					throws Exception {
							return customerDAO.selectCustomerList(customerVO);
			}
}
```

Controller, JUnit, Java 어플리케이션을 실행 시킬 수 있도록 서비스를 작성한다.

보통 트랜잭션 및 비즈니스 로직 처리를 위한 서비스를 정의, 실행 시키는 것이 일반적이며 DAO를 바로 호출하지 않는다. 여기서는 비즈니스 로직이 없기 때문에 괜히 똑같은 과정을 한번 더 하는 것 같아 불필요해 보여도 실제 복잡한 업무에서는 유연한 처리와 모듈의 재사용성을 높이기 위한 과정이다.

```java
public class CustomerApp {
		public static void main(String[] args) throws Exception {
			String configLocation = "classpath*:META-INF/spring/context-*.xml";
			ApplicationContext context
			= new ClassPathXmlApplicationContext(configLocation);
			
			CustomerService customer
							=(CustomerService)context.getBean("customerService"); //Service DI설정
			                                                             //(@Service 매칭)
			vo.id = "1";
			vo.name = "KIM";
			vo.address = "SEOUL";
			customer.insertCustomer(vo);
			
			vo.id = "2";
			vo.name = "LEE";
			vo.address = "PUSAN";
			customer.insertCustomer(vo);
// 위 두 값 insert
			
			List<CustomerVO> resultList= customer.selectCustomerList(vo); //입력된 값 SELECT
			int num = resultList.size();
			for (int i=0; i<num; i++) {
								CustomerVO resultvo = resultList.get(i);
								System.out.println("id="+resultvo.id);
								System.out.println("name="+resultvo.name);
								System.out.println("address="+resultvo.address);
				}
} }
```

이제 이를 테스트하는 자바 어플리케이션을 구현하여 실행하자.

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/543b6349-a014-4dd4-8c53-f84d5b7fa0d8/Untitled.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/543b6349-a014-4dd4-8c53-f84d5b7fa0d8/Untitled.png)

다음과 같은 결과를 얻을 수 있다.

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/380c459d-6f96-4e79-b446-072100e899a8/Untitled.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/380c459d-6f96-4e79-b446-072100e899a8/Untitled.png)

Java SQL과 MyBatis의 소스코드를 비교해보자. 확실하게 간결해졌다.

```java
<select id="selectEmpList" parameterType=“empVO" resultType=“empVO">
			select EMP_NO as empNo, EMP_NAME as empName
			 from EMP
		<trim prefix="WHERE" prefixOverrides="AND|OR ">
		<if test="empNo != null">
				 EMP_NO = #{empNo}
		 </if>
		 <if test="empName != null">
				 AND EMP_NAME LIKE '%' || #{empName} || '%‘
		 </if>
		 </trim>
 </select>
```

MyBatis는 조건에 따라 SQL문을 포함하거나 미포함시킬 수 있는 동적 SQL을 지원한다.

아래 예제에서는 empNo가 null이 아니거나 empName이 null이 아닐 경우 where절 조건에 포함되게 한다. 조건이 만족하지 않는 경우 where절도 포함되지 않으며 이 외에도 foreach(반복문), choose(조건문) 등을 사용할 수 있다.

### 9.5 Transaction 처리

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/06b0f8f4-2fb1-49ac-b005-7952f52151c8/Untitled.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/06b0f8f4-2fb1-49ac-b005-7952f52151c8/Untitled.png)

Transaction는 DB 데이터의 정합성을 위해 반드시 필요하다.

쇼핑몰에서 고객이 주문한 상품을 배송할 때 시스템에서 진행단계를 배송 상태로 수정하는 처리와 배송정보를 입력하는 두 가지의 데이터 처리가 있다고 가정해보자.

만약 처리 단계를 수정하는 단계에서 에러가 발생했을 때 배송정보가 입력이 되면 배송 단계가 아닌 데도 배송 처리가 된다. 올바른 결과를 가지려면 두 처리가 모두 성공적으로 처리가 되어야 한다.

이런 데이터의 정합성 관리를 Transaction 처리라고 한다.

Transaction을 활용하기 위해서는 먼저 서비스를 제공할 transaction manager를 결정해줘야 한다.

Transaction Manager는 Sprinng에서 제공하는 DataSource 기반으로 관리하는 방법과 WAS에서 제공하는 JTA(Java Transaction API)를 활용하는 방법이 있다. 

```java
<bean id="transactionManager"
	 class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
	 <property name="dataSource" ref="dataSource" />
</bean>
```

DataSource로 활용하는 방법은 위와 같이 Bean을 생성하면 된다.

JTA를 활용하는 방법은 WAS에서 transaction manager를 설정하고 이를 JNDI로 연결하는 방식으로 앞에서 설명한 connection pool을 활용하는 방법과 비슷하다. 

```java
<tx:jta-transaction-manager />
		<jee:jndi-lookup id="dataSource" jndi-name="dbmsXADS"
				 resource-ref="true">
		 <jee:environment>
				 java.naming.factory.initial=weblogic.jndi.WLInitialContextFactory
				 java.naming.provider.url=t3://was:7002
		 </jee:environment>
</jee:jndi-lookup>
```

jndi-name이나 provide URI는 환경에 맞추어 변경을 해야하며 별도의 bean을 정의하지 않아도 좋다.

상용 WAS를 쓰면 WAS에서 제공하는 JTA를 활용하자.

Transaction manager가 결정되면 소스코드에서 transaction을 처리하는 방식을 결정한다.

1. Annotation을 활용한 처리
2. AOP를 활용한 처리
3. 직접 코드에서 호출하는 방법

세 방법 중 어느것을 선택해도 결과는 같고 편리한 걸 선택하면 좋다.

AOP를 활용하면 XML로 advice와 point cut 설정만 하면 소스코드에 아무런 설정이 없어도 transaction이 적용되어 편리하다.

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/e7e747c6-a928-4f7d-a758-601ddf68b9b9/Untitled.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/e7e747c6-a928-4f7d-a758-601ddf68b9b9/Untitled.png)

Transaction 테스트 시나리오

Transaction 설정 테스트를 위해서 추가로 employee테이블을 생성한 후에 동일한 값
(“1”,”KIM”,”SEOUL”)을 INSERT 한다. 다음으로 customer 테이블의 값에서 “SEOUL”을
“BUSAN”으로 수정하고, employee는 수정시에 컬럼 수를 초과로 값을 입력하여 고의로 에
러를 유발한다. 트랜잭션 처리를 한 상태에서 결과를 출력하면 rollback 처리가 되어 수정
이전의 값인 “SEOUL”을 유지하게 된다. 트랜잭션 처리를 하지 않고 결과를 출력하면 수정
된 값이 그대로 유지되어 “PUSAN”이 출력되게 된다.

다음은 트랜잭션 처리를 설계하는 과정이다.

```sql
create table employee (
id varchar(10) primary key,
name varchar(10),
addr varchar(10));
```

앞에서 생성한 customer 테이블을 그대로 이름만 변경해 새로 생성하자.

```xml
<bean id="txManager"
				class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
		<property name="dataSource" ref="dataSource"/>
</bean>

<tx:annotation-driven transaction-manager="txManager"/> //anotation 방식의 설정
```

앞서 employee 테이블 처리를 위한 mapper 및 DAO를 신규로 생성하고, transaction 적용되도록 설정을추가하였다. 

anntation 기반으로 설정하기 위해 spring의 xml설정파일을 위와 같이 수정하자.

```java
@Service("customerService")
public class CustomerServiceImpl implements CustomerService {

		 @Resource(name = "customerDAO")   
		 public CustomerDAO customerDAO;
		                                  // DAO 연결을 위한 DI 설정
		 @Resource(name = "employeeDAO")
		 public EmployeeDAO employeeDAO;  
		
		public void insertCustomer(CustomerVO customerVO) throws Exception {
				customerDAO.insertCustomer(customerVO);
				employeeDAO.insertEmployee(customerVO);
		}
		
		public void deleteCustomer(CustomerVO customerVO) throws Exception {
				customerDAO.deleteCustomer(customerVO);
				employeeDAO.deleteEmployee(customerVO);
		}
		
		@Transactional //Transaction 적용 (메소드 전체)
		public void updateCustomer(CustomerVO customerVO) throws Exception {
				customerDAO.updateCustomer(customerVO);
				CustomerVO vo = new CustomerVO();
				vo.id = "1";
				vo.name = "KIMKIMKIMKIMKIM";
				vo.address = "SEOUL";
				employeeDAO.updateEmployee(vo); // 에러 유발을 위해 값을 재정의하고 DAO 호출
		}
		
		public List<CustomerVO> selectCustomerList(CustomerVO customerVO)
				throws Exception {
						return customerDAO.selectCustomerList(customerVO);
		}
		
		public List<CustomerVO> selectEmployeeList(CustomerVO customerVO)
				throws Exception {
						return employeeDAO.selectEmployeeList(customerVO);
		}
}
```

employee에 대한 DAO, mapper를 생성했으면 서비스에 transaction 처리를 하기 위해 설정을 추가한다.  업데이트 처리하는 updateCustomer 메소드에 @Transactional를 적용하였고 두 테이블에 수정하도록 각각 DAO의 update 메소드를 호출한다. employeeDAO에는 prameter object를 새로운 값으로  정의하여 전달한다.

```java
public class CustomerApp {
		public static void main(String[] args) {
				String configLocation = "classpath*:META-INF/spring/context-*.xml";
				ApplicationContext context =
				new ClassPathXmlApplicationContext(configLocation);
		
				CustomerService customer=
								(CustomerService)context.getBean("customerService");
				List<CustomerVO> resultList;
				CustomerVO vo = new CustomerVO();
				try {
						customer.deleteCustomer(vo);
				
						vo.id = "1";
						vo.name = "KIM";
						vo.address = "SEOUL";
						customer.insertCustomer(vo);  // insert 입력 값 세팅 및 insert
				
						resultList= customer.selectCustomerList(vo);
						printResult(resultList);
				
						vo.id = "1";
						vo.name = "KIM";
						vo.address = "PUSAN";
						customer.updateCustomer(vo); // update 입력 값 세팅 및 update
		
				} catch(Exception e) {
						e.printStackTrace();
				}
				finally {
								try {
												resultList= customer.selectCustomerList(vo);
												printResult(resultList); // 에러후에 결과 출력을 위해 
								} catch (Exception e) {          // finally 에서 처리
												e.printStackTrace();
								}
					}
			}
						public static void printResult(List<CustomerVO> resultList) {
						int num = resultList.size();
						for (int i=0; i<num; i++) {
						CustomerVO resultvo = resultList.get(i);
						System.out.println("id="+resultvo.id);
						System.out.println("name="+resultvo.name);
						System.out.println("address="+resultvo.address);
						}
			}
}
```

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/d4562217-fb74-44a5-95c3-ff259ddd06ff/Untitled.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/d4562217-fb74-44a5-95c3-ff259ddd06ff/Untitled.png)

Java 어플리케이션을 실행하여 결과를 확인해 보면 다음과 같다. Service에서
Transaction을 적용하기 위해 @Transactional을 설정하고 실행하면 rollback 처리가 되어
이전의 “SEOUL”을 유지하게 되고, @Transactional을 주석처리 한 후에 실행하면
transaction처리가 되지 않기 때문에 수정된 “PUSAN” 값이 그대로 출력된다.

위에 Serviceimpl 소스에서 employee의 insert id 입력값 한도를 초과시켜 에러가 발생했기 때문에 customer의 업데이트가 함께 반영이 되지 않는것(롤백)이다.

```xml
<aop:config>
		 <aop:pointcut id="requiredTx"
				 expression="execution(* lab.*Impl.*(..))" />
		 <aop:advisor advice-ref="txAdvice" pointcut-ref="requiredTx" />
</aop:config>

<tx:advice id="txAdvice" transaction-manager="transactionManager">
		 <tx:attributes>
				 <tx:method name="find*" read-only="true" />
				 <tx:method name="createNoRBRole" no-rollback-for="NoRoleBackTx" />
				 <tx:method name="createRBRole" rollback-for="RoleBackTx" />
				 <tx:method name="create*" />
		 </tx:attributes>
</tx:advice>
```

앞에 설정한 annotation 방식은 Service의 메소드 별로 Transactional 설정이 필요하지만 위와 같이 AOP를 활용하면 lab 패키지 밑에 Impl로 끝나는 모든 클래스의 메소드에 transaction이 설정된다.

```xml
<bean id="transactionTemplate"
		 class="org.springframework.transaction.support.TransactionTemplate">
		 <property name="transactionManager" ref="transactionManager" />
</bean>
<bean id="transactionManager"
		 class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
		 <property name="dataSource" ref="dataSource" />
</bean>
```

위 예시는 transactionalTempltae을 활용해 개발자가 직접 transaction 영역을 지정할 수 있다.

```java
public void testInsertCommit() throws Exception {
	 transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			 public void doInTransactionWithoutResult(TransactionStatus status) {
				 try {
						 …
				 } catch (Exception e) {
						 status.setRollbackOnly();
				 }
		 }
 });
```

Template 빈을 활용해 execute()로 정의된 영역에 transaction이 설정된다.
