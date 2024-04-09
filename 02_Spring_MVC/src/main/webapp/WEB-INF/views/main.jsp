<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>main</title>
<link href="${contextPath}/resources/css/sample.css" rel="stylesheet">
<script src="${contextPath}/assets/js/sample.js"></script>
</head>
<body>
	<!-- 
						url로 localhost:8888/mvc/
						localhost:88888/mvc/main.do
	 					요청시 /WEB-INF/views/main.jsp가 보여지도록 설정 	 					
	 -->
	 					
	<h1>메인페이지</h1>
	
	<h3 onclick="resourceTest();">1. 정적인 자원 확인</h3>
	
	<img src="${contextPath}/resources/images/dolphin.jpg" width="200">
	<img src="${contextPath}/assets/images/plant.jpg" width="200">
	
	<hr>
	
	<h3>2. 응답페이지 보여지게 하는 연습(포워딩 방식과 redirect 방식)</h3>
	
	<!-- 
			member/mypage.do
			notice/list.do
	 -->
	<a href="${contextPath}/book/list.do">도서목록 페이지로 이동</a><br>
	<a href="${contextPath}/book/enrollForm.do">도서등록 페이지로 이동</a>
	
	<h3>3. 요청시 전달되는 값(파라미터) 받기 연습</h3>
	
	<!-- HttpServletRequest 방법 -->
	<a href="${contextPath}/member/detail.do?no=1">회원상세조회</a>
	<br><br>
	
	<form action="${contextPath}/member/enroll1.do" method="post">
		이름 : <input type="text" name="name"><br>
		나이 : <input type="text" name="age"><br>
		주소 : <input type="text" name="address"><br><br>
		<button type="submit">제출</button>
	</form><br><br>

	<hr>
	<!-- @RequestParam 이용하는 방법 -->
	<form action="${contextPath}/member/enroll2.do" method="post">
		이름 : <input type="text" name="name"><br>
		나이 : <input type="text" name="age"><br>
		주소 : <input type="text" name="address"><br><br>
		<button type="submit">제출</button>
	</form><br><br>
</body>
</html>