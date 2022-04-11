# 10. MVC(Model View Controller)

### 10.1 JSP 웹 프로그래밍

JSP는 HTML을 그대로 활용하면서 "<% ... %>" 사이에 Java 언어를 입력할 수 있도록 도와주어 기존 웹 프로그래밍 방식보다  가독성을 올려준다.

```c
void print_rows() {
		 printf("<tr><td>%d</td><td>%s</td><td>%8.2f</td><td>%d</td></tr>\n",
				 emp_rec.emp_number, emp_rec.emp_name,
				 emp_rec.salary, emp_rec.dept_no);
}
```

위 소스는 초창기 웹 프로그래밍의 예시이다. 매우 가독성이 안좋다.

![10%20MVC(Model%20View%20Controller)%2055546ed49fd4421a8ad5f2d024c8440c/Untitled.png](10%20MVC(Model%20View%20Controller)%2055546ed49fd4421a8ad5f2d024c8440c/Untitled.png)

프로젝트 디렉토리 구성

JSP 프로그래밍은 Dynamic Web Project로 구성되어 프로젝트 내에 디렉토리는 위 WebContent에 웹 관련 자원(HTML, CSS, JavaScript)이 들어가게 된다.

### WebContext

- index.jsp : Default 실행을 위한 JSP
- helloworld.jsp : Hello World 출력 예제
- personForm.jsp : 신상 정보를 입력하는 폼
- personSuccess : 폼에서 입력받은 정보를 출력

다음은 jsp로 이루어지는 인자 넘기기의 간단한 예제이다.

```xml
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>사원정보</title>
</head>
<body>
<h1>사원정보</h1>
<form action="<%=request.getContextPath()%>/person/personSuccess.jsp"
method="post"> //submit 시에 호출할 URL
	 <div>NAME :
	 <input name="name"/></div><br>
	 <div>COMPANY :
	 <input name="company"/></div><br>
	 <div>PHONE :
	 <input name="phone"/></div><br>
	 <div>EMAIL :
	 <input name="email"/></div><br>
	 <div><input type="submit" value="register"></div>
</form>
</body>
</html>
```

```xml
<%@ page contentType="text/html; charset=UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>사원정보</title>
</head>
<body>
<%     <!--전달받은 값을 변수로 치환-->
	request.setCharacterEncoding("UTF-8");
	 String name = request.getParameter("name");
	 String company = request.getParameter("company");
	 String phone = request.getParameter("phone");
	 String email = request.getParameter("email");
%>
<div>NAME: <%=name%></div>
<div>COMPANY: <%=company%></div>
<div>PHONE: <%=phone%></div>
<div>EMAIL: <%=email%></div>
</body>
</html>
```

personForm으로부터 인자를 받아 출력하는 personSuccess의 코드이다.

위 코드 중 request.getContextPath()는 현재 실행되는 어플리케이션의 루트 URL를 반환한다.

![10%20MVC(Model%20View%20Controller)%2055546ed49fd4421a8ad5f2d024c8440c/Untitled%201.png](10%20MVC(Model%20View%20Controller)%2055546ed49fd4421a8ad5f2d024c8440c/Untitled%201.png)

실행결과

실행화면은 다음과 같다.

### 10.2 MVC 개요

JSP는 직관적으로 코드를 짤 수 있지만 로직이 복잡한 프로그램을 개발하기에는 불편하다.

- Model : 어플리케이션 데이터와 비즈니스 로직을 담는다.
- View : Model의 정보를 사용자에게 표시한다. 하나의 Model를 다양한 View에서 사용할 수 있다.
- Controller : 사용자의 요청을 받아 Model에 데이터를 전달하고 로직을 실행시키고 View를 선택한다.

MVC패턴은 기존의 웹 코딩에서 합쳐져 있던 HTML출력, DB연계, 인수처리, 비즈니스 로직 등을 나누어 작업하는 방법이다.

모델1는 뷰와 로직이 JSP페이지 하나에서 처리하는 구조이고

모델2(MVC2)는 뷰와 로직을 나누어 처리하는 구조이다.

모델 2에서 모델은 서비스클래스, 자바빈이 담당하고 컨트롤러는 서블릿이 담당하며 요청 결과는 뷰인 JSP에 출력된다.

**Spring MVC의 구성요소**

- Dispatcher Servlet : 웹 요청을 전달 받는 Front Controller 역할
- Handler mapping : URL로 전달된 웹 요청을 어떤 Controller가 처리할지 결정
- Controller : 비즈니스 로직 수행 결과를 ModelAndView에 반영
- ModelAndView : Controller가 요청한 결과를 반영하는 Model 객체와 이동할 View로 구성
- ViewReslover : 어떤 View를 선택할지 결정
- View : Model의 결과 데이터를 View Reslover에 선택된 형태로 출력

![10%20MVC(Model%20View%20Controller)%2055546ed49fd4421a8ad5f2d024c8440c/Untitled%202.png](10%20MVC(Model%20View%20Controller)%2055546ed49fd4421a8ad5f2d024c8440c/Untitled%202.png)

Spring MVC 동작

