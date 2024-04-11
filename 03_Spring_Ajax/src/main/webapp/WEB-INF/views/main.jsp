<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>main</title>
</head>
<body>
	
	<!-- /WEB-INF/views/member/manage1.jsp 포워딩 -->
	<a href="${contextPath}/member/manage1.do">회원관리1</a><br>
	
	<!-- /WEB-INF/views/member/manage2.jsp 포워딩 -->
	<a href="${contextPath}/member/manage2.do">회원관리2</a>

</body>
</html>