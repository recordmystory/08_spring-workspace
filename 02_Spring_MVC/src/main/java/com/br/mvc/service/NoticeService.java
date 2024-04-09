package com.br.mvc.service;

import java.util.List;

import com.br.mvc.dto.NoticeDto;

public interface NoticeService {
	// 전체조회용 서비스
	List<NoticeDto> selectNoticeList();
	
	// 번호로 공지사항 한 개 조회용 서비스
	NoticeDto selectNoticeByNo(int noticeNo);
	
	// 공지사항 등록용 서비스
	int insertNotice(NoticeDto n);
}
