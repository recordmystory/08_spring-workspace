<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>list</title>
</head>
<body>
		<!-- /WEB-INF/views/book/list.jsp -->
		<table border="1">
			<thead>
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>저자</th>
				</tr>
			</thead>
			
			<tbody>
				<tr onclick="location.href='${contextPath}/book/detail.do'">
					<td>1</td>
					<td>수학의정석</td>
					<td>김두두</td>
				</tr>
				<tr>
					<td>2</td>
					<td>자바의정석</td>
					<td>최디디</td>
				</tr>
				<tr>
					<td>3</td>
					<td>스프링의정석</td>
					<td>조다다</td>
				</tr>
			</tbody>
			
		</table>
</body>
</html>