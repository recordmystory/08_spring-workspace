package com.br.spring.ioc.anno;

import javax.swing.Spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class SpringMain {
	public static void main(String[] args) {
		
		// 컨테이너 생성 (빈등록구문이 쓰여져있는 파일을 읽어들이면서)
		AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(SpringBeanConfig.class);
		
		Music m1 = (Music) ctx.getBean("music1");
		Singer s1 = (Singer) ctx.getBean("singer1", Singer.class);
		
		System.out.println(m1);
		System.out.println(s1);
		
		System.out.println(ctx.getBean("music2", Music.class));
		System.out.println(ctx.getBean("singer2", Singer.class));
	}
}