**Spring MVC의 구성흐름**

1. Client 요청이 들어오면 Dispatcher Servlet이 가장 먼저 요청을 받는다. 주로 *.do 형태의 요청을 처리한다.
2. HandlerMapping이 요청에 해당하는 Controller를 리턴한다.
@RequestMapping의 형태로 요청되는 URL을 처리할  Controller 메소드가 Mapping된다.
3. Controller는 비즈니스 로직을 호출하고 전달 받은 결과를 ModelAndView에 반영하여 리턴한다.
4. ViewReslover는 어떤 형태로 출력할 것인지 형태를 결정하고, view name을 받아 해당되는 view객체를 리턴한다. 주로 JSP가 활용되며, XML,JSON 등 활용 가능
5. View는 Model 객체를 받아 결과를 출력한다.

### 10.4 Spring MVC 설정 및 활용

```java
String configLocation = "classpath*:META-INF/spring/context-*.xml";
ApplicationContext context = new ClassPathXmlApplicationContext(configLocation);
```

```java
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:META-INF/spring/context-*.xml"})
public class CustomerServiceTest { … }
```

Java 어플리케이션 환경에서 Spring 프레임워크 활용을 위해 ApplicationContext를 활용해 Spring Container를 생성하는 과정이다.

각 설정파일의 명칭이나 값은 환경마다 다를 수 있으며

퍼스트북 표준에는 아래와 같이 사용한다.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns:xsi=http://www.w3.org/2001/XMLSchema-instance
xmlns=http://java.sun.com/xml/ns/javaee
xmlns:web=http://java.sun.com/xml/ns/javaee
xsi:schemaLocation="http://java.sun.com/xml/ns/javaee
http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd" id="WebApp_ID" version="2.5">

<context-param>
 <param-name>contextConfigLocation</param-name>
 <param-value> classpath*:META-INF/spring/context-*.xml </param-value>
 </context-param>  <!-- 스프링 설정파일 위치 -->

 <listener>
 <listener-class>
org.springframework.web.context.ContextLoaderListener</listener-class>
 </listener> <!-- ContextLoaderListener 활용 -->

 <servlet>
 <servlet-name>mvcAction</servlet-name>
 <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
 <init-param>           <!--Dispatcher Servlet 설정-->
 <param-name>contextConfigLocation</param-name>
 <param-value>/WEB-INF/config/springmvc/context-*.xml </param-value>
 </init-param>  <!-- 스프링 웹 설정파일 위치-->
 <load-on-startup>1</load-on-startup>
 </servlet>

 <servlet-mapping>
 <servlet-name>mvcAction</servlet-name>
 <url-pattern>*.do</url-pattern> <!-- *.do 형태 요청은 DispatcherServlet으로 전송하여 처리 -->
 </servlet-mapping>
</web-app>
```

웹 환경에서도 어플리케이션이 동작할 때 Spring 프레임워크 활용을 위한 설정은 web.xml을 사용한다. 

**web.xml :** 비즈니스 로직을 위한 서비스, DAO 등에서 ContextLoaderListener를 통해 ApplicationContext를 생성하여 스프링이랑 연결한다.

웹 환경에서 필요한 Spring MVC설정, 메시지 설정, 다국어, validator 등의 설정은 Dispatcher Servlet에서 WebApplicationContext를 생성한다.

web.xml은 Web Project에서 src>main>webapp>WEB-INF 하위에 있다.

```java
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns=http://www.springframework.org/schema/beans
 xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
xmlns:p="http://www.springframework.org/schema/p"
xmlns:context="http://www.springframework.org/schema/context"
xsi:schemaLocation="http://www.springframework.org/schema/beans
http://www.springframework.org/schema/beans/spring-beans-4.0.xsd
http://www.springframework.org/schema/context
http://www.springframework.org/schema/context/spring-context-4.0.xsd">

 <context:component-scan base-package="lab" /> <!-- 어노테이션 세팅 -->

<bean class="org.springframework.web.servlet.view.InternalResourceViewResolver"
 p:prefix="/WEB-INF/jsp/" p:suffix=".jsp" />
  <!-- View 설정, customerList.do를 매핑할 경우 /WEB-INF/jsp/customerList.jsp 로 결정됨 -->
</beans>
```

Dispatcher Servlet 설정에서 URL 패턴이 *.do로 명시되어 있어서 

do로 끝나는 요청이 들어오면 Dispatcher Servlet으로 전달되어 처리된다. 꼭( *.do가 아니여도 됨)

WebApplicationContext는 spring-servlet.xml (servlet-context.xml) 에서 지정되어 활용된다.

설정파일에서는 MVC에서 활용되는 annotation(controller, RequestMapping, ModelAttribute 등)을 component scan하기 위한 설정과 View를 지정하기 위한 View Resolver가 위치한다.

HandlerMapping는 별도의 설정이 없으면 default handler가 설정된다.

### 10.4 Spring MVC 활용

web.xml과 Spring MVC 설정이 되었다면 다음으로 할 일은 Controller 작성이다. Controller에서 사용하는 annotation은 다음과 같다.

- Controller : 해당 클래스가 Controller임을 선언
- RequestMapping : 요청에 대해 어떤 Controller 및 메소드가 처리할지 맵핑
- RequestParam : Controller 메소드 인자와 웹 요청 인자와 맵핑
- ModelAttribute : Controller 메소드의 인자와 리턴값을 Model 객체와 바인딩
- SessionAttributes : Model 객체를 세션에 저장 및 사용

```java
@Controller
public class HelloController {

