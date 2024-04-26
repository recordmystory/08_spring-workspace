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
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	
	<form action="${contextPath}/notice/regist.do" method="post" enctype="multipart/form-data">
	 	제목 : <input type="text" name="noticeTitle"><br>
	 	내용 : <input type="text" name="noticeContent"><br>
	 	파일 : <input type="file" id="upfile" class="form-control-file border file" name="uploadFiles" multiple><br><br>
	 	<button type="submit">등록</button>
	</form>
</body>
</html>