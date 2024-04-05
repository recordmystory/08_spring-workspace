package com.br.spring.di;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.br.spring.ioc.xml.Calculator;
import com.br.spring.ioc.xml.Student;

@Controller
public class PhoneController {
	
	/*
	@RequestMapping("/test1") // http://localhost:8888/spring/test1 url로 요청시 실행시킬 메소드
	public void test1() {
		System.out.println("test1 메소드 실행됨");
	}
	*/
	
	/*
	 * * 스프링 컨테이너로부터 Bean 객체를 가져오는 방법
	 * > 가장 근본적인 방법 : 컨테이너 객체(ApplicationContext)로부터 getBean으로 가져오는 방법
	 * 
	 * > DI와 관련된 어노테이션을 이용하는 방법
	 * - @Inject : 등록되어있는 빈들 중 타입(class)이 일치하는 Bean 객체를 주입해줌
	 * - @Resource : 등록되어있는 빈들 중 이름(id)이 일치하는 Bean 객체를 주입해줌
	 * - @Authowired : 등록되어있는 빈들 중 타입으로 먼저 탐색하고 동일한 타입이 여러 개일 경우 이름으로 찾아서 주입
	 * 
	 */
	
	/*
	 * @Autowired 방법
	 * 
	 *  1) 필드 주입 
	 *  - 주입받고자 하는 필드 상단에 @Autowired 어노테이션 기재
	 */
	
	/*
	 * 2) 생성자 주입
	 * - 해당 클래스의 매개변수 생성자 작성 
	 * - 이때 매개변수로는 주입받고자하는 변수를 작성
	 * 
	 * - @Autowired 어노테이션 생략 가능
	 */

//	@Autowired
//	private Phone a; // 기본적으로 타입을 이용해 빈을 탐색함(필드명은 중요하지 않음)
	
	/*
	private Phone a;
	
	public PhoneController(Phone a) { // 매개변수에 DI가 발생
		this.a = a;
	}
	*/
	
	/*
	 * 3) 메소드 주입 
	 * - 메소드 작성 후 매개변수에 객체를 주입받고자하는 매개변수 작성시 DI 발생
	 * - 단, 메소드 상단에 @Autowired 기재해야됨
	 * - 주로 setter 메소드 사용 (메소드명 상관없긴함)
	 */
	
	/*
		private Phone a;
		
		@Autowired //@Autowired 작성 안할 때 주입이 되지 않아 실행결과는 null로 나옴
		public void abcd(Phone a) { this.a = a; }
		
		@RequestMapping("/test1")
		public void diTest1() {
			System.out.println(a);
		}
	*/
	
	/*
	 * 4) 동일한 타입의 빈이 여러 개일 경우
	 * 	  동일한 타입의 빈이 여러 개일 경우 오류를 유발시킬 수 있음 (@Inject와 동일)
	 * 	  단, 이름을 이용해서 빈을 탐색할 수 있도록 해주면 오류를 해결 
	 * 		 ㄴ 필드명 또는 매개변수명을 주입받고자하는 빈의 이름으로 작성하면됨
	 * 			(내부적으로 @Autowired에 내장되어있는 @Qualitier(식별자)의 기능)
	 * 
	 * 		탐색순서 : 타입(class) => 이름(id)
	 */
	
	/* 오류나는 구문
		@Autowired
		private Phone a;
		
		@Autowired
		private Phone b;
	*/
	
	// 1) 필드 주입
	
	/*
	@Autowired
	private Phone phone1;
	
	@Autowired
	private Phone phone2;
	
	@RequestMapping("/test1")
	public void diTest1() {
		System.out.println(phone1);
		System.out.println(phone2);
	}
	*/
	
	// 2) 생성자 주입
	/*
		private Phone a;
		private Phone b;
		
		public PhoneController(Phone phone1, Phone phone2) {
			this.a = phone1;
			this.b = phone2;
		}
		
		@RequestMapping("/test1")
		public void diTest1() {
			System.out.println(a);
			System.out.println(b);
		}
	*/
	
	// 3) 메소드 주입
	/*
		private Phone a;
		private Phone b;
		
		@Autowired
		public void setA(Phone phone1) {
			this.a = phone1;
		}
		
		@RequestMapping("/test1")
		public void diTest1() {
			System.out.println(a);
			System.out.println(b);
		}
		
		@Autowired
		public void setB(Phone phone2) {
			this.b = phone2;
		}
	*/
	
	// ------------------ 실습 -----------------------
	
	/*
	 * root-context.xml 에 
		1. calc라는 이름으로 Calculator 클래스를 빈으로 등록
		
		2. setter 주입을 이용해서 student 라는 이름으로 Student클래스를 빈으로 등록 
		   이때 calc 필드에는 calc이름의 빈을 주입하도록
		   이름, 학원명, 반색상 값은 임의로 담기
		
		3. PhoneController클래스에서 
		
		   Calculator, Student 클래스를 필드로 선언한 후 
		   @Autowired 생성자 주입을 이용해서 각 필드에 DI 진행 
		
		  /example 라는 url로 요청시 실행되는 임의의 메소드를 작성하여 
		  Calculator필드를 이용해서 plus, minus, mul 메소드 실행 
		  Student 필드 출력 해보기
	 */
	
	@Autowired
	private Calculator calc;
	@Autowired
	private Student student;
	
	public void CalculatorController(Calculator calc, Student student) {
		this.calc = calc;
		this.student = student;
	}
	
	@RequestMapping("/example")
	public void goExample() {
		calc.plus(10, 10);
		calc.minus(10, 10);
		calc.mul(10, 10);
		
		System.out.println(student);
	}
}
