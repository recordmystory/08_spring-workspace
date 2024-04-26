package com.br.spring.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.br.spring.dao.NoticeDao;
import com.br.spring.dto.AttachDto;
import com.br.spring.dto.NoticeDto;
import com.br.spring.dto.PageInfoDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class NoticeServiceImpl implements NoticeService {
	private final NoticeDao noticeDao;

	@Override
	public List<NoticeDto> selectNoticeList(PageInfoDto pi) {
		return noticeDao.selectNoticeList(pi);
	}

	@Override
	public int selectNoticeListCount() {
		return noticeDao.selectNoticeListCount();
	}

	@Override
	public int insertNotice(NoticeDto notice) {
		int result1 = noticeDao.insertNotice(notice);
		
		// attachment insert
		int result2 = 1;
		List<AttachDto> attachList = notice.getAttachList();
		if(!attachList.isEmpty()) { // 첨부파일이 존재할 경우
			result2 = 0;
			for(AttachDto at : attachList) {
				result2 += noticeDao.insertAttach(at);
			}
		}
		
		return result1 * result2;
	}

	@Override
	public NoticeDto selectNotice(int no) {
		return noticeDao.selectNotice(no);
	}
}
