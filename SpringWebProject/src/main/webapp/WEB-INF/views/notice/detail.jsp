<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 상세</title>
</head>
<body>
	제목 : ${notice.noticeTitle}<br>
	작성자 : ${notice.noticeWriter}<br> 
	내용 : ${notice.noticeContent}<br>
	작성일 : ${notice.registDate}<br>
	첨부파일 : 
	<c:forEach var="at" items="${notice.attachList}">
      <a href="${contextPath}${at.filePath}/${at.filesystemName}" download="${at.originalName}">${at.originalName}</a><br>
  </c:forEach>
</body>
</html>