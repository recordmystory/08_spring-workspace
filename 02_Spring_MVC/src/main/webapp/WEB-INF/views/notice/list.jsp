<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<meta charset="UTF-8">
<title>공지사항 목록</title>
<style>
	tr:hover{
		cursor: pointer;
	}
</style>
</head>
<body>
	<table border="2">
		<thead>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>내용</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${empty list}">
					<tr>
						<td colspan="3">조회된 공지사항이 없습니다.</td>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach var="n" items="${list}">
						<tr><!--  onclick="location.href='${contextPath}/notice/detail.do?no=${n.no}'" -->
							<td class="notice_no">${n.no}</td>
							<td>${n.title}</td>
							<td>${n.content}</td>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>
	
	<script>
		$(document).ready(function(){
			$("tbody tr").on("click", function(){
				location.href = "${contextPath}/notice/detail.do?no=" + $(this).children(".notice_no").text();
			});
		});
	</script>
</body>
</html>