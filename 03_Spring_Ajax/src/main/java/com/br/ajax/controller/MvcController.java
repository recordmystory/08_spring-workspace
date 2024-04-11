package com.br.ajax.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MvcController {
	
	/** 메인페이지 띄워주는 메소드
	 * @return main
	 */
	@RequestMapping(value={"/", "main.do"})
	public String main() {
		return "main";
	}
	
	/** 회원 페이지 이동용 1
	 * 
	 */
	@GetMapping("/member/manage1.do")
	public void memberManagePage1() {}
	
	/** 회원 페이지 이동용 2
	 * 
	 */
	@GetMapping("/member/manage2.do")
	public void memberManagePage2() {}
	
}
