package com.br.ajax.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.br.ajax.dto.MemberDto;
import com.br.ajax.service.MemberService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/member2")
//@Controller
@RestController // 이 클래스 안에 모든 메소드들에 전부 @ResponseBody가 붙여진채로 동작함
public class MemberController2 {
	private final MemberService memberService;

	// @ResponseBody : 비동기식으로 데이터 응답시 필요한 어노테이션
	// 해당 어노테이션이 붙은 메소드에서 리턴되는 값은 뷰명(jsp)가 아니라 어떤 data(text, json, xml, ...)라는 걸 의미
	 
	@GetMapping(value="/detail1.do", produces="text/html; charset=utf-8")
	public String memberDetail1(String id, String pwd/* , HttpServletResponse response */) throws IOException{ // 어노테이션 생략시 키값과 동일한 이름의 매개변수 값으로 선언하면 자동으로 주입됨
		System.out.printf("아이디 : %s, 비밀번호 : %s\n", id, pwd);
		
		String result = memberService.selectMemberByIdPwd(id, pwd);
		
		return result; 
	}
	
	
	@GetMapping(value="/detail2.do", produces="text/html; charset=utf-8")
	public String memberDetail2(String userId, String userPwd) {	
		return memberService.selectMemberByIdPwd(userId, userPwd);
	}
	
	
	@GetMapping(value="/detail3.do", produces="application/json")
	public MemberDto memberDetail3(int userNo) {
		MemberDto result = memberService.selectMemberByNo(userNo);
		
		return result;
		
	}
	
	
	@GetMapping(value="/list.do", produces="application/json")
	public List<MemberDto> selectMemberList(){
		return memberService.selectMemberList();
	}
	
	
	@GetMapping(value="/map.do", produces="application/json")
	public Map<String, Object> mapTest() {
		Map<String, Object> map = new HashMap<>();
		map.put("no", 1);
		map.put("list", memberService.selectMemberList());
		map.put("m", memberService.selectMemberByNo(2));
		
		return map;
	}
}