		@RequestMapping(value="/hello.do", method = RequestMethod.GET)
		public String helloGet(){
		 ...
		}

		@RequestMapping(value="/hello.do", method = RequestMethod.POST)
		public String helloPost(){
		 ...
		}
}
```

RequestMapping은 GET, POST 방식의 웹 요청에 대해 Controller를 맵핑하여 실행시켜준다.

위와 같은 식으로 작성할 수 있는데, 축약형으로 GetMapping, PostMapping(이외 HTTP메소드도 가능)로 작성하여도 무관하다. (Spring 4.3 이후 추가된 어노테이션임)

```java
@Controller
public class HelloController {

 @RequestMapping("/hello.do")
 public String hello(@RequestParam("name") String name,
			 @RequestParam(value="pageNo", required=false) String pageNo){
 ...
	 }
}
```

@RequestParam은 웹 요청 파라미터를 변수로 맵핑 하기 위한 것으로  Java에서 제공하는 request.getParameter()와 같은 역할을 한다.

RequestParameter가 필수가 아닐 경우 required=false로 옵션을 해주어야 함에 유의하자

(default는 true임)

![10%20MVC(Model%20View%20Controller)%2055546ed49fd4421a8ad5f2d024c8440c/Untitled%203.png](10%20MVC(Model%20View%20Controller)%2055546ed49fd4421a8ad5f2d024c8440c/Untitled%203.png)

Controller에서 ModelAtttribute 활용

RequestParam은 파라미터 개수만큼 정의가 되기 때문에 40개의 인자를 넘겨야 한다면 40개를 정의해야 한다. 이런 상황에서 사용 하는 것이 @ModelAttribute이다.

특정 Form에서 submit이 호출되면 input 태그의 값들은 controller로 전달되어 VO객체에 담긴다.

코드에서 "personinfo"라고 지정된 값은 View의 commandName과 동일해야 맵핑이 된다.

Controller로 전달 된 값은 @ModelAttribute에 정의된 대로 person 클래스를 타입으로 하는 command 클래스로 생성된다.

```java
		private String formView = "person/personForm";
		private String successView = "person/personSuccess";
		
		@RequestMapping(value = "/person.do", method=RequestMethod.GET)
		protected String idInput(ModelMap model) throws Exception{
				model.addAttribute("personinfo",new person());
				return formView;
		}
		
		@RequestMapping(value = "/person.do", method=RequestMethod.POST)
		protected String regist(@ModelAttribute("personinfo") person command,
		BindingResult errors, ModelMap model) throws Exception {

		model.addAttribute("pinfo",command);
		return successView;
}
```

이제 Controller에 리턴 값을 view에 전달해주는 일만 남았다.

ViewResolver에 설정된 대로 JSP로 view가 보여지게 된다. Controller에 GET일 때와 POST일 때 다른 변수를 리턴하고 있다. GET일 때는 formView (person/personForm)을 리턴하고, POST일 때는 successView (person/personSuccess)를 리턴한다.

처음 person.do가 호출될 때는 GET방식으로 새로운 값을 입력할 수 있는 폼이 출력이 되고, submit을 하면 POST로 전달되어 처리가 되고 personSuccess가 출력된다.

String로 리턴할 때에는 위 ViewResolver에 정의했던 context-servlet.xml설정에 의해서

/WEB-INF/jsp/ 디렉터리 밑에서 해당되는 JSP를 찾게 된다. @ModelAttribute를 써서 바인딩 된 model 클래스 이외의 정보를 view로 전달하기 위해서는 인수 부분에 ModelMap model를 정의해 주고, 전달할 값을 클래스에 넣은 다음 model.addAttribute("pinfo", command) 의 형태로 추가하여 리턴하고 view에서는 ${pinfo.name}의 형태로 출력한다. 출력 결과는 다음과 같다.

```java
<body>
		<div>NAME: ${pinfo.name}</div>
		<div>COMPANY: ${pinfo.company}</div>
		<div>PHONE: ${pinfo.phone}</div>
		<div>EMAIL: ${pinfo.email}</div>
</body>
```

```java
@RequestMapping(value = "/person3.do", method=RequestMethod.GET)
protected String personInput3(ModelMap model) throws Exception{
		person pobject = new person();
		pobject.setName("KIM");
		pobject.setCompany("company");
		pobject.setPhone("02-1234-5948");
		pobject.setEmail("abc@email.com");
		
		model.addAttribute("personinfo",pobject);
		return formView;
}
```

수정 폼을 구현할 경우 addAttribute에 view의 form commandName을 그대로 적어주고 객체를 매핑해주면 input tag값이 초기화 되어 들어가게 된다.