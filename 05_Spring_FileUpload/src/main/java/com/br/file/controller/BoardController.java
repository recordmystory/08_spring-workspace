package com.br.file.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.br.file.dto.AttachDto;
import com.br.file.dto.BoardDto;
import com.br.file.service.BoardService;
import com.br.file.util.FileUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/board")
@Controller
public class BoardController {
	private final BoardService boardService;
	private final FileUtil fileUtil;

	@PostMapping("/insert1.do")
	public String insertOneFileBoard(BoardDto board, MultipartFile uploadFile) {
		
		// * 1~3번까지 FileUtil 클래스의 fileUpload 메소드 호출
		
		// 라이브러리 추가 + multipartResolver 빈으로 등록해야 값이 잘 주입됨

		// log.debug("board : {}", b);
		// log.debug("attach : {}", uploadFile);

		AttachDto attach = null;
		if (uploadFile != null && !uploadFile.isEmpty()) { // 첨부파일이 존재할 경우
			// 전달된 첨부파일 업로드 처리 (외부경로)

			Map<String, String> map = fileUtil.fileUpload(uploadFile);
			
			// 4) DB에 기록을 위해 AttachDto 객체 생성해서 insert할 데이터 담기
			attach = AttachDto.builder()
								.filePath(map.get("filePath"))
								.originalName(map.get("originalName"))
								.fileSystemName(map.get("fileSystemName"))
								.build();
			
		}
		
		log.debug("board : {} ", board);
		log.debug("attach: {}", attach);
		int result = boardService.insertOneFileBoard(board, attach);
		
		if(result > 0) {
			log.debug("게시글 작성 성공");
		} else {
			log.debug("게시글 작성 실패");
		}
		
		return "redirect:/";
	}
}
