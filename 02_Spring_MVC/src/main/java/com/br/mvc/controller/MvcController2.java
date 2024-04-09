package com.br.mvc.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	 */
	@PostMapping("/enroll2.do")
	public String memberEnroll2(
			/* @RequestParam(value="name") */String name,
			@RequestParam(value="age", defaultValue="50") int age, @RequestParam(value="address") String addr) {
		
		System.out.println("이름 : " + name + ", 나이 : " + age + ", 주소 : " + addr);
		return "main";
	}
}
