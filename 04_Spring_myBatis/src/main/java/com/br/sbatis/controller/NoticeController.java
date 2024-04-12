package com.br.sbatis.controller;

import org.springframework.stereotype.Controller;

import com.br.sbatis.service.NoticeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class NoticeController {
	private final NoticeService noticeService;
	
	
}
