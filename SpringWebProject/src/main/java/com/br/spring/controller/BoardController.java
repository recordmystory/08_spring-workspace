package com.br.spring.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.br.spring.dto.AttachDto;
import com.br.spring.dto.BoardDto;
import com.br.spring.dto.MemberDto;
import com.br.spring.dto.PageInfoDto;
import com.br.spring.service.BoardService;
import com.br.spring.util.FileUtil;
import com.br.spring.util.PagingUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/board")
@RequiredArgsConstructor
@Controller
public class BoardController {
	private final BoardService boardService;
	private final PagingUtil pagingUtil;
	private final FileUtil fileUtil;
	
	// * 게시판 리스트 조회, 페이징
	@GetMapping("/list.do")
	// jsp에서 쿼리 스트링으로 페이지 번호를 넘기지 않았을 때 defaultValue값을 설정해놨기 때문에 1로 요청됨
	public ModelAndView list(@RequestParam(value="page", defaultValue = "1") int currentPage, ModelAndView mv) {
		int listCount = boardService.selectBoardListCount();
		PageInfoDto pi = pagingUtil.getPageInfo(listCount, currentPage, 5, 5);
		List<BoardDto> list = boardService.selectBoardList(pi);
		
		mv.addObject("pi", pi).addObject("list", list).setViewName("board/list"); // 메서드 체이닝 가능
		
		return mv;
	}
	
	@GetMapping("/search.do")
	public ModelAndView search(@RequestParam(value="page", defaultValue="1") int currentPage, @RequestParam Map<String, String> search, ModelAndView mv) {
		
		 log.debug("search : {}", search);
		 
		 int listCount = boardService.selectSearchListCount(search);
		 // listCount, 요청페이지번호, 5, 5
		 PageInfoDto pi = pagingUtil.getPageInfo(listCount, currentPage, 5, 5);
		 List<BoardDto> list = boardService.selectSearchList(search, pi);
		 
		 mv.addObject("pi", pi).addObject("list", list).addObject("search", search).setViewName("board/list");
		 
		return mv;
	}
	
	// 글 작성 관련
	@GetMapping("/registForm.page")
	public String registForm() {
		return "board/registForm";
	}
	
	@PostMapping("/regist.do")
	public String regist(BoardDto board, List<MultipartFile> uploadFiles, HttpSession session, RedirectAttributes redirectAttributes) {
		MemberDto loginUser = (MemberDto) session.getAttribute("loginUser");
		board.setBoardWriter(String.valueOf(loginUser.getUserNo()));
		
		List<AttachDto> attachList = new ArrayList<>();
		
		for(MultipartFile uploadFile : uploadFiles) {
			if(uploadFiles != null && !uploadFile.isEmpty()) {
				// 파일 업로드
				Map<String, String> map = fileUtil.fileUpload(uploadFile, "board");
				
				// insert할 데이터 -> AttachDto 객체 만들기 -> attachList에 담기
				attachList.add(AttachDto.builder()
										.filePath(map.get("filePath"))
										.filesystemName(map.get("filesystemName"))
										.originalName(map.get("originalName"))
										.refType("B")
										.build());
			}
		}
		
		board.setAttachList(attachList); // 첨부파일이 없었을 경우 텅빈리스트
		
		int result = boardService.insertBoard(board);
		
		// 성공시 alert 메시지와 함께 list 페이지(1번 페이지)로 이동
		// 실패시 alert 메시지와 함께 작성페이지에 머물기
		
		redirectAttributes.addFlashAttribute("alertTitle", "게시판 작성서비스");
		
		if(attachList.isEmpty() && result == 1 || !attachList.isEmpty() && result == attachList.size()) { // 첨부파일이 없고 result가 1일 경우 || 첨부파일이 있고 insert된 행수가 attachList의 길이와 같을 경우
			redirectAttributes.addFlashAttribute("alertMsg", "게시판 글 작성이 완료되었습니다.");			
		} else {			
			redirectAttributes.addFlashAttribute("alertMsg", "게시판 글 작성에 실패했습니다.");
			redirectAttributes.addFlashAttribute("historyBackYN", "Y");
		}
		
		return "redirect:/board/list.do";
	}
}
