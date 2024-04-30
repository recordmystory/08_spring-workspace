<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>채팅방</title>
<style>
      .chat{width:400px; margin:auto; padding:10px; border:1px solid lightgray;}
      .chat-area{height:500px; overflow: auto;}

       .chat-message{margin:10px 0px;}
       .chat-message.mine{display: flex; justify-content:flex-end;}
       
      .chat-message .send-message{
         padding: 5px 7px;
         border-radius: 10px;
         max-width: 190px;
         font-size:0.9em;
         white-space: pre-line;
      }
      .chat-message.other .send-message{background: lightgray;}
      .chat-message.mine .send-message{background: #FFE08C;}
   
      .chat-user {
         text-align:center; 
         border-radius:10px;   
         background: lightgray;
         opacity: 0.5;
         margin: 20px 0px;
         color: black;
         line-height: 30px;
      }
   </style>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>

   <div class="chat">
   
      <div class="chat-area">
      <!--  
         <div class="chat-message mine">
            <div class="send-message">내가보낸 메세지내가보낸 메세지내가보낸 메세지내가보낸 메세지내가보낸 메세지</div>
         </div>   
         
         <div class="chat-message other">
            <span class="send-user">상대방</span>
            <div class="send-message">남이보낸 메세지남이보낸 메세지남이보낸 메세지남이보낸 메세지남이보낸 메세지남이보낸 메세지</div>
         </div>
         
         <div class="chat-user entry">
            xxx님이 들어왔습니다.
         </div>
         
         <div class="chat-user exit">
            xxx님이 나갔습니다.
         </div>         
       -->
      </div>
      
      <div class="input-area">
      
         <div class="form-group">
             <textarea class="form-control" rows="3" id="message" style="resize:none"></textarea>
         </div>
         
         <button type="button" class="btn btn-sm btn-secondary btn-block" onclick="sendMessage();">전송하기</button>
         <button type="button" class="btn btn-sm btn-danger btn-block" onclick="onClose();">퇴장하기</button>
         
      </div>   
      
   </div>
   
   <!-- sock js 사용 -->
   <script src="https://cdn.jsdelivr.net/sockjs/1/sockjs.min.js"></script> 
   <script>
   	 // servlet-context.xml에 작성해둔 sock 객체와 연결됨
   	 const sock = new SockJS('${contextPath}/chat'); // 웹 소켓 서버와 연결(접속)되는 구문 : ChatEchoHandler에 재정의해둔 afterConnectionEstablished 메소드가 실행됨
   	 sock.onmessage = onMessage; 
   	 sock.onclose = onClose;
   	 
	   // 메시지를 출력시키는 영역의 요소
	   const $chatArea = $('.chat-area');
   	 
	   // 전송하기 버튼 클릭 시 실행되는 function
	   function sendMessage(){ // 사용자가 입력한 채팅 메세지를 웹소켓으로 전송
		   sock.send($('#message').val()); // * websocket으로 메시지 전달 (ChatEchoHandler의 handleMessage 메소드 실행)
	   	 $('#message').val('');
	   };
	   
	   // 나에게 메시지가 왔을 때 실행되는 function
	   function onMessage(evt){ // evt : 웹소켓에서 클라이언트에게 보내준 데이터
		   console.log('evt', evt);
		   console.log('evt data', evt.data);	// new TextMessage 객체로 보내준 텍스트메시지 값
		   
		   let msgArr = evt.data.split('|'); // msgArr : ['메시지유형(chat/entry/exit)', '메시지내용', '발신자아이디']가 담겨있는 배열
		   
		   let $chatDiv = $('<div>'); // 채팅창에 append 시킬 div 요소 제작 (각 조건에 따라 다르게)
		   if(msgArr[0] == 'chat'){ // 채팅 메시지일 경우
			   /*
					   	<div class="chat-message mine">
			            <div class="send-message">내가보낸 메세지내가보낸 메세지내가보낸 메세지내가보낸 메세지내가보낸 메세지</div>
			         </div>   
			         
			         <div class="chat-message other">
			            <span class="send-user">상대방</span>
			            <div class="send-message">남이보낸 메세지남이보낸 메세지남이보낸 메세지남이보낸 메세지남이보낸 메세지남이보낸 메세지</div>
			         </div>
	         */
	         
	         // 내가 쓴 메시지면 mine class 추가, 남이 쓴 메시지면 other class 추가
	         $chatDiv.addClass('chat-message').addClass(msgArr[2] == '${loginUser.userId}' ? 'mine' : 'other').append($('<div>').addClass('send-message').text(msgArr[1]));
	         
	         // 상대방이 보낸 메시지일 경우 따로 진행해야되는 작업
	         $chatDiv.hasClass('other') && $chatDiv.prepend($('<span>').addClass('send-user').text(msgArr[2]));
	         
	         $chatArea.append($chatDiv);
	         $chatArea.scrollTop($chatArea[0].scrollHeight); // 스크롤 항상 하단으로 유지시켜주는 내용
	         
		   } else { // 입장 또는 퇴장과 관련한 메시지
			   /*
			   		<div class="chat-user entry">
	            xxx님이 들어왔습니다.
	         </div>
	         
	         <div class="chat-user exit">
	            xxx님이 나갔습니다.
	         </div>        
			   */
			   
			   $chatDiv.addClass('chat-user').addClass(msgArr[0]).text(msgArr[1]);
			   
			   $chatArea.append($chatDiv);
		     $chatArea.scrollTop($chatArea[0].scrollHeight); // 스크롤 항상 하단으로 유지시켜주는 내용
		   }
	   };
	   
	   // 퇴장하기 클릭 시 실행되는 function : 웹 소켓 통신 끊김
	   function onClose(){
		   location.href = '${contextPath}';
	   };
   </script>
   
   <jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>