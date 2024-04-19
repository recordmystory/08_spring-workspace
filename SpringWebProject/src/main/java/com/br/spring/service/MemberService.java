package com.br.spring.service;

import com.br.spring.dto.MemberDto;

public interface MemberService {
	// 로그인용 서비스
	MemberDto selectMember(MemberDto m);
	
	// 회원가입용 서비스 (아이디 중복체크도 있어야됨)
	int selectUserIdCount(String checkId);
	int insertMember(MemberDto m);
	
	// 마이페이지 정보수정용 서비스 (프로필이미지만 별도로 수정)
	int updateProfileImg(MemberDto m);
	int updateMember(MemberDto m);
	
	// 회원탈퇴용 서비스
	int deleteMember(String userId);
}
