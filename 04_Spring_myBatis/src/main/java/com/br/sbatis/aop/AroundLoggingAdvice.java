package com.br.sbatis.aop;

import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import lombok.extern.slf4j.Slf4j;

@Slf4j
// @Aspect
public class AroundLoggingAdvice {
	/*
	 * Around Advice 메소드 작성법 
	 * - 반환타입 : Object 
	 * - 메소드명 : 아무렇게나 
	 * - 매개변수 : ProceedingJoinPoint 타입
	 */

	@Around("execution (* com.br.sbatis.controller.*Controller.*(..))")
	public Object aroundLogAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

		// pointcut 이전에 실행시킬 코드

		// HttpServletRequest 얻어내기
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = servletRequestAttributes.getRequest();
		
		log.info("{}", "┬".repeat(50));
		log.info("{} | {}", request.getMethod(), request.getRequestURI());
		
		Map<String, String[]> map = request.getParameterMap(); // ("no" = {"1"}, "name" = {"홍길동", "김말똥"})
		
		if(map.isEmpty()) {
			log.info("{}", "No Parameter");
		} else {
			Set<Entry<String, String[]>> set = map.entrySet(); // map => set
			for(Entry<String, String[]> entry : set) {
				log.info("{}: {}", entry.getKey(), Arrays.toString(entry.getValue()));
			}
		}
		
		Object obj = proceedingJoinPoint.proceed(); // pointcut 메소드 호출

		log.info("{}", "┴".repeat(50));
		
		// pointcut 이후에 실행시킬 코드
		return obj;

	}
}
