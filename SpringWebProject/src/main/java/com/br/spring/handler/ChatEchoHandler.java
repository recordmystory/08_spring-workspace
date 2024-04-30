package com.br.spring.handler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.br.spring.dto.MemberDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ChatEchoHandler extends TextWebSocketHandler {
	
	// sessionList : 현재 웹소켓과 연결된 클라이언트들을 저장해놓는 리스트
	private List<WebSocketSession> sessionList = new ArrayList<>();
	// private final ChatService chatService;
	
	/**
	 *	1) aterConnectionEstablished : 클라이언트와 연결되었을 때 처리할 내용을 정의
	 *	
	 *	@param session : 현재 웹 소켓과 연결된 클라이언트 정보를 가지고 있는 객체
	 */
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		log.debug("****************************** websocket 연결됨 ******************************");
		log.debug("websocketsession 객체 : {}", session);
		log.debug("session Id : {}", session.getId());
		log.debug("session attributes : {}", session.getAttributes());
		log.debug("현재 채팅방에 참가한 로그인한 회원 : {}", session.getAttributes().get("loginUser"));
		
		sessionList.add(session);
		
		// 모든 클라이언트들에게 입장 메시지 전달
		for(WebSocketSession client : sessionList) {
			
			// 전달하고자 하는 메시지의 형식 : 메시지유형(chat/entry/exit)|메시지내용|발신자아이디|...(프로필 이미지 등등을 덧붙여도됨)
			String msg = "entry|" + ((MemberDto) session.getAttributes().get("loginUser")).getUserId() + "님이 입장하셨습니다.";
			
			// 클라이언트에게 메시지 발송
			// 해당 코드가 실행될 때 채팅방 jsp의 onMessage function 실행됨
			client.sendMessage(new TextMessage(msg));
		}
	}
	
	/**
	 *	2) handleMessage : 데이터(텍스트, 파일 등)가 웹소켓으로 전송되었을 때 처리할 내용을 정의
	 *
	 *	@param message : 현재 웹소켓으로 전달된 데이터에 대한 정보를 가지고 있는 객체
	 */
	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		log.debug("\n===================================== 메시지 들어옴 =====================================");
		log.debug("현재 웹소켓으로 메시지를 보낸 회원 정보 : {}" , session.getAttributes().get("loginUser"));
		log.debug("현재 웹소켓으로 전달된 메시지의 내용 : {}", message.getPayload());
		
		// 현재 해당 웹소켓에 연결되어있는 모든 클라이언트들에게 현재 들어온 메시지를 재발송함 (작성자 본인 포함)
		for(WebSocketSession client : sessionList) {
			
			// 전달하고자 하는 메시지의 형식 : 메시지유형(chat/entry/exit)|메시지내용|발신자아이디|...(프로필 이미지 등등을 덧붙여도됨)
			String msg = "chat|" + message.getPayload() + "|" + ((MemberDto) session.getAttributes().get("loginUser")).getUserId();
			
			// 클라이언트에게 메시지 발송
			// 해당 코드가 실행될 때 채팅방 jsp의 onMessage function 실행됨
			client.sendMessage(new TextMessage(msg));
			
			// DB에 채팅메시지 내역을 남기고자할 경우
			// EchoHandler에서 service 연결해서 insert 요청하기
		}
	}
	
	/**
	 *	3) afterConnectionClosed : 클라이언트와 연결이 끊겼을 때 처리할 내용을 정의
	 */
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		log.debug("\n****************************** websocket 연결 끊김 ******************************");
		log.debug("WebSocketSession 객체 : {}", session);
		log.debug("session Id : {}", session.getId());
		log.debug("현재 채팅방에서 나간 회원 : {}", session.getAttributes().get("loginUser"));
	
		sessionList.remove(session);
		
		// 모든 클라이언트들에게 입장 메시지 전달
		for(WebSocketSession client : sessionList) {
			
			// 전달하고자 하는 메시지의 형식 : 메시지유형(chat/entry/exit)|메시지내용|발신자아이디|...(프로필 이미지 등등을 덧붙여도됨)
			String msg = "exit|" + ((MemberDto) session.getAttributes().get("loginUser")).getUserId() + "님이 퇴장하셨습니다.";
			
			// 클라이언트에게 메시지 발송
			// 해당 코드가 실행될 때 채팅방 jsp의 onMessage function 실행됨
			client.sendMessage(new TextMessage(msg));
		}
	}
	
}
