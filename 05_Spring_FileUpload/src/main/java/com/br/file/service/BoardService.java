package com.br.file.service;

import java.util.List;

import com.br.file.dto.AttachDto;
import com.br.file.dto.BoardDto;

public interface BoardService {
	
	// 첨부파일 한 개 게시글 추가용 서비스
	int insertOneFileBoard(BoardDto board, AttachDto attach);
	
	// 다중 첨부파일 게시글 추가용 서비스
	int insertManyFileBoard(BoardDto board, List<AttachDto> list);
	
	// 첨부파일 조회용 
	List<AttachDto> selectAtList();
}
