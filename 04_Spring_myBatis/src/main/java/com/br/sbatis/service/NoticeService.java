package com.br.sbatis.service;

import java.util.List;

import com.br.sbatis.dto.NoticeDto;

public interface NoticeService {
	// 전체 목록 조회용 서비스 (selectNoticeList)
	List<NoticeDto> selectNoticeList();
	
	// 글번호로 공지사항 조회용 서비스 (selectNoticeByNo)
	NoticeDto selectNoticeByNo(int noticeNo);
	
	// 공지사항 등록용 서비스 (insertNotice)
	int insertNotice(NoticeDto n); 

	// 공지사항 수정용 서비스 (updateNotice)
	int updateNotice(NoticeDto n);

}
