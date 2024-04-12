package com.br.sbatis.dto;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class NoticeDao {
	private final SqlSessionTemplate sqlSessionTemplate;

	public List<NoticeDto> selectNoticeList() {
		return sqlSessionTemplate.selectList("noticeMapper.selectNoticeList");
	}

	public NoticeDto selectNoticeByNo(int noticeNo) {
		return sqlSessionTemplate.selectOne("noticeMapper.selectNoticeByNo", noticeNo);
	}

	public int insertNotice(NoticeDto n) {
		return sqlSessionTemplate.insert("noticeMapper.insertNotice", n);
	}

	public int updateNotice(NoticeDto n) {
		return sqlSessionTemplate.update("noticeMapper.updateNotice", n);
	}
}
