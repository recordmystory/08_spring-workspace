package com.br.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/book") // 해당 컨트롤러는 앞으로 /book으로 시작하는 모든 url 요청을 담당해서 처리
@Controller
public class MvcController1 {
	// 웅답페이지로 포워딩 또는 redirect 위주로 연습
	
	/*
		// @RequestMapping(value="book/list.do", method=RequestMethod.GET) url mapping값 맨앞에 / 생략 가능
		// @RequestMapping(value="book/list.do") method 속성 생략 가능 (생략시 둘다 받음)
		@RequestMapping(value="book/list.do", method=RequestMethod.GET)
		public String bookList() {
			// 포워딩 (/WEB-INF/views/book/list.jsp)
			
			return "book/list";
		}
		
		@RequestMapping(value="book/detail.do")
		public void bookeDetail() {
			// 포워딩 (/WEB-INF/views/book/detail.jsp)
			
		}
		
		// 
		@RequestMapping(value="book/modifyForm.do")
		public String bookModify() {
			return "book/modify";
		}
	*/
	
	@RequestMapping(value="/list.do", method=RequestMethod.GET)
	public String bookList() {
		// 포워딩 (/WEB-INF/views/book/list.jsp)
		
		return "/book/list";
	}
	
	@RequestMapping(value="/detail.do")
	public void bookeDetail() {
		// 포워딩 (/WEB-INF/views/book/detail.jsp)
		
	}
	
	// 
	@RequestMapping(value="/modifyForm.do")
	public String bookModify() {
		return "/book/modify";
	}
}
