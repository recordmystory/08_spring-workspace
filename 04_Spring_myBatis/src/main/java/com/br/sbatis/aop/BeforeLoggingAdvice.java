package com.br.sbatis.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
// import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import lombok.extern.slf4j.Slf4j;

@Slf4j
// @Aspect // Advice 등록시켜주는 어노테이션
public class BeforeLoggingAdvice {
	// 모든 Controller 클래스의 모든 메소드 실행 전에 로그 출력될 수 있도록 advice 등록
	
	/* 포인트컷 표현식 : execution (반환타입 패키지.클래스명.메소드(매개변수))
	* 
	* 반환 타입
	* - * : 모든 반환타입
	* - void : void
	* - !void : void가 아닌 반환타입
	* 
	* * 매개변수
	* - * : 1개의 모든 매개변수
	* - .. : 모든 매개변수
	*/
	@Pointcut("execution (* com.br.sbatis.controller.*Controller.*(..))") // 포인트컷 표현식
	public void setPointCut() {} // 해당 PointCut에 이름 지정
	
	/*
	 * Before Advice 메소드 작성
	 * - 반환타입 : void
	 * - 메소드명 : 맘대로
	 * - 매개변수 : JoinPoint 타입 객체 (생략 가능)
	 */
	@Before("setPointCut()")
	public void beforeLogAdvice(JoinPoint joinpoint) {
		// log.info("************** beforeLogAdvice 실행 **************");
		
		// JoinPoint : 실행되는 핵심로직의 메소드에 대한 정보(요청시 전달값, 메소드명, 반환형, ...)가 필요할 때 사용
		
		// 핵심로직의 메소드에 대한 정보 (클래스, 메소드, 반환형, ...)
		Signature signature = joinpoint.getSignature();
		log.info("method: {}", signature.getName()); // method: mainPage
		log.info("short string : {}", signature.toShortString()); // MvcController.mainPage()
		log.info("long string : {}", signature.toLongString()); // public java.lang.String com.br.sbatis.controller.MvcController.mainPage()
		
		Object[] args = joinpoint.getArgs(); // 핵심로직의 메소드로 전닳된 데이터 (요청시 전달된 데이터)
		
		for(Object obj : args) {
			log.info("arg : {}", obj);
		}
		
	} 
}
