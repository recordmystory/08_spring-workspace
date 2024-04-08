<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>modify</title>
</head>
<body>
	<form action="" method="post">
		제목 : <input type="text" value="수학의 정석"><br>
		저자 : <input type="text" value="김수학"><br>
		
		<button>수정</button>
	</form>
</body>
</html>