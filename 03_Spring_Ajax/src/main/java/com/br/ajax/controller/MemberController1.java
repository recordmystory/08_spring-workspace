package com.br.ajax.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.br.ajax.dto.MemberDto;
import com.br.ajax.service.MemberService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/member1")
@Controller
public class MemberController1 {
	private final MemberService memberService;

	// @ResponseBody : 비동기식으로 데이터 응답시 필요한 어노테이션
	// 해당 어노테이션이 붙은 메소드에서 리턴되는 값은 뷰명(jsp)가 아니라 어떤 data(text, json, xml, ...)라는 걸 의미
	@ResponseBody 
	@GetMapping(value="/detail1.do", produces="text/html; charset=utf-8")
	public String memberDetail1(String id, String pwd/* , HttpServletResponse response */) throws IOException{ // 어노테이션 생략시 키값과 동일한 이름의 매개변수 값으로 선언하면 자동으로 주입됨
		System.out.printf("아이디 : %s, 비밀번호 : %s\n", id, pwd);
		
		String result = memberService.selectMemberByIdPwd(id, pwd);
		
		return result; // return "이름"; 기본적으로 /WEB-INF/views/홍길동.jsp로 포워딩하려고 들거임
	}
	
	@ResponseBody
	@GetMapping(value="/detail2.do", produces="text/html; charset=utf-8")
	public String memberDetail2(String userId, String userPwd) {	
		return memberService.selectMemberByIdPwd(userId, userPwd);
	}
	
	@ResponseBody
	@GetMapping(value="/detail3.do", produces="application/json")
	public MemberDto memberDetail3(int userNo) {
		MemberDto result = memberService.selectMemberByNo(userNo);
		
		return result;
		// {userNo: 1, userId: "user01", userPwd: "pass01", userName: "홍길동"}
		
	}
	
	@ResponseBody
	@GetMapping(value="/list.do", produces="application/json")
	public List<MemberDto> selectMemberList(){
		return memberService.selectMemberList();
	}
}
