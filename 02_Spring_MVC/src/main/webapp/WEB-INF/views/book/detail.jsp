<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>detail</title>
</head>
<body>
	제목 : 수학의 정석<br>
	저자 : 김수학<br><br>
	
	<button onclick="location.href='${contextPath}/book/modifyForm.do'">수정하기 페이지로 이동</button>
</body>
</html>