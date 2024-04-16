<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 등록</title>
</head>
<body>
	<form action="${contextPath}/notice/insert.do" method="post">
	
		제목 : <input type="text" name="title" required><br>
		내용 : <textarea name="content"></textarea><br><br>
		
		<input type="submit" value="등록">
	</form>
	
</body>
</html>