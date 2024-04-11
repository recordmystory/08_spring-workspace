<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<meta charset="UTF-8">
<title>회원관리2</title>
</head>
<body>
	<h2>회원관리 두 번째 페이지</h2>
	
	<form id="member_form">
		번호 : <input type="number" name="userNo"><br>
		아이디 : <input type="text" name="userId"><br>
		비밀번호 : <input type="password" name="userPwd"><br><br>
		
		<!-- submit은 동기식 -->
		<input type="button" value="아이디와 비밀번호로 검색1" onclick="searchByIdPwd1();"> 
		<input type="button" value="아이디와 비밀번호로 검색2" onclick="searchByIdPwd2();">
		
		<input type="button" value="번호로 회원조회" onclick="searchByNo();">
		<input type="button" value="전체 회원조회" onclick="searchList();">
	</form>
	
	<br>
	<hr>
	
	<!-- ajax를 통해 result 영역 reload -->
	<div id="result">
		<h3>결과가 보여지는 영역</h3>
		
	</div>

	<script>
	// 아이디와 비밀번호로 검색1 클릭 시 실행될 function
		function searchByIdPwd1(){
			$("#result").empty(); // 해당 영역 초기화
			
			$.ajax({
					// 요청 관련
					url: "${contextPath}/member1/detail1.do",
					type: "get",
					data: "id=" + $("#member_form input[name='userId']").val() + "&pwd=" + $("#member_form input[name='userPwd']").val(),
					// 응답관련
					// dataType="", // dataType : 응답데이터 타입
					success: function(result){
						console.log(result);
						$("#result").text(result);
					},
					error: function(){
						console.log("아이디랑 비번으로 이름 검색1 ajax 통신 실패");
					}
			});
		};
		
		function searchByIdPwd2(){
			$("#result").empty(); // 해당 영역 초기화
			
			$.ajax({
					// 요청 관련
					url: "${contextPath}/member2/detail2.do",
					type: "get",
					data: $("#member_form").serialize(), // form의 모든 입력 요소들을 직렬화해주는 메소드(위의 쿼리스트링처럼 만들어짐), key값은 name 속성 값으로 설정됨, serialize : 직렬화
					// 응답관련
					// dataType="", // dataType : 응답데이터 타입
					success: function(result){
						console.log(result);
						$("#result").text(result);
					},
					error: function(){
						console.log("아이디랑 비번으로 이름 검색2 ajax 통신 실패");
					}
			});
		};
		
		function searchByNo(){
			$("#result").empty(); 
			
			$.ajax({
				url: "${contextPath}/member2/detail3.do",
				type: "get",
				data: {userNo: $("#member_form input[name='userNo']").val()},
				success: function(result){
					console.log(result);
					
					let value="<ul>";
					
					value += "<li>번호 : " + result.userNo + "</li>"
									+ "<li>아이디 : " + result.userId + "</li>"
									+ "<li>이름 : " + result.userName + "</li>"
									+ "</ul>";
									
				 $("#result").html(value);
								
				},
				error: function(){
					console.log("번호로 회원 검색 ajax 통신 실패");
				}
			});
		};
		
		function searchList(){
			$.ajax({
					url: '${contextPath}/member2/list.do',
					success: function(result){
						console.log(result);	
						
						let value = "<table border=1>" 
												+ "<tr>" 
												+ "<th>번호</th>"
												+ "<th>아이디</th>"
												+ "<th>이름</th>"
												+ "</tr>";
												
						for(let i=0; i<result.length; i++){
								value += "<tr>"
											+ "<td>"
											+ result[i].userNo
											+ "</td>"
											+ "<td>"
											+ result[i].userId
											+ "</td>"
											+ "<td>"
											+ result[i].userName
											+ "</td>"
											+ "</tr>";
						}
						value += "</table>";
						$("#result").html(value);
					},
					error: function(){
						console.log('회원 전체 조회 ajax 통신 실패');
					}
			});
		};
		
		$(function(){
				$.ajax({
					url: "${contextPath}/member2/map.do",
					success: function(map){
						console.log(map);						
					},
					error: function(){
						console.log("ajax 통신 실패");
					}
				});
		});
	</script>
</body>
</html>