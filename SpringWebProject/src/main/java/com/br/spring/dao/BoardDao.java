package com.br.spring.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.br.spring.dto.AttachDto;
import com.br.spring.dto.BoardDto;
import com.br.spring.dto.PageInfoDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class BoardDao {
	private final SqlSessionTemplate sqlSessionTemplate;

	public int selectBoardListCount() {
		return sqlSessionTemplate.selectOne("boardMapper.selectBoardListCount");
	}

	public List<BoardDto> selectBoardList(PageInfoDto pi) {
		
		int limit = pi.getBoardLimit();
		int offset = (pi.getCurrentPage() - 1) * limit;
		
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		return sqlSessionTemplate.selectList("boardMapper.selectBoardList", null, rowBounds);
	}

	public int selectSearchListCount(Map<String, String> search) {
		return sqlSessionTemplate.selectOne("boardMapper.selectSearchListCount", search);
	}

	public List<BoardDto> selectSearchList(Map<String, String> search, PageInfoDto pi) {
		RowBounds rowBounds = new RowBounds((pi.getCurrentPage() - 1) * pi.getBoardLimit(), pi.getBoardLimit());
		return sqlSessionTemplate.selectList("boardMapper.selectSearchList", search, rowBounds);
	}
	
	public int insertBoard(BoardDto b) {
		return sqlSessionTemplate.insert("boardMapper.insertBoard", b);
	}
	
	public int insertAttach(AttachDto at){
		return sqlSessionTemplate.insert("boardMapper.insertAttach", at);		
	}
}