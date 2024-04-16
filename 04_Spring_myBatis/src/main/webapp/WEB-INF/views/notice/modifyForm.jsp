<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 수정</title>
</head>
<body>
	<form action="${contextPath}/notice/update.do" method="post">
		<input type="hidden" name="no" value="${n.no}">
		제목 : <input type="text" name="title" value="${n.title}" required><br>
		내용 : <textarea name="content">${n.content}</textarea><br><br>
		
		<input type="submit" value="수정">
	</form>
	
	
	<!-- controller 메소드 작성 서비스 호출해서 성공일 경우 상세페이지 요청, 실패일 경우 메인페이지 요청 -->
</body>
</html>