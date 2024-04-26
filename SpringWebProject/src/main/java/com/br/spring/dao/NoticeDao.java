package com.br.spring.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.br.spring.dto.AttachDto;
import com.br.spring.dto.NoticeDto;
import com.br.spring.dto.PageInfoDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class NoticeDao {
	private final SqlSessionTemplate sqlSessionTemplate;

	public List<NoticeDto> selectNoticeList(PageInfoDto pi) {
		int limit = pi.getBoardLimit();
		int offset = (pi.getCurrentPage() - 1) * limit;
		
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		return sqlSessionTemplate.selectList("noticeMapper.selectNoticeList", null, rowBounds);
	}

	public int selectNoticeListCount() {
		return sqlSessionTemplate.selectOne("noticeMapper.selectNoticeListCount");
	}

	public int insertNotice(NoticeDto notice) {
		return sqlSessionTemplate.insert("noticeMapper.insertNotice", notice);
	}

	public int insertAttach(AttachDto at) {
		return sqlSessionTemplate.insert("noticeMapper.insertAttach", at);
	}

	public NoticeDto selectNotice(int no) {
		return sqlSessionTemplate.selectOne("noticeMapper.selectNotice", no);
	}
}
