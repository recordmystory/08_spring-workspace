package com.br.spring.di;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("sss")
public class SpringBeanConfig { // @Configuration : 빈을 등록하는 클래스라고 알려주는 어노테이션 
	
	@Bean
	public Phone phone3() {
		return new Phone("갤럭시 s22", "삼성", 1200000, "2022-08-22");
	}
}
