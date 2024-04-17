package com.br.sbatis.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;

import lombok.extern.slf4j.Slf4j;

@Slf4j
//@Aspect
public class AfterLoggingAdvice {
	
	@After("execution (* com.br.sbatis.controller.*Controller.*(..))")
	public void afterLogAdvice() {
		log.info("******** afterLog 출력 ********");
	}
}
