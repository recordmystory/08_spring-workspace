package com.br.spring.ioc.xml;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.br.spring.di.Animal;
import com.br.spring.ioc.anno.SpringBeanConfig;

public class SpringMain {
	public static void main(String[] args) {
		// * Spring 사용 전 (개발자가 직접 생성)
		// Calculator calc = new Calculator();
		
		// * Spring 사용 후 (프레임워크가 미리 생성해둔 빈을 가져다 씀)
		/*
		 * > 스프링 컨테이너와 관련된 클래스
		 * - GenericXmlApplicationContext 
		 * - ClassPathXmlApplicationContext
		 * - Annotation 
		 * 
		 */
		
		// 컨테이너
		// classpath는 classes 폴더를 가리킴
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:xml/appCtx.xml");
		
		Calculator calc = (Calculator) ctx.getBean("calculator", Calculator.class);
		calc.plus(10, 3);
		calc.minus(10, 3);
		calc.mul(2, 5);
		System.out.println();
		
		Student stu1 = (Student) ctx.getBean("student1", Student.class);
		Student stu2 = (Student) ctx.getBean("student2", Student.class);
		Student stu3 = (Student) ctx.getBean("student3", Student.class);
		
		System.out.println(stu1);
		System.out.println(stu2);
		System.out.println(stu3);
		
		System.out.println("=======================================================================================");
		
		// * Spring 사용 전 (매 요청때마다 매번 Dao 객체가 생성되고 소멸됨을 반복)
		// 만약에 10000건의 요청이 동시에 들어왔다면 10000개의 dao 객체가 생성되고 소멸됨을 반복 -> 메모리를 빈번하게 사용함
		
		/*
		 // 주소값이 다름
			// 전체조회요청 => controller => service => dao
			StudentDao sDao = new StudentDao();
			System.out.println(sDao);
			sDao.selectStudentList();
			
			// 등록요청 => controller => service => dao
			StudentDao sDao1 = new StudentDao();
			System.out.println(sDao1);
			sDao1.insertStudent(stu1);
		*/
		
		// * Spring 사용 후 (한번 생성해둔 객체를 스프링 가지고 있을거고 나는 필요할때마다 받아쓰면 됨 -> 메모리를 효율적으로 사용 가능함, 한번 생성해둔 객체를 재사용 (singleton))
		
		// 주소값이 같음
		// 전체조회요청 => controller => service => dao
		StudentDao sDao = ctx.getBean("studentDao", StudentDao.class);
		System.out.println(sDao);
		sDao.selectStudentList();
		
		// 등록요청 => controller => service => dao
		StudentDao sDao1 = ctx.getBean("studentDao", StudentDao.class);
		System.out.println(sDao1);
		sDao1.insertStudent(stu1);
		
		GenericXmlApplicationContext ctx2 = new GenericXmlApplicationContext("classpath:xml/appCtx.xml"); // xml 파일 내에 빈 읽기
		ctx2.getBean("animal", Animal.class);
	
		AbstractApplicationContext ctx3 = new AnnotationConfigApplicationContext(SpringBeanConfig.class); // xml 파일 내에 빈 읽기
		
	}
}
