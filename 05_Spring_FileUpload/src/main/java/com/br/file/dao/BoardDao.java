package com.br.file.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.br.file.dto.AttachDto;
import com.br.file.dto.BoardDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class BoardDao {
	private final SqlSessionTemplate sqlSessionTemplate;
	
	public int insertBoard(BoardDto board) {
		return sqlSessionTemplate.insert("boardMapper.insertBoard", board);
	}

	public int insertAttach(AttachDto attach) {
		return sqlSessionTemplate.insert("boardMapper.insertAttach", attach);
	}
	
	public List<AttachDto> selectAtList(){
		return sqlSessionTemplate.selectList("boardMapper.selectAtList");
	}
	
}
