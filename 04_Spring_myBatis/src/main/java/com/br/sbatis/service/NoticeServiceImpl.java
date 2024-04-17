package com.br.sbatis.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.br.sbatis.dto.NoticeDao;
import com.br.sbatis.dto.NoticeDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class NoticeServiceImpl implements NoticeService {

	private final NoticeDao noticeDao;

	@Override
	public List<NoticeDto> selectNoticeList() {
		return noticeDao.selectNoticeList();
	}

	@Override
	public NoticeDto selectNoticeByNo(int noticeNo) {
		return noticeDao.selectNoticeByNo(noticeNo);
	}

	@Override
	public int insertNotice(NoticeDto n) {
		int result = 0;

		try {
			result = noticeDao.insertNotice(n);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public int updateNotice(NoticeDto n) {
		return noticeDao.updateNotice(n);
	}

	@Override
	public int deleteNotice(String[] deleteNo) {
		return noticeDao.deleteNotice(deleteNo);
	}

	@Override
	public int transactionTest() {
		
		int result1 = noticeDao.insertNotice(NoticeDto.builder()
														.title("트랜잭션 테스트용 제목2")
														.content("트랜잭션 테스트용 내용2")
														.build()); // 성공용
		
		int result2 = noticeDao.insertNotice(new NoticeDto()); // 실패용
		
		// 둘중에 하나라도 실패하면 전체 실패되어야함
		
		return result1 * result2;
	}

}
