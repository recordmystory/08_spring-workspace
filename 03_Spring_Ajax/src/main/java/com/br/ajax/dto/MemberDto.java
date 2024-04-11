package com.br.ajax.dto;

import lombok.AllArgsConstructor;
//import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
//@Builder
public class MemberDto {
	
	private int userNo;
	private String userId;
	private String userPwd;
	private String userName;

}
