package com.br.spring.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.br.spring.dto.MemberDto;
import com.br.spring.service.MemberService;
import com.br.spring.util.FileUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
	private final MemberService memberService;
	private final BCryptPasswordEncoder bcryptPwdEncoder;
	private final FileUtil fileUtil;
	
	//  --------------------------- 로그인 관련 ---------------------------
	@PostMapping("/signin.do")
	public void signIn(MemberDto m, HttpServletRequest request, HttpServletResponse response) throws IOException {
		MemberDto loginUser = memberService.selectMember(m); // 아이디와 비밀번호로 조회된 회원 객체
		
		// 로그인 성공시 : alert와 함께 메인페이지로 이동
		// 로그인 실패시 : alert와 함께 기존에 보인 페이지 유지
		
		// 실행시킬 script문을 요청했던 페이지로 돌려주는 행위
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		// 암호화 전 : 아이디와 비밀번호로 조회된 회원 검색
		// 암호화 후 : 아이디로만 조회된 회원 객체
		//			 loginUser.getUserPwd() : DB에 저장되어있는 암호문
		// 			 매개변수 m.getUserPwd() : 로그인 요청 시 입력했던 평문 그대로의 비밀번호
		
		out.println("<script>");
		if(loginUser != null && bcryptPwdEncoder.matches(m.getUserPwd(), loginUser.getUserPwd())) { // 암호화 후 로그인 성공시
		// if(loginUser != null) { // 조회결과가 있을 경우
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
		 * 
		 * * case 3 RedirectAttributest1 생성							RedirectAttributes1 유지
		 * url 요청 ---------> controller ----------> controller ----------> 포워딩
		 * 				.addFlashAttribute 데이터 담기
		 */
		
		// redirectAttributes : 2번째까지 데이터 유지됨
			redirectAttributes.addFlashAttribute("alertTitle", "회원가입 서비스"); // alert창 title
		if(result > 0) {
			redirectAttributes.addFlashAttribute("alertMsg", "성공적으로 회원가입 되었습니다.");
		} else {
			redirectAttributes.addFlashAttribute("alertMsg", "회원가입에 실패했습니다.");
			redirectAttributes.addFlashAttribute("historyBackYN", "Y"); // 실패했을 경우 이전 페이지로 돌아가기 : history.back()
		}
		return "redirect:/"; // HomeController mainPage 메소드 실행 -> main.jsp 포워딩
		// log.debug("암호화 후 member : {}", member);
	}
	
	// --------------------------- 마이페이지 관련 ---------------------------
	@GetMapping("/myinfo.page")
	public String myInfo() {
		return "member/myinfo";
	}
	
	@ResponseBody
	@PostMapping("/modifyProfile.do")
	public String ajaxModifyProfile(MultipartFile uploadFile, HttpSession session) {
		
		MemberDto loginUser = (MemberDto) session.getAttribute("loginUser");
		String originalProfileUrl = loginUser.getProfileUrl(); // 기존 경로
		
		// 파일 업로드
		Map<String, String> map = fileUtil.fileUpload(uploadFile, "profile"); // profile 폴더에 저장
		loginUser.setProfileUrl(map.get("filePath") + "/" + map.get("filesystemName"));
		
		int result = memberService.updateProfileImg(loginUser);
		
		if(result > 0) {
			if(originalProfileUrl != null) {
				new File(originalProfileUrl).delete();
			}
			return "SUCCESS";
		} else {
			new File(map.get("filePath") + "/" + map.get("filesystemName")).delete(); // delete() : 파일 삭제
			return "FAIL";
		}
	}
	
	@PostMapping("/modify.do")
	public String modify(MemberDto member, RedirectAttributes redirectAttributes, HttpSession session) {
		int result = memberService.updateMember(member);
		
		// 성공시 : alert 메시지, 세션에 Member 객체 갱신
		// 실패시 : alert 메시지
		redirectAttributes.addFlashAttribute("alertTitle", "회원 정보 수정 서비스");
		if(result > 0) {
			redirectAttributes.addFlashAttribute("alertMsg", "성공적으로 정보 수정 되었습니다.");
			session.setAttribute("loginUser", memberService.selectMember(member));
		} else {
			redirectAttributes.addFlashAttribute("alertMsg", "정보수정에 실패했습니다.");				
		}
		
		return "redirect:/member/myinfo.page";
	}
	
	@PostMapping("/leave.do")
	public String leave(String userPwd, HttpSession session, RedirectAttributes redirectAttributes) {
		// 현재 로그인한 회원 비번이랑 사용자가 입력한 비번값이 일치하는지
		// session에 담겨있음  			userPwd
		
		MemberDto loginUser = (MemberDto) session.getAttribute("loginUser");
		
		redirectAttributes.addFlashAttribute("alertTitle", "회원탈퇴 서비스");
		if(bcryptPwdEncoder.matches(userPwd, loginUser.getUserPwd())) {
			memberService.deleteMember(loginUser.getUserId());
			redirectAttributes.addFlashAttribute("alertMsg", "회원탈퇴가 성공적으로 완료되었습니다. 그동안 이용해주셔서 감사합니다.");
			session.invalidate();
		} else { // 비밀번호 잘못 입력했을 경우
			redirectAttributes.addFlashAttribute("alertMsg", "비밀번호가 틀렸습니다. 비밀번호를 다시 확인해주세요.");
			redirectAttributes.addFlashAttribute("historyBackYN", "Y");
		}
		
		return "redirect:/";
	}
	
	@PostMapping("/modifyPwd.do")
	public String updatePassword(String userPwd, String updatePwd, HttpSession session, RedirectAttributes redirectAttributes) {
		MemberDto loginUser = (MemberDto) session.getAttribute("loginUser");
		
		redirectAttributes.addFlashAttribute("alertTitle", "비밀번호 변경용 서비스");
		if(bcryptPwdEncoder.matches(userPwd, loginUser.getUserPwd())) {
			loginUser.setUserPwd(bcryptPwdEncoder.encode(updatePwd));
			int result = memberService.updatePassword(loginUser);
			if(result > 0) {
				redirectAttributes.addFlashAttribute("alertMsg", "비밀번호 변경이 완료되었습니다.");				
				session.setAttribute("loginUser", memberService.selectMember(loginUser));
			} else {
				redirectAttributes.addFlashAttribute("alertMsg", "비밀번호가 변경에 실패했습니다. 다시 시도해주세요.");				
			}
		} else { 
			redirectAttributes.addFlashAttribute("alertMsg", "비밀번호가 틀렸습니다. 다시 입력해주세요.");
		}
		
		return "redirect:/member/myinfo.page";
		
	}
}
