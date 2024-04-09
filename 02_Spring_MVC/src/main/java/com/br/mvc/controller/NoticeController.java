package com.br.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.br.mvc.service.NoticeService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/notice")
@RequiredArgsConstructor
@Controller
public class NoticeController {
	private final NoticeService noticeService;
	
	@GetMapping("/list.do")
	public String noticeList() {
		
		return "notice/list";
	}
	
	@GetMapping("/detail.do")
	public String noticeDetail(@RequestParam(value="no") int no) {
		
		return "notice/detail";
	}
}
