<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>도서 등록</title>
</head>
<body>
	<form action="${contextPath}/book/enroll.do" method="post">
		제목 : <input type="text" name="title"><br>
		저자 : <input type="text" name="author"><br>
			<button type="submit">등록</button>
	</form>
</body>
</html>