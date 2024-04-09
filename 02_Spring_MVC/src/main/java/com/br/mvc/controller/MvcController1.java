package com.br.mvc.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/book") // 해당 컨트롤러는 앞으로 /book으로 시작하는 모든 url 요청을 담당해서 처리
@Controller
public class MvcController1 {
	// 웅답페이지로 포워딩 또는 redirect 위주로 연습

	/*
	 * // @RequestMapping(value="book/list.do", method=RequestMethod.GET) url
	 * mapping값 맨앞에 / 생략 가능 // @RequestMapping(value="book/list.do") method 속성 생략 가능
	 * (생략시 둘다 받음)
	 * 
	 * @RequestMapping(value="book/list.do", method=RequestMethod.GET) public String
	 * bookList() { // 포워딩 (/WEB-INF/views/book/list.jsp)
	 * 
	 * return "book/list"; }
	 * 
	 * @RequestMapping(value="book/detail.do") public void bookeDetail() { // 포워딩
	 * (/WEB-INF/views/book/detail.jsp)
	 * 
	 * }
	 * 
	 * //
	 * 
	 * @RequestMapping(value="book/modifyForm.do") public String bookModify() {
	 * return "book/modify"; }
	 */

	@RequestMapping(value = "/list.do", method = RequestMethod.GET)
	public String bookList() {
		// 포워딩 (/WEB-INF/views/book/list.jsp)

		return "/book/list";
	}

	@RequestMapping(value = "/detail.do")
	public void bookDetail() {
		// 포워딩 (/WEB-INF/views/book/detail.jsp)

	}

	//
	@RequestMapping(value = "/modifyForm.do")
	public String bookModifyForm() {
		return "/book/modify";
	}

	// 따로 응답페이지를 포워딩하지 않고 script 코드를 반환해서 흐름제어하기
	@RequestMapping(value = "/modify.do", method = RequestMethod.POST)
	public void bookModify(HttpServletRequest request, HttpServletResponse response) throws IOException {

		// DB 연동이 되었을 경우 => 서비스 호출
		// int result = new BookServiceImpl().updateBook();
		int result = Math.random() < 0.5 ? 1 : 0; // 랜덤으로 성공 또는 실패 결과 가져오기

		response.setContentType("text/html; charset=UTF-8"); // 응답데이터에 대한 mime-type 설정
		PrintWriter out = response.getWriter();
		
		out.println("<script>");
		
		if (result > 0) {
			// 성공일경우 => 해당 페이지에서 alert로 성공 메세지 출력 후 상세페이지 재요청
			out.println("alert('성공적으로 수정되었습니다.');");
			out.println("location.href='" + request.getContextPath() + "/book/detail.do';");
			
		} else {
			// 실패일경우 => 해당 페이지에서 alert로 실패 메세지 출력 후 현재 페이지에서 다시 머물도록			
			out.println("alert('수정에 실패하였습니다.');");
			out.println("history.back();");			
		}
		
		out.println("</script>");
		
	}
	
	// /book/enrollForm.do url 요청시 /WEB-INF/views/book/enroll.jsp 포워딩
	@RequestMapping(value="/enrollForm.do")
	public String bookEnrollForm() {
		return "book/enroll";
	}
	
	// redirect 방법 (controller단에서 url 재요청 하는 방법)
	@RequestMapping(value="/enroll.do")
	public String bookEnroll() {
		int result = Math.random() < 0.5 ? 1:0;
		
		if(result > 0) {
			// 성공일 경우 => 목록 페이지로 이동 (url 재요청)
			return "redirect:/book/list.do";
		} else {
			// 실패일 경우 => 메인 페이지로 이동 (url 재요청)
			return "redirect:/";
		}
	}
}
