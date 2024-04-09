package com.br.mvc.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.br.mvc.dto.MemberDto;

@RequestMapping("/member")
@Controller
public class MvcController2 {
	// 요청시 전달값(파라미터) 받아내기 연습

	/*
	 * 1. HttpServletRequest 방법
	 */

	// @RequestMapping(value="/detail.do", method=RequestMethod.GET)
	@GetMapping("/detail.do") // Spring4부터 지원
	public String memberDetail(HttpServletRequest request) {
		int no = Integer.parseInt(request.getParameter("no"));
		System.out.println("조회하고자하는 번호 : " + no);
		return "main";
	}

	@PostMapping("/enroll1.do")
	public String memberEnroll1(HttpServletRequest request) throws UnsupportedEncodingException {
		// request.setCharacterEncoding("UTF-8");
		
		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
		String address = request.getParameter("address");
		
		System.out.println();
		System.out.println("이름 : " + name + ", 나이 : " + age + ", 주소 : " + address);
		return "main";
	}
	
	/*
	 * 2. @RequestParam 어노테이션 이용 방법 (Spring에서 제공)
	 * 
	 * 	1) 요청시 전달값을 받기 위한 변수를 매개변수로 작성하기
	 * 	2) 전달값의 키값과 받고자 하는 매개변수의 변수명이 동일할 경우 어노테이션 생략가능함
	 * 	3) 어노테이션의 추가적인 속성을 사용하기 위해서는 어노테이션 명시해야됨
	 * 
	 */
	@PostMapping("/enroll2.do")
	public String memberEnroll2(
			/* @RequestParam(value="name") */String name,
			@RequestParam(value="age", defaultValue="50") String age, @RequestParam(value="address") String addr) {
		
		
		MemberDto m = new MemberDto();
		m.setAge(age);
		m.setName(name);
		m.setAddr(addr);
		
		System.out.println(m);
		return "main";
	}
	
	/*
	 * 3. 커맨드 객체 방법
	 * 
	 * > 커맨드 객체 : 요청 파라미터들을 각 필드에 담고자 하는 객체
	 * 
	 * 내부적으로 해당 객체 기본생성자로 생성한 후에
	 * 각 필드의 setter 메소드로 전달값이 필드에 주입됨
	 * 단, 전달되는 값의 키값이 담고자하는 필드의 필드명과 같아야됨!
	 */
	@PostMapping("/enroll3.do")
	public String memberEnroll(MemberDto m) {
		// 내부적으로 setName 메소드가 실행됨
		System.out.println(m);
		return "main";
	}
}
