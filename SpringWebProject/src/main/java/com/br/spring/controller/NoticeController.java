package com.br.spring.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.br.spring.dto.AttachDto;
import com.br.spring.dto.MemberDto;
import com.br.spring.dto.NoticeDto;
import com.br.spring.dto.PageInfoDto;
import com.br.spring.service.NoticeService;
import com.br.spring.util.FileUtil;
import com.br.spring.util.PagingUtil;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/notice")
@Controller
public class NoticeController {
	private final NoticeService noticeService;
	private final PagingUtil pagingUtil;
	private final FileUtil fileUtil;

	@GetMapping("/list.do")
	public ModelAndView list(@RequestParam(value="page", defaultValue = "1") int currentPage, ModelAndView mv) {
		int listCount = noticeService.selectNoticeListCount();
		PageInfoDto pi = pagingUtil.getPageInfo(listCount, currentPage, 5, 5);
		List<NoticeDto> list = noticeService.selectNoticeList(pi);
		
		mv.addObject("pi", pi).addObject("list", list).setViewName("notice/list");
		
		return mv;
	}
	
	@GetMapping("/registForm.page")
	public String registForm() {
		return "notice/registForm";
	}
	
	@PostMapping("/regist.do")
	public String regist(NoticeDto notice, List<MultipartFile> uploadFiles, HttpSession session, RedirectAttributes redirectAttributes) {
		MemberDto loginUser = (MemberDto) session.getAttribute("loginUser");
		notice.setNoticeWriter(String.valueOf(loginUser.getUserNo()));
		
		List<AttachDto> attachList = new ArrayList<>();
		
		for(MultipartFile uploadFile : uploadFiles) {
			if(uploadFiles != null && !uploadFile.isEmpty()) {
				Map<String, String> map = fileUtil.fileUpload(uploadFile, "notice");
			
				attachList.add(AttachDto.builder()
						.filePath(map.get("filePath"))
						.filesystemName(map.get("filesystemName"))
						.originalName(map.get("originalName"))
						.refType("N")
						.build());
			}
		}
		
		notice.setAttachList(attachList);
		
		int result = noticeService.insertNotice(notice);
		
		redirectAttributes.addFlashAttribute("alertTitle", "공지사항 작성 서비스");

		if(attachList.isEmpty() && result == 1 || !attachList.isEmpty() && result == attachList.size()) { // 첨부파일이 없고 result가 1일 경우 || 첨부파일이 있고 insert된 행수가 attachList의 길이와 같을 경우
			redirectAttributes.addFlashAttribute("alertMsg", "공지사항 작성이 완료되었습니다.");			
		} else {			
			redirectAttributes.addFlashAttribute("alertMsg", "공지사항 작성에 실패했습니다.");
			redirectAttributes.addFlashAttribute("historyBackYN", "Y");
		}
		
		return "redirect:/notice/list.do";
	}
	
	@GetMapping("/detail.do")
	public String detail(int no, Model model) {
		model.addAttribute("notice", noticeService.selectNotice(no));
		
		return "notice/detail";
	}
}
