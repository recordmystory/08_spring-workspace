<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 목록</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	
	<c:if test="${loginUser.userId == 'admin01'}">
		<a href="${contextPath}/notice/registForm.page">글쓰기</a><br>
	</c:if>
	<table border="1">
		<thead>
			<tr>
				<th>글번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
			</tr>
		</thead>
		<tbody>
		<c:choose>
		<c:when test="${empty list}">
			<tr>
				<td colspan="6"> 조회된 게시글이 없습니다.</td>
			</tr>
		</c:when>
		<c:otherwise>
			<c:forEach var="n" items="${list}">
				<tr onclick="location.href='${contextPath}/notice/detail.do?no=${n.noticeNo}'">
				<td>${n.noticeNo}</td>			
				<td>${n.noticeTitle}</td>			
				<td>${n.noticeWriter}</td>			
				<td>${n.registDate}</td>			
			</c:forEach>
		</c:otherwise>
		</c:choose>
		</tbody>
	</table>
	<br>
	
	<div id="pagingArea">
                <ul class="pagination">
                    <li class="page-item ${pi.currentPage == 1 ? 'disabled' : ''}"><a class="page-link" href="${contextPath}/notice/list.do?page=${pi.currentPage - 1}">Previous</a></li>
                    
                    <c:forEach var="p" begin="${pi.startPage}" end="${pi.endPage}">
                    	<li class="page-item ${pi.currentPage == p ? 'disabled' : ''}"><a class="page-link" href="${contextPath}/notice/list.do?page=${p}">${p}</a></li>
                    </c:forEach>
                    
                    <li class="page-item ${pi.currentPage == pi.maxPage ? 'disabled' : ''}"><a class="page-link" href="${contextPath}/notice/list.do?page=${pi.currentPage + 1}">Next</a></li>
                </ul>
            </div>
</body>
</html>