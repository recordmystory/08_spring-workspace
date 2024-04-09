package com.br.mvc.dao;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.br.mvc.dto.NoticeDto;

@Repository
public class NoticeDao {


	private NoticeDao nDao;
	
	private List<NoticeDto> dbList = Arrays.asList
			(
				new NoticeDto(1, "제목1", "내용1"), 
				new NoticeDto(2, "제목2", "내용2"),
				new NoticeDto(3, "제목3", "내용3")
			);

	public List<NoticeDto> selectNoticeList() {
		return dbList;

	}

	public NoticeDto selectNoticeByNo(int noticeNo) {
		NoticeDto result = null;

		for (NoticeDto n : dbList) {
			if (n.getNo() == noticeNo) {
				result = n;
			}
		}
		return result;

	}

	public int insertNotice(NoticeDto n) {
		dbList.add(n);
		
		return 1;

	}
}
