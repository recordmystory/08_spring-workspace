package com.br.mvc.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.br.mvc.dto.NoticeDto;
import com.br.mvc.service.NoticeService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/notice")
@RequiredArgsConstructor
@Controller
public class NoticeController {
	private final NoticeService noticeService;
	
	/*
	 * * 포워딩시 필요한 데이터를 담는 방법
	 * 
	 * 1. Model 객체 이용하는 방법
	 * 	requestScope 영역으로 데이터를 맵형식(key-value)으로 담을 수 있는 스프링에서 제공하는 객체
	 * 	setAttribute가 아닌 addAttribute로 담으면 됨
	 * 
	 * Model 객체가 필요할 경우 매개변수로 레퍼런스 작성해두면 됨
	 */
	@GetMapping("/list.do")
	public String noticeList(Model model) {
		// List<NoticeDto> list = noticeService.selectNoticeList();
		// model.addAttribute("list", list);
		model.addAttribute("list", noticeService.selectNoticeList());
		return "notice/list";
	}
	
	/*
		@GetMapping("/detail.do")
		public String noticeDetail(int no, Model model) {
			
			model.addAttribute("n", noticeService.selectNoticeByNo(no));
			return "notice/detail";
		}
	*/
	
	/*
	 * 2. ModelAndView 객체 이용하기
	 * 	  Model 객체와 View 객체가 합쳐져있는 형태
	 * 	  Model은 데이터를 담는 영역의 객체라면 View는 응답뷰에 대한 정보를 담을 수 있는 영역의 객체 (단, View는 단독으로 사용 불가)
	 * 
	 * 	  ModelAndView 객체에 데이터와 응답뷰에 대한 정보 다 담고 해당 객체를 리턴하면 됨
	 */
	

	@GetMapping("/detail.do")
	public ModelAndView noticeDetail(int no, ModelAndView mv) {
		// 메소드 체이닝 가능
		mv.addObject("n", noticeService.selectNoticeByNo(no)).setViewName("notice/detail");;
		
		return mv;
	}
}
