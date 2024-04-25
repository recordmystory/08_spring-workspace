<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 상세</title>
<style>
	table *{margin:5px;}
  table{width:100%;}
</style>
</head>
<body>
	<!-- 이쪽에 메뉴바 포함 할꺼임 -->
    <jsp:include page="/WEB-INF/views/common/header.jsp"/>

    <div class="content">
        <br><br>
        <div class="innerOuter">
            <h2>게시글 상세보기</h2>
            <br>
            
            <a class="btn btn-secondary" style="float:right" href="${contextPath}/board/list.do">목록으로</a>
            <br><br>
            <table id="contentArea" align="center" class="table">
                <tr>
                    <th width="100">제목</th>
                    <td colspan="3">${board.boardTitle}</td>
                </tr>
                <tr>
                    <th>작성자</th>
                    <td>${board.boardWriter}</td>
                    <th>작성일</th>
                    <td>${board.registDate}</td>
                </tr>
                <tr>
                    <th>첨부파일</th>
                    <td colspan="3">
	                    <c:forEach var="at" items="${board.attachList}">
	                        <a href="${contextPath}${at.filePath}/${at.filesystemName}" download="${at.originalName}">${at.originalName}</a><br>
	                    </c:forEach>
                    </td>
                </tr>
                <tr>
                    <th>내용</th>
                    <td colspan="3"></td>
                </tr>
                <tr>
                    <td colspan="4"><p style="height:150px">${board.boardContent}</p></td>
                </tr>
            </table>
            <br>
						
						<c:if test="${loginUser.userId eq board.boardWriter}">
	            <div align="center">
	                <!-- 수정하기, 삭제하기 버튼은 이글이 본인글일 경우만 보여져야됨 -->
	                <a class="btn btn-primary" href="">수정하기</a>
	                <a class="btn btn-danger" href="">삭제하기</a>
	            </div><br><br>
						</c:if>
						
            <!-- 댓글 기능은 나중에 ajax 배우고 접목시킬예정! 우선은 화면구현만 해놓음 -->
            <table id="replyArea" class="table" align="center">
                <thead>
                	<c:choose>
                		<c:when test="${empty loginUser}">
	                    <tr>
	                        <th colspan="2">
	                            <textarea class="form-control" name="replyContent" id="replyContent" cols="55" rows="2" style="resize:none; width:100%" readonly>로그인 후 이용 가능한 서비스입니다.</textarea>
	                        </th>
	                        <th style="vertical-align: middle"><button class="btn btn-secondary disabled">등록하기</button></th>
	                    </tr>
                    </c:when>
                    <c:otherwise>
                    	<tr>
	                        <th colspan="2">
	                            <textarea class="form-control" name="replyContent" id="replyContent" cols="55" rows="2" style="resize:none; width:100%"></textarea>
	                        </th>
	                        <th style="vertical-align: middle"><button class="btn btn-secondary" onclick="ajaxInsertReply();">등록하기</button></th>
	                    </tr>
                    </c:otherwise>
                  </c:choose>
                    <tr>
                    <!-- 댓글 개수와 댓글 뿌려주기 -->
                       <td colspan="3">댓글 (<span id="rcount">0</span>) </td> 
                    </tr>
                </thead>
                <tbody>
                    <!-- 
	                    <tr>
	                        <th>user02</th>
	                        <td>댓글입니다.너무웃기다앙</td>
	                        <td>2020-04-10</td>
	                    </tr> 
                    -->
                </tbody>
            </table>
        </div>
        
        <script>
        	$(document).ready(function(){
        		ajaxReplyList();
        		
        		// 동적으로 만들어진 요소에 이벤트 걸 때 : 이벤트 메소드 방식 실행 안됨
        		// $(상위요소).on('', '', function(){})
        		$(document).on('click', '.removeReply', function(){ // #replyArea에 removeReply가 있을 경우 실행됨
        			console.log('삭제할 댓글번호 : ' + $(this).data('replyno'));
        			
        			// 해당 댓글 삭제용 ajax 요청
        			$.ajax({
        				url: '${contextPath}/board/removeReply.do',
        				data: {replyNo: $(this).data('replyno')},
        				type: 'get',
        				success: function(result){
        					if(result == 'SUCCESS'){
        						ajaxReplyList();
        						alertify.alert('댓글 삭제 서비스', '댓글 삭제가 완료되었습니다.');
        					} else if(result == 'FAIL'){
        						alertify.alert('댓글 삭제 서비스', '댓글 삭제에 실패했습니다.');        						
        					}
        				},
        				error: function(){
        					
        				}
        			})
        			
        		});
        	});
        	
        	// 댓글 ajax로 작성 요청하는 function
        	function ajaxInsertReply(){
        		if($('#replyContent').val().trim().length !=0){
        			$.ajax({
        				url: '${contextPath}/board/registReply.do',
        				type: 'post',
        				data: { replyContent: $('#replyContent').val(), refBoardNo: ${board.boardNo} },
        				success: function(result){
        					if(result == 'SUCCESS'){
        						$('#replyContent').val('');
        						ajaxReplyList();
        					} else if(result == 'FAIL'){
        						alertify.alert('댓글 작성 서비스', '다시 입력해주세요');
        					}
        				},
        				error: function(){
        					console.log('댓글 작성 서비스 ajax 통신 실패');
        				}
        			});
        		} else {
        			alertify.alert('댓글 작성 서비스', '다시 입력해주세요.');
        		}
        	}; 
        	
        	// 현재 게시글의 댓글 리스트를 ajax로 조회해서 뿌리는 function
        	function ajaxReplyList(){
        		// 비동기식 "/board/replyList.do" url 요청
        		// 요청처리 결과로 조회된 댓글 리스트를 응답데이터로 받기
        		// 해당 응답데이터로 댓글 한 개당 하나의 tr 요소로 만들어 tbody 영역에 뿌리기
        		// +) 댓글 개수 수정
        		$.ajax({
        			url: '${contextPath}/board/replyList.do',
        			data: "boardNo=${board.boardNo}",
        			type: 'get',
        			success: function(result){
        				console.log('ajax 통신 성공');
        				console.log(result);
        				
        				$('#rcount').text(${result.length});
        				
        				let row = '';
        				for(let i=0; i<result.length; i++){
        					
        					row += '<tr>' 
        							+ '<th>' + result[i].replyWriter + '</th>'
        							+ '<td>' + result[i].replyContent + '</td>'
        							+ '<td>' + result[i].registDate;
        					
        					// 현재 로그인한 회원이 해당 댓글의 작성자일 경우
        				  if(result[i].replyWriter == '${loginUser.userId}'){
        							row += '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button class="btn btn-sm btn-danger removeReply" data-replyno="' + result[i].replyNo + '">삭제</button>';        					  
        				  }			
        					
        					row += '</td>'
					        			+ '</tr>';    
        				}
        					
        					$('#replyArea tbody').html(row);        					
        			},
        			error: function(){
        				console.log('댓글 조회 ajax 통신 실패');
        			}
        		});
        	};
        </script>
        <br><br>
    </div>

    <!-- 이쪽에 푸터바 포함할꺼임 -->
    <jsp:include page="/WEB-INF/views/common/footer.jsp"/>
    
</body>
</html>