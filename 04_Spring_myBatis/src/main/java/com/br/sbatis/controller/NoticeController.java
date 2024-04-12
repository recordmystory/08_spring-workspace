package com.br.sbatis.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.br.sbatis.dto.NoticeDto;
import com.br.sbatis.service.NoticeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/notice")
@Controller
@RequiredArgsConstructor
public class NoticeController {
	private final NoticeService noticeService;
	
	@GetMapping("/list.do")
	public String noticeList(Model model) {
		List<NoticeDto> list = noticeService.selectNoticeList();
		// log.debug("list : {}", list);
		model.addAttribute("list", list);
		
		 return "/notice/list";
	}
	
	@GetMapping("/detail.do") // /notice/detail.do?no=x
	public ModelAndView noticeDetail(int no, ModelAndView mv) {
		NoticeDto n = noticeService.selectNoticeByNo(no);
		log.debug("n : {}", n);
		
		mv.addObject("n", n).setViewName("notice/detail");
		
		return mv;
	}
	
	
	
}
