package com.br.ajax.service;

import java.util.List;

import com.br.ajax.dto.MemberDto;

public interface MemberService {
	
	// 아이디와 비번을 이용해 특정 회원 이름 검색하는 서비스
	String selectMemberByIdPwd(String id, String pwd);
	
	// 회원번호로 회원 전체 정보 조회 서비스
	MemberDto selectMemberByNo(int no);
	
	// 전체 회원 조회 서비스
	List<MemberDto> selectMemberList();
}
