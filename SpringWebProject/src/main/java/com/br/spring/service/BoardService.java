package com.br.spring.service;

import java.util.List;
import java.util.Map;

import com.br.spring.dto.AttachDto;
import com.br.spring.dto.BoardDto;
import com.br.spring.dto.PageInfoDto;
import com.br.spring.dto.ReplyDto;

public interface BoardService {
	
	// * 게시판 리스트 조회 서비스 (페이징)
	int selectBoardListCount();
	List<BoardDto> selectBoardList(PageInfoDto pi);
	
	// 게시판 검색 리스트 조회 서비스 (페이징)
	int selectSearchListCount(Map<String, String> searchMap);
	List<BoardDto> selectSearchList(Map<String, String> searchMap, PageInfoDto pi);
	
	// 게시판 작성하기 서비스
	int insertBoard(BoardDto board);
	
	// 게시판 상세조회 서비스
	// 조회수 증가 서비스
	BoardDto selectBoard(int boardNo);
	int updateIncreaseCount(int boardNo);
	
	// 댓글 리스트 조회 서비스
	List<ReplyDto> selectReplyList(int boardNo);
	
	// 댓글 작성 서비스
	int insertReply(ReplyDto reply);
	
	// 게시판 수정 서비스
	int updateBoard(BoardDto board);
	
	// 게시판 삭제 서비스
	int deleteBoard(int boardNo);
	
	// 댓글 삭제 서비스
	int deleteReply(int replyNo);
	
	List<AttachDto> selectDelFileList(String[] delFileNo);
	
	int updateBoard(BoardDto board, String[] delFileNo);
}
