package com.br.sbatis.controller;

import java.util.Arrays;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	private Logger logger = org.slf4j.LoggerFactory.getLogger(NoticeController.class);
	
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
	
	@GetMapping("/enrollForm.do")
	public void noticeEnrollForm() {
		
	}
	
	@PostMapping("/insert.do")
	public String noticeInsert(NoticeDto n) {
		int result = noticeService.insertNotice(n);

		if(result > 0) { // 성공했을 경우 => 전체 목록 페이지
			return "redirect:/notice/list.do";
		} else { // 실패했을 경우 => 메인페이지로 돌아가게
			return "redirect:/";
		}
	}
	
	@GetMapping("/modifyForm.do")
	public String noticeModifyForm(int no, Model model) {
		
		NoticeDto n = noticeService.selectNoticeByNo(no);
		
		model.addAttribute("n", n);
		
		return "notice/modifyForm";
		
	}
	
	@PostMapping("/update.do")
	public String noticeUpdate(NoticeDto n) {
		int result = noticeService.updateNotice(n);
		
		if (result > 0) {
			return "redirect:/notice/detail.do?no=" + n.getNo();			
		} else {
			return "redirect:/";
		}
	}
	
	@PostMapping("/delete.do")
	public String noticeDelete(@Param ("deleteNo") String[] deleteNo) {
		log.debug("String[] deleteNo: {}", String.join("/", deleteNo));
		
		int result = noticeService.deleteNotice(deleteNo);
		
		if(result == deleteNo.length) { // result값이 배열의 길이와 일치할 경우 삭제 성공된 것
			return "redirect:/notice/list.do";
		} else {
			return "redirect:/";
		}
	}
}
