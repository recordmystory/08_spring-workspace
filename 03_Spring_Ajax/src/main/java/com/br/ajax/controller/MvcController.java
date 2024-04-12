package com.br.ajax.controller;

import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MvcController {
	
	private Logger logger = org.slf4j.LoggerFactory.getLogger(MvcController.class);
	
	/** 메인페이지 띄워주는 메소드
	 * @return main
	 */
	@RequestMapping(value={"/", "main.do"})
	public String main() {
		logger.info("인포 하이루");
		logger.debug("디버그 하이루");
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
