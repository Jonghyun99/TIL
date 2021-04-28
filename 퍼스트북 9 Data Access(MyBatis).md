# 9. Data Access(MyBatis)

Java에서 제공하는 SQL은 불필요하고 반복적인 코드 작성이 필요하다.

MyBatis를 이용해 간단하게 SQL를 실행할 수 있다.

![9%20Data%20Access(MyBatis)%20b44f01e2c81044b3aafb570b06aab547/Untitled.png](9%20Data%20Access(MyBatis)%20b44f01e2c81044b3aafb570b06aab547/Untitled.png)

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

DBCP를 활용하는 일반적인 DataSource Bean 설정 방법이다. 기존의 DataSource Bean설정보다 요구하는 속성이 더 많다. 이는 DBCP를 제공하는 사이트의 doc을 보고 세팅하자.

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

![9%20Data%20Access(MyBatis)%20b44f01e2c81044b3aafb570b06aab547/Untitled%201.png](9%20Data%20Access(MyBatis)%20b44f01e2c81044b3aafb570b06aab547/Untitled%201.png)

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

<insert id="insertCustomer" parameterType="customerVO">
				<![CDATA[
						insert into customer (id, name, addr)
						values (#{id}, #{name}, #{address})
				]]>
</insert>

<select id="selectCustomerList" parameterType="customerVO"
		resultMap="customerResult">
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

결과 값은 ResultMap으로 설정된 Result Object를 통해 리턴되며, mapper파일 안에서 SQL 실행 결과 컬럼 값과 Result Object 멤버 변수와 매핑 정보가 기술된다. Parameter Object와 마찬가지로 ResultMap은 mapper config에서 설정한 alias를 사용한다.