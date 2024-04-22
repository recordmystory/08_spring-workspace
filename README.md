# 08_spring-workspace
#### 2024-04-04(목)
 - 환경설정

강의자료에서 sts 프로그램 다운

압축풀기

sts-bundle 파일 c드라이브로 옮기기

sts 실행 후 runtime environments에서 서버 지우기

package explorer내에 서버도 지우기 (옵션 체크 후 지우기)

package explorer내에 spring legacy project 만들기

https-content.xml : mvc 관련파일을 C:\workspaces\08_spring-workspace\.metadata\.plugins\org.springsource.ide.eclipse.commons.content.core에 넣기 (mybox에서 다운)

**사용자명이 한글일 경우 스프링 실행에 문제 생김**

프로젝트 새로 만들 때 Spring MVC Project 선택 후 next

스프링에서는 3번째 레벨까지 파일명을 작성해야 finish 버튼이 활성화됨

- pom.xml 수정 후 해야될 것

항상 pom.xml에서 자바, 스프링 버전 수정하기

프로젝트 우클릭 → Maven → Update Project → OK

위 과정을 해야 프로젝트에 반영됨

---
#### 2024-04-05(금)
- mavenrepository 사이트 접속 안될때

https://mvnrepository.com/

크롬 확장 프로그램 touch VPN 사용하면 됨

- spring 공식사이트

https://spring.io/

- bean은 곧 객체

- Spring Legacy Project를 만들면 기본적으로 HomeController와 home.jsp가 작성되어있음

- src/main/resources : xml 파일 보관하는 용도
- src/test/ java : 테스트 용도
- src/main/webapp : 배포되는 용도

- 빈 생성 방법
1. 어노테이션
2. xml (Spring Bean Configuration File)
    
    빈 등록하기
    
    ```xml
    <bean
      class="빈으로 등록하고자 하는 클래스명(패키지 포함해서 풀클래스명으로 작성)"
      id="이름"
    >
      <property name="필드명" value="주입하고자하는 값"/>
    	<constructor-arg value="주입하고자하는 값"/>
    </bean>
    ```
   
    ```xml
     	 <bean class="com.br.spring.ioc.xml.Student" id="student2" p:name="김말똥" p:academy="구트아카데미" p:classColor="blue" p:calc-ref="calculator"/>
    
    ```
    

### DI

- 등록된 Bean 가져오는 방법 (DI)

ApplicationContext 객체(컨테이너)를 통해서 빈 등록구문이 쓰여져있는 파일을 읽어들이고 해당 객체로부터 getBean으로 가져오기

- DI(Dependency Injection) : 의존성 주입

IoC와 관련된 내용으로 개발자가 직접 객체생성하지 않고 스프링 컨테이너가 가지고있는 Bean 객체를 자동으로 주입받아서 사용한다는 개념

### IoC

IoC (Inversion of Controller) : 제어역전
개발자가 프로그램을 제어하지 않고 Spring Framework가 프로그램의 전반적인 흐름제어
객체 생성 및 생명주기 관리, 의존관계 설정 등을 개발자가 아닌 Framework가 직접 관리
→ 스프링에서는 객체 == 빈(Bean)

IoC 컨테이너 (스프링컨테이너, 빈컨테이너)
위의 내용들을 전반적으로 관리하는 컨테이너
개발에 필요한 객체들을 스프링이 관리하게끔 하려면 먼저 빈으로 등록해야되고
이 빈들이 담겨있는 컨테이너 == IoC 컨테이너

컨테이너 클래스 종류

BeanFactory : Spring Bean Configuration File에 등록된 빈들을 전반적으로 관리하는 기본적인 컨테이너

ApplicationContext : 트랜잭션관리, 메세징 처리 등등의 기능에 추가

GenericapplicationContext : 파일 시스템 또는 클래스 경로에 있는 xml 설정 파일을 로딩해서 <bean>태그로 작성되어있는 것들을 빈으로 등록

AnnotationConfigApplicationContext - 자바 어노테이션을 이용해서 등록된 빈을 가져올 때 사용

상속 구조

BeanFactory

ㄴ ApplicationContext

ㄴ GenericApplicationContext

ㄴ GenericXmlApplicationContext (xml 파일에 등록된 빈 읽어들일 때 사용)

ㄴ AnnotationConfigApplicationContext (어노테이션으로 등록된 빈 읽어들일 때 사용)

- spring 사용전과 사용후의 차이

spring 사용시 한번 생성해둔 객체를 재사용이 가능하고(싱글톤), 메모리를 효율적으로 관리 가능함

- Bean 등록하는 방법

xml 방식 : Spring Bean Configuration File(xml)에서 <bean> 태그 이용해서 등록

어노테이션(자바) 방식 : @Configuration 클래스에 @Bean 어노테이션을 이용해서 등록

- context-param : listener
ContextLoaderListener 클래스에 의해서 root-context.xml 파일을 읽어들이면서 Spring Container를 생성하겠다.

```
	1) 서버 start시 제일 먼저 web.xml 파일 실행됨
	2) context-param + listener에 의해서 root-context.xml에 바로 읽혀짐 (pre-loading)
	3) root-context.xml 파일에 작성된 빈들이 스프링 컨테이너에 바로 등록됨

```

- 스프링 컨테이너로부터 Bean 객체를 가져오는 방법
    - 가장 근본적인 방법 : 컨테이너 객체(ApplicationContext)로부터 getBean으로 가져오는 방법
    
    - DI와 관련된 어노테이션을 이용하는 방법
    - @Inject : 등록되어있는 빈들 중 타입(class)이 일치하는 Bean 객체를 주입해줌
    - @Resource : 등록되어있는 빈들 중 이름(id)이 일치하는 Bean 객체를 주입해줌
    - @Authowired : 등록되어있는 빈들 중 타입으로 먼저 탐색하고 동일한 타입이 여러 개일 경우 이름으로 찾아서 주입
