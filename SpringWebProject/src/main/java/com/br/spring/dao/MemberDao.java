package com.br.spring.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.br.spring.dto.MemberDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class MemberDao {
	private final SqlSessionTemplate sqlSessionTemplate;

	public MemberDto selectMember(MemberDto m) {
		return sqlSessionTemplate.selectOne("MemberMapper.selectMember", m);
	}

	public int selectUserIdCount(String checkId) {
		return sqlSessionTemplate.selectOne("MemberMapper.selectUserIdCount", checkId);
	}

	public int insertMember(MemberDto m) {
		return sqlSessionTemplate.insert("MemberMapper.insertMember", m);
	}

	public int updateProfileImg(MemberDto m) {
		return sqlSessionTemplate.update("MemberMapper.updateProfileImg", m);
	}

	public int updateMember(MemberDto m) {
		return sqlSessionTemplate.update("MemberMapper.updateMember", m);
	}

	public int deleteMember(String userId) {
		return sqlSessionTemplate.update("MemberMapper.deleteMember", userId);
	}
}
