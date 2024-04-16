package com.br.sbatis.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect // Advice 등록시켜주는 어노테이션
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
	public void beforeLogAdvice() {
		log.info("************** beforeLogAdvice 실행 **************");
	} 
}