---
#### 2024-04-09(월)
- @Configuration 어노테이션 사용시

클래스명이 똑같은 클래스가 2개 있고, 두 개의 클래스에 @Configuration이 붙어있을 때 뒤에 이름을 지정해줘야 오류가 나지 않음

```java
@Configuration("이름")
```

### service(인터페이스)는 한 개, 해당 인터페이스를 구현하는 모바일용/웹용 구현 클래스 만들기

- 개발자가 직접 객체 생성할 경우

결합도가 높아지는 문제가 발생함

결합도(종속관계) : 연관관계의 클래스들 간 각 클래스 수정시 서로에게 영향을 미치는 정도

B 클래스 수정시 A 클래스도 일부 수정해야되는 상황이 발생할 때 두 객체간 결합도가 강하다고 표현함

**→ 스프링 IoC와 DI를 사용하면 결합도 문제를 해결할 수 있음**

- @Service 어노테이션을 클래스에 붙일 경우 빈으로 등록됨

빈 이름을 지정하지 않았을 경우 기본적으로 클래스명을 따서 등록됨

@Service 어노테이션을 한 클래스에만 작성하던지, @Service 어노테이션을 사용할 때 빈 이름 지정 후 선언할 때 빈 이름으로 선언

---

### DI 어노테이션 종류

- @Inject : 등록된 bean들 중 type(class)이 일치하는 Bean 객체를 주입해주는 어노테이션 + 이름으로 찾으려면 @Quallifier(”이름”)을 직접 명시해야됨
- @Resource : 등록된 bean들 중 이름(id) 이 일치하는 Bean 객체를 주입해주는 어노테이션
- @Autowired : 등록된 bean들 중 type(class)이 일치하는 Bean 객체를 주입해주는 어노테이션 + 타입이 여러 개일 경우 이름으로 찾아짐 → Autowired 같은 경우 @Qualifier이 내장되어있어서 명시할 필요 없음

- @Autowired 사용법
1. 필드로 가져오기 
    
    필드마다 매번 @Autowired 작성 (즉, 필드 10개일 경우 @Autowired도 10개 기입)
    
2. 생성자로 가져오기

생성자의 매개변수로 Bean 가져와짐

@Autowired 생략 가능

1. 메소드로 가져오기
    
    메소드의 매개변수로 Bean 가져와짐
    
    @Autowired 작성해야됨 (즉, 메소드 10개일 경우 @Autowired도 10개 기입)
    
    일반적으로 setter 메소드 형태로 작성 (메소드명 상관없긴함)
    

→ 필드가 많을 경우 생성자를 사용하는 게 편함

- IoC + DI의 장점
1. 메모리 효율적으로 관리 가능
    
    직접 new로 객체 생성할 경우 new에 의해서 메모리 영역에 매번 생성됨
    
    자주 사용될 객체일 경우 또는 다수가 한꺼번에 사용할 객체일 경우 자주 생성 + 소멸 반복됨 → 메모리를 빈번하게 사용하는 이슈가 발생됨
    

→ IoC와 DI를 적용하면 스프링 컨테이너가 해당 객체를 딱 한 번 생성해두고 가지고 있다가 필요한 시점에 생성된 객체를 주입해줌 (즉, 싱글톤(한 번 생성된 객체 사용) 적용)

1. 클래스간의 결합도 해소 가능 (결합도 낮출 수 있음)
    
    결합도가 높을 때 발생되는 문제점 : 특정 클래스의 내용 수정 시 해당 클래스를 사용하고 있는 다른 클래스에서도 코드를 수정해야됨
    
    → IoC와 DI를 적용하면 결합도가 낮아져서 소스코드의 수정을 최소화할 수 있음
    

---

### 스프링 MVC

- MVC 패턴
    
    디자인 패턴 중 하나의 애플리케이션 구현할 때 구성요소들을 Model, View, Controller로 역할을 구분하는 패턴
    
    코드가 분리되어있고 한 곳에 다 작성되어있을 경우 : 문제 발생시 찾기 어려움 (유지보수가 불편함)
    
    → MVC 패턴 적용하면 각 클래스마다 역할을 부여해서 코드 작성
    
    → 문제 발생시 보다 쉽게 찾을 수 있음 (유지보수에 용이해짐)
    
    Model 
    
    애플리케이션의 데이터와 관련된 역할을 수행
    
    JAVA 파일 (src/main/java에서 작업)
    
    View 
    
    애플리케이션의 화면(시각적인 요소)과 관련된 역할을 수행
    
    JSP 파일 (src/main/webapp/WEB-INF/views 에서 작업)
    
    Controller
    
    애플리케이션에서의 요청 처리 관련된 역할을 수행 (Model과 View 중계 역할)
    
    Java 파일 (src/main/java 에서 작업)
    
- servlet-context.xml : DispatcherServlet과 관련된 설정 파일

