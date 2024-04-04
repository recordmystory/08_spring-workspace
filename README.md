# 08_spring-workspace
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

- 라이브러리 다운된 곳

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/0d5eb3b6-72a2-4651-99fa-511798047979/6e39a77d-96ee-4ef9-acad-74ca6288869a/Untitled.png)

- pom.xml 수정 후 해야될 것

항상 pom.xml에서 자바, 스프링 버전 수정하기

프로젝트 우클릭 → Maven → Update Project → OK

위 과정을 해야 프로젝트에 반영됨
