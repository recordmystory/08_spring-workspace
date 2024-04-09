package com.br.mvc.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class NoticeDto {
	private int no;
	private String title;
	private String content;
	
	// private String noTitle; // 롬복 쓸 때 필드명 소문자 두 자 이상으로 작성
	
}
