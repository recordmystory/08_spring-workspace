package com.br.spring.service;

import org.springframework.stereotype.Service;

import com.br.spring.dao.MemberDao;
import com.br.spring.dto.MemberDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {
	
	private final MemberDao memberDao;
	
	@Override
	public MemberDto selectMember(MemberDto m) {
		return null;
	}

	@Override
	public int selectUserIdCount(String checkId) {
		return 0;
	}

	@Override
	public int insertMember(MemberDto m) {
		return 0;
	}

	@Override
	public int updateProfileImg(MemberDto m) {
		return 0;
	}

	@Override
	public int updateMember(MemberDto m) {
		return 0;
	}

	@Override
	public int deleteMember(String userId) {
		return 0;
	}

}
