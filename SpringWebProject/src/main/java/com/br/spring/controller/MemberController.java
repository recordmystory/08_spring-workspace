package com.br.spring.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.br.spring.dto.MemberDto;
import com.br.spring.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
	private final MemberService memberService;
	private final BCryptPasswordEncoder bcryptPwdEncoder;
	
	//  --------------------------- 로그인 관련 ---------------------------
	@PostMapping("/signin.do")
	public void signIn(MemberDto m, HttpServletRequest request, HttpServletResponse response) throws IOException {
		MemberDto loginUser = memberService.selectMember(m); // 아이디와 비밀번호로 조회된 회원 객체
		
		// 로그인 성공시 : alert와 함께 메인페이지로 이동
		// 로그인 실패시 : alert와 함께 기존에 보인 페이지 유지
		
		// 실행시킬 script문을 요청했던 페이지로 돌려주는 행위
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		if(loginUser != null) { // 조회결과가 있을 경우
			request.getSession().setAttribute("loginUser", loginUser);
			out.println("alert('" + loginUser.getUserName() + "님 환영합니다.');");
			out.println("location.href='" + request.getContextPath() + "';");
		} else {
			out.println("alert('로그인에 실패했습니다. 아이디 및 비밀번호를 다시 확인해주세요');");
			out.println("history.back();");
		}
		out.println("</script>");
	}
	
	//  --------------------------- 로그아웃 관련 ---------------------------
	@RequestMapping("/signout.do")
	public String signOut(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	// --------------------------- 회원가입 관련 ---------------------------
	@GetMapping("/signup.page")
	public String signupPage() {
		return "member/signup";
	}
	
	@ResponseBody
	@GetMapping("/idcheck.do")
	public String ajaxIdCheck(String checkId) {
//		int count = memberService.selectUserIdCount(checkId);
	
		return memberService.selectUserIdCount(checkId) > 0 ? "NNNNN" : "YYYYY";
//		if(count > 0) { // 사용 불가능
//			return "NNNNN";
//		} else { // 사용 가능
//			return "YYYYY";			
//		}
	}
	
	@PostMapping("/signup.do")
	public String signUp(MemberDto member, RedirectAttributes redirectAttributes) {
		// log.debug("암호화 전 member : {}", member);
		
		// 암호화 처리
		member.setUserPwd(bcryptPwdEncoder.encode(member.getUserPwd()));
		
		int result = memberService.insertMember(member);
		
		// 회원가입 성공시 -> alert 메시지 출력 후 메인페이지 보여지게
		// 회원가입 실패시 -> alert 메시지 출력 후 기존에 작업중이던 페이지 보여지게
		
		/*
		 * * case 1  Model1 생성 		Model1 유지
		 * url 요청 -> controller -> 포워딩 jsp
		 * 
		 * * case 2   Model1 생성     Model1 소멸      Model2 생성    Model2 유지
		 * url 요청 -> controller -----------------> controller -> 포워딩 jsp
		 * 						    redirect 방식
		 */
		
		// redirectAttributes : 2번째까지 데이터 유지됨
			redirectAttributes.addFlashAttribute("alertTitle", "회원가입 서비스"); // alert창 title
		if(result > 0) {
			redirectAttributes.addFlashAttribute("alertMsg", "성공적으로 회원가입 되었습니다.");
		} else {
			redirectAttributes.addFlashAttribute("alertMsg", "회원가입에 실패했습니다.");
			redirectAttributes.addFlashAttribute("historyBackYN", "Y");
		}
		return "redirect:/"; // HomeController mainPage 메소드 실행 -> main.jsp 포워딩
		// log.debug("암호화 후 member : {}", member);
	}
}
