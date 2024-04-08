package com.br.spring.ioc.anno;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.br.spring.di.Animal;

/*
 * * @Configuration 
 * - Spring Bean Configuration File의 역할을 대신해주는 java 클래스에게 부여하는 어노테이션
 */
@Configuration
public class SpringBeanConfig {
	/*
	 * @Bean - Java에서 Bean을 등록할 때 사용하는 어노테이션 - @Configuration 클래스 내에서 메소드 하나당 빈 하나를
	 * 생성
	 * 
	 * 메소드명 : 빈 이름 (id) 반환형 : 빈 타입(class)
	 * 
	 * <bean class="Music" id="music1"> <property name="xx" value="xx"/> <property
	 * name="xx" value="xx"/> <property name="xx" value="xx"/> </bean>
	 * 
	 */

	@Bean
	public Music music1() {
		Music m = new Music();
		m.setTitle("Love wins all");
		m.setGenre("발라드");

		return m;
	}

	@Bean
	public Singer singer1() {
		Singer s = new Singer();
		s.setName("아이유"); // <property name="name" value="아이유"/>
		s.setMusic(music1()); // <property name="music" ref="music1" />

		return s;
	}

	@Bean
	public Music music2() {
		return new Music("EASY", "K-POP");
	}

	// music2를 가지는 singer2를 생성자 주입으로 빈으로 등록하시오.
	@Bean(name="singer2")
	public Singer Singer2() {
		return new Singer("르세라핌", music2());
	}
	
	@Bean(name="animal") // name값이 bean 이름으로 잡힘
	public Animal aaa() {
		return new Animal("야옹", "고양이");
	}
}
