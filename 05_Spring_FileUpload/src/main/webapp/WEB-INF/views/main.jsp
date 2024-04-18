<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="${contextPath}/resources/js/jquery-3.7.1.min.js"></script>
<title>메인페이지</title>
<style>
	.guide{
		font-size: 10px;
		color: gray;
	}
</style>
</head>
<body>

	<script>
		$(document).ready(function(){
			let totalSize = 0;
				$('.file').change(function(evt){
					/* 
					console.log('input 요소에 change 이벤트 발생');
					console.log('현재 이벤트가 발생된 요소', evt.target); 
					console.log('현재 선택된 파일', evt.target.files); 
					*/
					
					const files = evt.target.files; // FileList
					
					for(let i=0; i<files.length; i++){
						if(files[i].size > 10 * 1024 * 1024){ // 첨부파일 한 개의 크기가 10MB를 초과했을 경우
							alert("첨부파일의 최대 크기는 10MB까지입니다.");
							evt.target.value = '';
							return;
						}
						totalSize += files[i].size;
						if(totalSize > 100 * 1024 * 1024){ // 누적된 첨부파일의 전체 크기가 100MB를 초과했을 경우
							alert("전체 첨부파일의 최대 크기는 100MB까지입니다.");
							evt.target.value = '';
							return;
						}
					}
				});
		});
	</script>
	
	<h2>메인페이지</h2>
	
	<h1>1. 한 개의 첨부파일 업로드 테스트</h1>
	<form action="${contextPath}/board/insert1.do" method="post" enctype="multipart/form-data">
		게시글 제목 : <input type="text" name="boardTitle"><br>
		게시글 내용 : <textarea name="boardContent"></textarea><br>
		첨부파일 : <input type="file" accept="image/*" class="file" name="uploadFile"><br><br>
		<label class="guide">첨부파일 사이즈는 10MB 이하여야 합니다.</label><br><br>
		
		<input type="submit" value="등록">
	</form>
	
	<h2>2. 다중 첨부파일 업로드 테스트</h2>
	<form action="${contextPath}/board/insert2.do" method="post" enctype="multipart/form-data">
		게시글 제목 : <input type="text" name="boardTitle"><br>
		게시글 내용 : <textarea name="boardContent"></textarea><br>
		첨부파일 : <input type="file" multiple class="file" name="uploadFiles"><br><br>
		<label class="guide">각 첨부파일 사이즈는 10MB 이하, 총 100MB 이하여야 합니다.</label><br><br>
		
		<input type="submit" value="등록">
	</form>
	
	<h2>3. 비동기식 첨부파일 업로드 테스트</h2>
	<div id="async_test">
		게시글 제목 : <input type="text"><br>
		게시글 내용 : <textarea></textarea><br>
		첨부파일 : <input type="file" class="file"><br><br>
		<label class="guide">첨부파일 사이즈는 10MB 이하여야 합니다.</label><br><br>
		
		<input type="submit" value="등록">
	</div>
	
	<h2>4. 첨부파일 목록 조회</h2>
</body>
</html>