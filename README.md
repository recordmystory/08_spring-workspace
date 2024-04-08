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
