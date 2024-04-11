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
