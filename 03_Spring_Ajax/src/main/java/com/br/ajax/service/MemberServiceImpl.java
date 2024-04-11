package com.br.ajax.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.br.ajax.dto.MemberDto;

@Service
public class MemberServiceImpl implements MemberService {

	private List<MemberDto> dbList = Arrays.asList
			(
				new MemberDto(1, "user01", "pass01", "홍길동"),
				new MemberDto(2, "user02", "pass02", "김디디"),
				new MemberDto(3, "user03", "pass03", "최동동"),
				new MemberDto(4, "user04", "pass04", "강두두")
			);
			
	@Override
	public String selectMemberByIdPwd(String id, String pwd) {
		// String result = null;
		
		for(MemberDto m : dbList) {
			if(m.getUserId().equals(id) && m.getUserPwd().equals(pwd)) {
				return m.getUserName();
			}
		}
		
		return null;
		
	}

	@Override
	public MemberDto selectMemberByNo(int no) {
		
		for(MemberDto m : dbList) {
			if(m.getUserNo() == no) {
				return m;
			}
		}
		
		return null;
		
	}

	@Override
	public List<MemberDto> selectMemberList() {
		return dbList;
	}

}
