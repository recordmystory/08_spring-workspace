package com.br.mvc.controller;

import javax.servlet.annotation.WebServlet;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/*@Controller
 * - @Component의 구체화된 어노테이션으로 클라이언트 요청시 그 요청을 받아 처리할 클래스에 부여하는 어노테이션
 * 스프링이 관리할 수 있는 빈으로 등록이 됨 -> url 요청시 DispatcherServlet의 HandlerMapping에 의해서 해당 컨트롤러가 찾아져서 자동으로 실행
 * 
*/

@Controller // 빈 스캐닝에 의해서 자동으로 빈으로 등록
public class MvcController {
	// 스프링 사용전
//	@WebServlet("/")
//	public class XXXServlet extends HttpServlet{
//		public void doGet(HttpServletRequest request, HttpServletResponse response) throws XXXException{
	// request.getDispatcher("/WEB-INF/views/main.jsp").forward(response, request);
//}
//	}
	
	/*
	 * 1. 요청처리를 담당하는 역할의 클래스 상단에 @Controller 어노테이션 등록
	 * 		> servlet-context.xml (DispatcherServlet)에 기술되어있는
	 * 		  <annotation-driven> + <context:component-scan>에 의해서 자동으로 빈으로 등록됨
	 * 
	 * 2. 각 url 요청별 실행할 메소드를 
	 * 	  해당 메소드 상단에 @RequestMapping 어노테이션 등록
	 * 	
	 * @RequestMapping(value="" method="RequestMethod.GET|POST")
	 * - 요청을 인식하는 어노테이션으로 url 매핑값과 요청전송방식(GET, POST)을 인식함
	 * - url과 실행시킬 메소드를 매핑시켜주는 어노테이션
	 * 
	 * < 속성 >
	 * 1) value : URL mapping값 (여러 개 작성 가능, {}로 묶어야됨)
	 * 2) method : 요청 전송 방식 (RequestMethod의 필드 (GET|POST)) method값 생략시 default값은 GET과 POST 둘다임
	 * 
	 * <메소드 작성법>
	 * 1) 반환타입 
	 * 		- String : 응답할 jsp의 경로 또는 jsp명
	 * 		- void : 응답할 jsp의 경로 또는 이름을 url 매핑값과 동일하게 인식함
	 * 
	 * 2) 메소드명 : 아무일도 안함 -> 아무렇게나 작성해도 됨 (우리가 직접 호출도 안함)
	 * 
	 * 3) 매개변수 : 요청과 응답을 위한 각종 변수를 선언
	 * 	  - HttpServletRequest request
	 * 	  - HttpServletResponse response
	 * 	  - HttpSession session
	 * 	  - 커맨드 객체 : 요청시 전달값(파라미터)를 받아내는 객체
	 *    - 일반 변수 : 요청시 전달값(파라미터)를 받아내는 변수
	 *    - Model model : 포워딩시 응답페이지상에 필요한 데이터를 담을 수 있는 객체
	 *    - ModelAndView mv : Model(데이터) + View(응답뷰)를 같이 담을 수 있는 객체
	 * 
	 */
	@RequestMapping(value={"/", "main.do"}, method=RequestMethod.GET)
	public String abcd(/* String a, HttpSession session */) { // 메소드명 의미 없이 지어도됨
		System.out.println("MvcController 클래스의 abcd 메소드 실행됨");
		
		// /WEB-INF/views/main.jsp로 포워딩
		return "/main"; 
		
		// 반환되는 값은 사실상 DispatcherServlet의 ViewResolver로 전달됨
	}

}
