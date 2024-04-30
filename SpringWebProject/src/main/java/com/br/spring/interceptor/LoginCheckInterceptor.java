package com.br.spring.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.lang.Nullable;
import org.springframework.ui.Model;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.FlashMapManager;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;

/*
 * * Interceptor 
 * - 해당 url 요청시 실행되는 Controller가 실행되기 전(preHandle), 실행된 후(postHandle)에 낚아채서 실행시킬 내용 작성
 * - 보통 로그인여부 판단할때 또는 로그인한 사용자의 권한체크할때 주로 사용 
 * 
 * > preHandle : DispatcherServlet이 해당 Controller를 호출하기 전에 낚아채는 영역
 * > postHandle : Controller에서 요청처리 한 후 DispatcherServlet으로 뷰 정보가 리턴되는 순간 낚아채는 영역
 */

public class LoginCheckInterceptor implements HandlerInterceptor {
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		// return true 시 => Controller를 정상적으로 수행시키겠다.
		// return false시 => Controller를 수행시키지 않겠다. 
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute("loginUser") == null) { // 로그인되어있지 않을 경우
			
			// Interceptor에서 RedirectAttributes 사용하고자 할 경우
			FlashMapManager flashMapManager = RequestContextUtils.getFlashMapManager(request);
			
			FlashMap flashMap = new FlashMap();
			flashMap.put("alertTitle", "비정상적인 접근");
			flashMap.put("alertMsg", "로그인 후 접근가능한 페이지입니다.");
			
			flashMapManager.saveOutputFlashMap(flashMap, request, response);
			
			response.sendRedirect(request.getContextPath()); // main페이지 요청
			return false;
		}else { // 로그인되어잇을 경우
			return true;
		}
		
	}
	
	/*
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable ModelAndView modelAndView) throws Exception {
		
		// modelAndView.getModel() => Model 객체 받을 수 있음 
		Model model = (Model)modelAndView.getModel();
		//model.get("키") => 밸류
		
	}
	*/

}