- [localhost:8888/mvc](http://localhost:8888/mvc로)로 요청시 main.jsp 띄우기

```java
@Controller // 빈 스캐닝에 의해서 자동으로 빈으로 등록
public class MVCController {
	// 스프링 사용전
//	@WebServlet("/")
//	public class XXXServlet extends HttpServlet{
//		public void doGet(HttpServletRequest request, HttpServletResponse response) throws XXXException{
	// request.getDispatcher("/WEB-INF/views/main.jsp").forward(response, request);
//}
//	}
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String abcd() { // 메소드명 의미 없이 지어도됨
		System.out.println("MvcController 클래스의 abcd 메소드 실행됨");
		
		// /WEB-INF/views/main.jsp로 포워딩
		return "main"; 
	}
}
```

servlet-context.xml (DispatcherServlet)에 기술되어있는 <annotation-driven> + context:component-scan에 의해서 자동으로 빈으로 등록됨

- ViewResolver 역할

DispatcherServlet으로 돌아오는 응답뷰명을 가지고 prefix(앞), suffix(뒤) 값을 붙여서 포워딩할 응답뷰의 경로를 완성시켜서 사용자에게 보여주는 역할

컨트롤러 쪽에서 return "main"; 하게되면 /WEB-INF/views/main.jsp의 경로가 완성됨

- <annotation-driven>

@Controller 어노테이션을 활성화 해주는 태그

각 요청시 실행할 Controller 클래스를 찾아 실행이 가능하게끔 하려면 HandlerMapping과 HandlerAdapter가 필요함

HandlerMapping과 HandlerAdapter가 자동으로 빈으로 등록시켜줌

- <component-scan>

base-package로 작성되어있는 package 내의 모든 클래스들을 스캔하면서 component(@Service, @Controller, @Repository) 어노테이션이 붙은 클래스들을 자동으로 빈으로 등록시켜주는 태그

---
#### 2024-04-09(화)
- @GetMapping 어노테이션

Spring 4부터 지원

@RequestMapping과 똑같은 역할을 함

- Controller에서 파라미터 값 받아오기
1. HttpServletRequest 방법
    
    매핑해주는 어노테이션이 붙은 메소드 내에 파라미터로 HttpServletRequest request 작성 후 request.getParameter로 받는 방법
    

- 스프링에서 제공하는 인코딩 필터 등록

```xml
<filter>
      <filter-name>encodingFilter</filter-name>
      <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
      <init-param>
         <param-name>encoding</param-name>
         <param-value>UTF-8</param-value>
      </init-param>
   </filter>
   
   <filter-mapping>
      <filter-name>encodingFilter</filter-name>
      <url-pattern>/*</url-pattern>
   </filter-mapping>
```

1. @RequestParam 이용하는 방법 (Spring에서 제공)
    
    @RequestParam  : request.getParameter를 대신해주는 어노테이션
    
    ```java
    @PostMapping("/enroll2.do")
    	public String memberEnroll2(@RequestParam(value="name") String name, @RequestParam(value="age") int age, @RequestParam(value="address") String addr) {
    		// 실행시킬 코드 작성
    	}
    ```
    
    1. 커맨드 객체
        
        커맨드 객체란 요청 파라미터들을 각 필드에 담고자 하는 객체
        
        내부적으로 해당 객체 기본생성자로 생성한 후에 각 필드의 setter 메소드로 전달값이 필드에 주입됨
        단, 전달되는 값의 키값이 담고자하는 필드의 필드명과 같아야됨!
        
        메소드의 매개변수로 객체 넣어야함
        
    
    - @RequestParam 사용시 int형 변수값이 비어있을 때 NumberFormatException이 뜨는데, 해결 방법
    1. int형 변수를 String형 변수로 선언
    2.  기본값 작성하기
    
    ```java
    @RequestParam(value=”age”, defaultValue=”50”)
    ```
    
    - 롬복(lombok) 사용
        
        vo에 필드만 적고 생성자, getter/setter 생성 안해도됨 → 롬복이 대신 생성해줌
        
        C:\Users\GD\.m2\repository\org\projectlombok\lombok\1.18.24 
        
        집에가서 installer로 다시 설치하기
        
        ![lombok_jar](https://github.com/recordmystory/08_spring-workspace/assets/113417749/b31ff03c-91f9-4288-9c46-4ed1ab29a938)
        
        - @Getter, @Setter, @ToString, @NoArgsConstructor, @AllArgsConstructor 어노테이션 사용해 vo 작성 가능
        
        ![lombok_eclipse](https://github.com/recordmystory/08_spring-workspace/assets/113417749/5f6b09b0-2aac-46f1-b7da-b09ce01f2188)
        
        - 롬복 사용시 getter/setter 메소드명 때문에 필드명 소문자 두 글자 이상으로 작성하기
        
        - @Builder : 생성자 대신 빌더 어노테이션을 사용하면 객체를 좀 더 편하게 생성 가능함
        
        ```java
        NoticeDto n = NoticeDto.builder()
				.title("제목")
				.content("내용")
				.no(1)
        			.build();
        ```
        
        매개변수 순서 상관없음
        
        - @RequiredArgsConstructor : 초기화 되지 않은 final 필드에만 생성자를 생성해줌
        
    - 모델에 데이터담기
        
        서블릿에서 하던 request.setAttribute 대신 model.addAttribute() 사용
        
        ```java
        @GetMapping("/list.do")
        	public String noticeList(Model model) {
        		model.addAttribute("list", noticeService.selectNoticeList());
        		return "notice/list";
        	}
        ```
        
        jsp에서는 EL 표현법으로 출력하면 됨
---
#### 2024-04-11(목)
- 조회는 주로 @GetMapping, 수정, 삭제 등은 @PostMapping

### ModelAndView

Model 객체와 View 객체가 합쳐져있는 형태
Model은 데이터를 담는 영역의 객체라면 View는 응답뷰에 대한 정보를 담을 수 있는 영역의 객체 (단, View는 단독으로 사용 불가)
ModelAndView 객체에 데이터와 응답뷰에 대한 정보 다 담고 해당 객체를 리턴하면 됨

```java
@GetMapping("/detail.do")
	public ModelAndView noticeDetail(int no, ModelAndView mv) {
		// 메소드 체이닝 가능
		mv.addObject("n", noticeService.selectNoticeByNo(no)).setViewName("notice/detail");;
		
		return mv;
	}
```

### Spring MVC 동작원리

1. 클라이언트 요청 (request)
2. 스프링에서 제공하는 DispatcherServlet이 요청을 먼저 받아줌 (FrontController 개념)
3. DispatcherServlet이 요청을 처리할 Controller를 찾아주는 HandlerMapping을 내부적으로 호출함
4. HandlerMapping은 요청을 처리할 Controller를 찾아 호출
    
    요청시 전달되는 값(파라미터) 처리하는 방법 (Controller에서 진행)
    
    - Spring 사용 전 : HttpServletRequest 객체로 전달받아 .getParameter(”키”)로 전달값 뽑아서 처리
    - Spring 사용 후 : HttpServletRequest 객체를 이용할 수 있긴 하나 @RequestParam으로 바로 변수로 받을 수도 있고 커맨드객체 방식으로 객체의 필드로 바로 받을 수도 있음
5. Controller는 비즈니스 로직 처리하는 Service 호출
6. 요청 처리 완료 후 Contrller는 응답을 처리할 Model and View 세팅 
    - Spring 사용 전 : 응답페이지 (포워딩 jsp)에서 필요한 데이터를 request에 담은 후 (setAttribute) RequestDistpatcher 이용해서 포워딩 진행
    - Spring 사용 후 : 응답페이지상에 필요한 데이터를 Spring에서 제공하는 Model 객체에 담고 응답페이지에 대해서는 리턴하는 방식(String으로 리턴), ModelAndView에 같이  담는 방식(ModelAndView 리턴)
    
    Model : 데이터를 담을 수 있는 영역 (requestScope)
    
    → Model은 보안적인 부분이 request보다 조금 더 향상된 객체
    
    View : 응답뷰를 담을 수 있는 영역
    
7. Controller에서는 ModelAndView 객체를 Dispatcher로 전달
8. DispatcherServlet은 해당 뷰 정보를 ViewResolver에게 전달
9. ViewResolver는 전달받은 뷰 정보를 갖고 prefix, suffix 값 붙여서 응답할 View 객체 생성
10. 해당 응답 뷰가 클라이언트에게 보여짐

## Spring Ajax

### pom.xml

- ajax를 위한 jackson databind 라이브러리 추가

데이터를 연결, 저장 변환 해주는 라이브러리

자바에서의 bean(객체) / map을 json 또는 xml 형식으로 변환시켜줌

https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind/2.14.2

### 회원관리 페이지 (회원관리1, 회원관리2)

- @ResponseBody

비동기식으로 데이터 응답시 필요한 어노테이션

해당 어노테이션 붙은 메소드에서 리턴되는 값은 뷰명(jsp)가 아니라 어떤 data(text, json, xml, …)라는 걸 의미

해당 어노테이션 사용 시 @GetMapping 어노테이션 안에 produces=”text/html; charset=utf-8 작성 안하면 한글 깨짐

- serialize()

직렬화해주는 메소드

```java
data: $("#member_form").serialize()
```

- @RestController

이 클래스 안에 모든 메소드들에 전부 @ResponseBody가 붙여진채로 동작함

### 출력문 대신 로그 사용 : LogBack

[Logback Manual](https://logback.qos.ch/manual/index.html)

[](https://mvnrepository.com/artifact/ch.qos.logback/logback-classic)

출력문을 과다하게 사용하면 성능 측면에서 과부하가 올 수 있음

- pom.xml에 LogBack 라이브러리 추가

- logback.xml
    1. appender : 전달된 로그를 어느 위치에 출력할건지 결정하는 태그 
        
        ConsoleAppender : 로그를 콘솔에 출력
        - JDBCAppender : 로그를 RDB에 출력
        - FileAppender : 로그를 파일에 출력
        - RollingFileAppender : 로그를 매일 새로운 파일에 출력
        
    2. logger / root : 출력할 메세지를 appender에 전달하는 역할
        
        name 속성 : 로그주체(로그가 발생되는 클래스)
        level 속성 : 로그 레벨 설정
        
        debug : 개발 시 디버그 용으로 사용하는 메세지
        info : 정보성 메시지
        warn : 지금 당장의 문제는 없지만 향후 시스템 에러의 원인이 될만한 것의 경고성 메시지
        error : 어떤 요청 처리 중 발생된 문제에 대한 메시지
        fatal : 아주 심각한 에러 메시지

---
#### 2024-04-12(금)
### 로그 출력

```java
	private Logger logger = org.slf4j.LoggerFactory.getLogger(MemberController1.class);
	logger.debug("아이디 : {}, 비밀번호 : {}", id, pwd);
```

### Spring & MyBatis

- pom.xml

DB 관련 라이브러리 추가

https://mvnrepository.com/artifact/com.oracle.database.jdbc/ojdbc8/23.2.0.0

https://mvnrepository.com/artifact/org.springframework/spring-jdbc/5.3.33

https://mvnrepository.com/artifact/org.mybatis/mybatis/3.5.13

https://mvnrepository.com/artifact/org.mybatis/mybatis-spring/2.0.6

https://mvnrepository.com/artifact/org.bgee.log4jdbc-log4j2/log4jdbc-log4j2-jdbc4.1/1.16

- log4jdbc 공식 홈페이지

https://log4jdbc.brunorozendo.com/

- DB 계정 생성 (sbatis)

```sql
CREATE USER sbatis IDENTIFIED BY sbatis;

GRANT RESOURCE, CONNECT TO sbatis;
```

```sql
CREATE TABLE NOTICE(
    NO NUMBER PRIMARY KEY,
    TITLE VARCHAR2(50) NOT NULL,
    CONTENT VARCHAR2(2000)
);

CREATE SEQUENCE SEQ_NNO;

INSERT INTO NOTICE VALUES(SEQ_NNO.NEXTVAL, '제목1', '내용1');
INSERT INTO NOTICE VALUES(SEQ_NNO.NEXTVAL, '제목2', '내용2');
INSERT INTO NOTICE VALUES(SEQ_NNO.NEXTVAL, '제목3', '내용3');

COMMIT;

```
---
#### 2024-04-16(화)
- 예외처리

화면단에서부터 예외가 발생하지 않도록 예외처리하는 것 중요함

- 마이바티스 동적 쿼리

배열값을 넘겨받아 DELETE문 수행

```sql
<delete id="deleteNotice">
		DELETE FROM NOTICE
		<where>
			<foreach collection="array" item="item" open="NO IN(" separator="," close=")">
				#{item}
			</foreach>
		</where>
	</delete>
```

최종적으로 완성되는 SQL문은 DELETE FROM NOTICE WHERE NO IN( '11' , '10' ) 

collection 속성엔 파라미터값의 타입 작성

foreach 태그 안엔 item 속성의 value값 작성

### AOP (Aspect Oriented Programming)

비즈니스로직 메소드(핵심로직)마다 매번 공통적으로 처리해야되는 내용(로깅, 트랜잭션 처리)을 하나의 코드로만 정의해두고 내가 원하는 시점에 반복 적용시키는 프로그래밍 기법

AOP를 적용해두면 공통로직을 핵심로직에 적용시키고자 할 때 공통로직을 내가 원하는 시점에 편하게 적용할 수 있음

- AOP 관련 용어
    - JoinPoint
        
        AOP를 적용시킬 수 있는 메소드 전체 
        
    - PointCut
        
        AOP를 적용시키고자하는 메소드 ( ex : 그중에 로깅 또는 트랜잭션 처리가 필요한 메소드)
        
    - Advice
        
        AOP 동작 자체 (즉, 삽입하고자 하는 공통로직)
        
- AOP 동작 시점 5가지
    - @Before : PointCut 동작 이전
    - @After : PointCut 동작 이후
    - @Around : PointCut 동작 이전 / 이후
    - @AfterReturning : PointCut 정상 완료 이후
    - @AfterThrowing : PointCut 예외 발생 이후
    
- **AspectJ Weaver**

	AOP 관련 라이브러리
	
	https://mvnrepository.com/artifact/org.aspectj/aspectjweaver/1.9.22
---
#### 2024-04-17(수)
### 파일 업로드

- pom.xml에 파일 업로드 관련 라이브러리 세팅

https://mvnrepository.com/artifact/commons-fileupload/commons-fileupload/1.5

https://mvnrepository.com/artifact/commons-io/commons-io/2.11.0

- 초기 세팅
    1. 항상 HomeController, home.jsp 파일 삭제
    2. root-context.xml 파일에 db 연결정보, 트랜잭션 정보(복사)
    3. src/main/resources : config, mappers 폴더 만들기, config/mybatis-config.xml 파일 만들기(dtd 설정) + settings, typeAlias, mapper 등록, mappers/board-mapper.xml 파일 만들기(dtd 설정) + namespace 지정
    
    com.br.file.controller.MvcController : “/” 요청시 “main.jsp” 뜨드록 
    
    com.br.file.dto.BoardController : “/board” 요청시 다 처리되도록
    
    com.br.file.dto.BoardDto + 필드, set/getter, 생성자, toString
    
    com.br.file.dto.AttachDto + 필드, set/getter, 생성자, toString
    
    com.br.file.dao.BoardDao 
    
    com.br.file.service.BoardService<I>
    
    com.br.file.service.BoardServiceImpl
    
    MVC 각 클래스 빈 등록 및 서로 의존성 주입되도록
    
- DB 테이블 생성 (sbatis 계정)

```sql
CREATE TABLE BOARD
(
    BOARD_NO NUMBER PRIMARY KEY
  , BOARD_TITLE VARCHAR2(100) NOT NULL
  , BOARD_CONTENT VARCHAR2(2000)
);

CREATE TABLE ATTACHMENT
(
    FILE_NO NUMBER PRIMARY KEY
  , FILE_PATH VARCHAR2(300)
  , ORIGINAL_NAME VARCHAR2(300)
  , FILESYSTEM_NAME VARCHAR2(300)
  , REF_BOARD_NO NUMBER REFERENCES BOARD
);


CREATE SEQUENCE SEQ_BNO NOCACHE;
CREATE SEQUENCE SEQ_ANO NOCACHE;
```
---
#### 2024-04-18(목)
### 첨부파일 업로드

- jquery
    
    이벤트 function에 파라미터 작성할 때
    
    [파라미터.target](http://파라미터.target) :  현재 이벤트가 발생된 요소를 가리킴
    
    파라미터.target.files : 첨부된 파일을 가리킴
    

- 첨부파일 업로드 시 빈 등록해야함

- UUID.randomUUID() : 32자리의 랜덤값 + 하이픈(-) 4개 생성
---
#### 2024-04-19(금)
- DB 계정 생성 및 테이블 생성

```sql
CREATE USER SPRING IDENTIFIED BY SPRING;
GRANT RESOURCE, CONNECT TO SPRING;
```

```sql

-----------------삭제------------------
--접속유저의 모든테이블 및 제약조건 삭제
BEGIN
    FOR C IN (SELECT TABLE_NAME FROM USER_TABLES) LOOP
    EXECUTE IMMEDIATE ('DROP TABLE '||C.TABLE_NAME||' CASCADE CONSTRAINTS');
    END LOOP;
END;
/
--접속유저의 모든 시퀀스 삭제
BEGIN
FOR C IN (SELECT * FROM USER_SEQUENCES) LOOP
  EXECUTE IMMEDIATE 'DROP SEQUENCE '||C.SEQUENCE_NAME;
END LOOP;
END;
/
--접속유저의 모든 뷰 삭제
BEGIN
FOR C IN (SELECT * FROM USER_VIEWS) LOOP
  EXECUTE IMMEDIATE 'DROP VIEW '||C.VIEW_NAME;
END LOOP;
END;
/
---------------------------------------

--------------------------------------------------
--------------     MEMBER 관련	------------------	
--------------------------------------------------
CREATE TABLE MEMBER (
  USER_NO NUMBER PRIMARY KEY,
  USER_ID VARCHAR2(30) NOT NULL UNIQUE,
  USER_PWD VARCHAR2(100) NOT NULL,
  USER_NAME VARCHAR2(15) NOT NULL,
  EMAIL VARCHAR2(100),
  GENDER VARCHAR2(1) CHECK (GENDER IN('M', 'F')),
  PHONE VARCHAR2(13),
  ADDRESS VARCHAR2(100),
  PROFILE_URL VARCHAR2(400),
  SIGNUP_DATE DATE DEFAULT SYSDATE,
  MODIFY_DATE DATE DEFAULT SYSDATE,
  STATUS VARCHAR2(1) DEFAULT 'Y' CHECK(STATUS IN('Y', 'N', 'A'))
);

CREATE SEQUENCE SEQ_UNO NOCACHE;

COMMENT ON COLUMN MEMBER.USER_ID IS '회원아이디';
COMMENT ON COLUMN MEMBER.USER_PWD IS '회원비밀번호';
COMMENT ON COLUMN MEMBER.USER_NAME IS '회원이름';
COMMENT ON COLUMN MEMBER.EMAIL IS '회원이메일';
COMMENT ON COLUMN MEMBER.GENDER IS '회원성별';
COMMENT ON COLUMN MEMBER.PHONE IS '회원전화번호';
COMMENT ON COLUMN MEMBER.ADDRESS IS '회원주소';
COMMENT ON COLUMN MEMBER.PROFILE_URL IS '프로필이미지경로';
COMMENT ON COLUMN MEMBER.SIGNUP_DATE IS '회원가입날짜';
COMMENT ON COLUMN MEMBER.MODIFY_DATE IS '회원수정날짜';
COMMENT ON COLUMN MEMBER.STATUS IS '회원상태값';

INSERT INTO MEMBER 
VALUES (SEQ_UNO.NEXTVAL, 'admin01', '1234', '관리자', 'admin@br.or.kr', 'F', '010-1111-2222', '서울시 강남구 역삼동', NULL, '20220101', '20220101', 'A');

INSERT INTO MEMBER 
VALUES (SEQ_UNO.NEXTVAL, 'user01', 'pass01', '홍길동', 'user01@br.or.kr', 'M', '010-3333-4444', '서울시 양천구 목동', NULL, '20220201', '20220201', DEFAULT);

INSERT INTO MEMBER 
VALUES (SEQ_UNO.NEXTVAL, 'user02', 'pass02', '김말똥', 'user02@br.or.kr', 'F', '010-5555-6666', '서울시 강서구 마곡', NULL, '20220301', '20220301', DEFAULT);

----------------------------------------------------
----------------     NOTICE 관련        -----------------	
----------------------------------------------------

CREATE TABLE NOTICE (
  NOTICE_NO NUMBER PRIMARY KEY,
  NOTICE_TITLE VARCHAR2(30) NOT NULL,
  NOTICE_WRITER NUMBER NOT NULL,
  NOTICE_CONTENT VARCHAR2(200) NOT NULL,
  REGIST_DATE DATE DEFAULT SYSDATE,
  FOREIGN KEY (NOTICE_WRITER) REFERENCES MEMBER ON DELETE CASCADE
);

COMMENT ON COLUMN NOTICE.NOTICE_NO IS '공지사항번호';
COMMENT ON COLUMN NOTICE.NOTICE_TITLE IS '공지사항제목';
COMMENT ON COLUMN NOTICE.NOTICE_WRITER IS '공지사항작성자';
COMMENT ON COLUMN NOTICE.NOTICE_CONTENT IS '공지사항내용';
COMMENT ON COLUMN NOTICE.REGIST_DATE IS '공지사항작성날짜';

CREATE SEQUENCE SEQ_NNO NOCACHE;

INSERT INTO NOTICE VALUES (SEQ_NNO.NEXTVAL, '관리자 공지', 1,
                          '공지서비스를 게시합니다.  많이 이용해 주세요', 
                          '20220401');
                          
INSERT INTO NOTICE VALUES (SEQ_NNO.NEXTVAL, '공지서비스 오픈 환영', 1,
                          '드디어 오픈되었군요. 많이 이용하겠습니다.', 
                          '20220403');
                          
INSERT INTO NOTICE VALUES (SEQ_NNO.NEXTVAL, '공지서비스 이용 안내', 1,
                          '공지서비스는 회원만 이용할 수 있습니다. 회원 가입하세요.', 
                          '20220415');

COMMIT;

----------------------------------------------------
----------------     BOARD 관련        -----------------	
----------------------------------------------------

CREATE TABLE BOARD(
  BOARD_NO NUMBER PRIMARY KEY,
  BOARD_TITLE VARCHAR2(100) NOT NULL,
  BOARD_WRITER NUMBER NOT NULL,
  BOARD_CONTENT VARCHAR2(4000) NOT NULL,
  COUNT NUMBER DEFAULT 0,
  REGIST_DATE DATE DEFAULT SYSDATE,
  STATUS VARCHAR2(1) DEFAULT 'Y' CHECK (STATUS IN('Y', 'N')),
  FOREIGN KEY (BOARD_WRITER) REFERENCES MEMBER ON DELETE CASCADE
);

COMMENT ON COLUMN BOARD.BOARD_NO IS '게시글번호';
COMMENT ON COLUMN BOARD.BOARD_TITLE IS '게시글제목';
COMMENT ON COLUMN BOARD.BOARD_WRITER IS '게시글작성자';
COMMENT ON COLUMN BOARD.BOARD_CONTENT IS '게시글내용';
COMMENT ON COLUMN BOARD.COUNT IS '게시글조회수';
COMMENT ON COLUMN BOARD.REGIST_DATE IS '게시글올린날짜';
COMMENT ON COLUMN BOARD.STATUS IS '게시글상태값';

CREATE SEQUENCE SEQ_BNO NOCACHE;

INSERT INTO BOARD 
VALUES(SEQ_BNO.NEXTVAL, '관리자 게시글', 1, 
       '저희 사이트를 이용해 주셔서 감사합니다.',  
       DEFAULT, '20220503', DEFAULT);

INSERT INTO BOARD 
VALUES(SEQ_BNO.NEXTVAL, 'MVC Model2 패턴이란', 2, 
       '웹 애플리케이션 설계 방식 중 하나입니다.', 
       DEFAULT, '20220507', DEFAULT);

INSERT INTO BOARD 
VALUES(SEQ_BNO.NEXTVAL, '설계방식 2', 2, 
       '웹 애플리케이션 설계 방식 중 두번째 방식은 각 서블릿 구동 앞에 First Controller 를 두는 것입니다..', 
       DEFAULT, '20220603', DEFAULT);

INSERT INTO BOARD 
VALUES(SEQ_BNO.NEXTVAL, '설계방식3', 2, 
       '웹 애플리케이션 설계 방식 중 세번째 방식은 Front Controller 다음에 연결되는 컨트롤러들을 서블릿이 아닌 자바 클래스로 작성해서 연결하는 방식입니다.', 
       DEFAULT, '20220615', DEFAULT);

INSERT INTO BOARD 
VALUES(SEQ_BNO.NEXTVAL, 'MVC Model1 패턴', 3, 
       '웹 애플리케이션 설계 방식 중 JSP 파일이 뷰와 컨트롤러 두가지 다를 처리하는 방식입니다.', 
        DEFAULT, '20220618', DEFAULT);

INSERT INTO BOARD 
VALUES(SEQ_BNO.NEXTVAL, 'JSP란', 3, 'Java Server Page 를 말함', 
       DEFAULT, '20220621', DEFAULT);

INSERT INTO BOARD 
VALUES(SEQ_BNO.NEXTVAL, 'Servlet 이란', 1, '서버에서 구동되는 웹 규약이 적용된 Java EE 모듈이 제공하는 서비스 처리용 클래스임.', 
       DEFAULT, '20220701', DEFAULT);

INSERT INTO BOARD 
VALUES(SEQ_BNO.NEXTVAL, 'JSP Elements 들', 1, 
       'Derective(지시자) 태그, Decleation(선언) 태그, Scriptlet 태그, Expression 태그, Comment 태그가 있다.', 
       DEFAULT, '20220714', DEFAULT);

INSERT INTO BOARD 
VALUES(SEQ_BNO.NEXTVAL, 'HTML5', 3, 
       '새로운 웹 표준기술로 모든 디바이스 장치와 브라우저에서 동일하게 작동되는 웹 페이지를 만들기 위한 기술을 제공한다.', 
        DEFAULT, '20220723', DEFAULT);

INSERT INTO BOARD 
VALUES(SEQ_BNO.NEXTVAL, 'CSS3', 2, '웹 페이지를 꾸미기 위한 스타일시트로 HTML5 버전에 맞추어 속성들이 업그레이드 되었다.', 
       DEFAULT, '20220730', DEFAULT);

INSERT INTO BOARD 
VALUES(SEQ_BNO.NEXTVAL, 'JavaScript 란', 3, '웹 표준 2.0 에서 새로 추가된 강력한 API 들을 제공한다.', 
       DEFAULT, '20220803', DEFAULT);

INSERT INTO BOARD 
VALUES(SEQ_BNO.NEXTVAL, 'jQuery 란', 1, 
       '자바스크립트 오픈 소스 라이브러리의 하나로 html 요소들을 css 선택자를 이용하여 쉽게 선택할 수 있는 기능들을 제공한다..', 
       DEFAULT, '20220807', DEFAULT);

INSERT INTO BOARD 
VALUES(SEQ_BNO.NEXTVAL, 'ajax 란', 1, 'asynchronos javascript and xml 의 줄임말로 서버의 서블릿과 직접 통신하는 자바스크립트 기술이다.', 
       DEFAULT, '20220808', DEFAULT);
       
INSERT INTO BOARD 
VALUES(SEQ_BNO.NEXTVAL, '필터(Filter) 란', 3, '클라이언트 요청한 서비스가 서블릿으로 전달되기 전에 먼저 구동되는 클래스이다.', 
       DEFAULT, '20220809', DEFAULT);

INSERT INTO BOARD 
VALUES(SEQ_BNO.NEXTVAL, '래파(Wrapper) 란', 2, '필터가 나꿔챈 요청에 대한 데이터 처리를 담당하는 클래스이다.', 
       DEFAULT, '20220810', DEFAULT);

----------------------------------------------------
---------------     ATTACHMENT 관련         -------------------	
----------------------------------------------------

CREATE TABLE ATTACHMENT(
  FILE_NO NUMBER PRIMARY KEY,
  FILE_PATH VARCHAR2(100),
  FILESYSTEM_NAME VARCHAR2(200),
  ORIGINAL_NAME VARCHAR2(200),
  UPLOAD_DATE DATE DEFAULT SYSDATE,
  REF_TYPE CHAR(1) CHECK(REF_TYPE IN ('N', 'B')),
  REF_NO NUMBER 
);

CREATE SEQUENCE SEQ_ANO NOCACHE;

----------------------------------------------------
---------------     REPLY 관련         -------------------	
----------------------------------------------------

CREATE TABLE REPLY(
  REPLY_NO NUMBER PRIMARY KEY,
  REPLY_CONTENT VARCHAR2(400) NOT NULL,
  REF_BNO NUMBER NOT NULL,
  REPLY_WRITER NUMBER NOT NULL,
  REGIST_DATE DATE DEFAULT SYSDATE,
  STATUS VARCHAR2(1) DEFAULT 'Y' CHECK (STATUS IN ('Y', 'N')),
  FOREIGN KEY (REF_BNO) REFERENCES BOARD ON DELETE CASCADE, 
  FOREIGN KEY (REPLY_WRITER) REFERENCES MEMBER ON DELETE CASCADE 
);

COMMENT ON COLUMN REPLY.REPLY_NO IS '댓글번호';
COMMENT ON COLUMN REPLY.REPLY_CONTENT IS '댓글내용';
COMMENT ON COLUMN REPLY.REF_BNO IS '참조게시글번호';
COMMENT ON COLUMN REPLY.REPLY_WRITER IS '댓글작성자아이디';
COMMENT ON COLUMN REPLY.REGIST_DATE IS '댓글작성날짜';
COMMENT ON COLUMN REPLY.STATUS IS '댓글상태값';

CREATE SEQUENCE SEQ_RNO NOCACHE;

INSERT INTO REPLY
VALUES(SEQ_RNO.NEXTVAL, '첫번째 댓글입니다.', 1, 1, '20220813', DEFAULT);

INSERT INTO REPLY
VALUES(SEQ_RNO.NEXTVAL, '첫번째 댓글입니다.', 13, 2, '20220813', DEFAULT);

INSERT INTO REPLY
VALUES(SEQ_RNO.NEXTVAL, '두번째 댓글입니다.', 13, 3, '20220814', DEFAULT);

INSERT INTO REPLY
VALUES(SEQ_RNO.NEXTVAL, '마지막 댓글입니다.', 13, 1, '20220816', DEFAULT);

COMMIT;

```

- spring 관련 클래스는 S가 붙어있음 (빈으로 등록되어있음)

### jUnit4

- 관련 어노테이션
    
    @RunWith(SpringJUnit4ClassRunner.class) : JUnit4를 이용해서 테스트
    
    @ContextConfiguration : 사용할 객체가 빈으로 등록되어있는 문서의 경로 작성
    
    @FixMethodOrder(MethodSorters.NAME_ASCENDING) : 메소드의 이름 순으로 테스트를 수행
    

- 테스트 작성 패턴
    - given (준비) : 어떠한 데이터를 가지고 할건지
    - when (실행) : 어떤 메소드 실행시
    - then (검증) : 어떤 결과가 나와야되는지
        
        assertEquals(예상값, 실행값) : 실행값과 예상값이 일치하는지 확인해주는 메소드
        assertNotNull(실행값) : 실행값이 null이 아닌지를 확인해주는 메소드
        assertTrue(조건) : 해당 조건이 참인지 확인해주는 메소드
---
#### 2024-04-22(월)
- header, footer 만들기
- 로그인
- 로그아웃
- 회원가입
    - 아이디 중복체크 (정규표현식 사용)
    - 회원가입시 비밀번호 암호화 : 평문 → 암호문
        
        암호문을 DB에 기록
        
        암호화 : 평문을 암호문으로 바꾸는 것
        
        복호화 : 암호문을 평문으로 바꾸는 것
        
        양뱡향 암호화 : 암호화도 가능하고 복호화도 가능한 것
        
        단방향 암호화 : 암호화만 가능함 복호화 불가능
        
        솔팅 기법 (Spring Security에서 제공)
        
        ```
        1111 + 2345 
        1111 + 2321
        ```
        
        평문 뒤에 매번 다른 랜덤값이 붙음
        
        pom.xml에 스프링 시큐리티 라이브러리 추가
        
        https://mvnrepository.com/artifact/org.springframework.security/spring-security-core/5.7.5
        
        ```xml
        <dependency>
        	    <groupId>org.springframework.security</groupId>
        	    <artifactId>spring-security-core</artifactId>
        	    <version>5.7.5</version>
        		</dependency>
        		<dependency>
        	    <groupId>org.springframework.security</groupId>
        	    <artifactId>spring-security-web</artifactId>
        	    <version>5.7.5</version>
        		</dependency>
        		<dependency>
        	    <groupId>org.springframework.security</groupId>
        	    <artifactId>spring-security-config</artifactId>
        	    <version>5.7.5</version>
        		</dependency>
        ```
        
        암호화 전과 암호화 후의 비밀번호 비교
        
		![image](https://github.com/recordmystory/08_spring-workspace/assets/113417749/d6ac331c-7380-4dce-8226-7d94597ee5e3)
        
    - alert 커스텀
    
    [AlertifyJS](https://alertifyjs.com/alert.html)
    
    [AlertifyJS](https://alertifyjs.com/guide.html)
    
    [alertifyjs.zip](https://prod-files-secure.s3.us-west-2.amazonaws.com/0d5eb3b6-72a2-4651-99fa-511798047979/16fa2dce-ffbe-4c8c-9ff8-476d68b4580c/alertifyjs.zip)
    
   ![image](https://github.com/recordmystory/08_spring-workspace/assets/113417749/600b230e-7815-4826-99af-1de2d37eaf90)
    
    해당 폴더내에 필요한 파일 추가
    
    사용법
    
    ```jsx
    alertify.alert('${alertTitle}', '${alertMsg}');
    ```
