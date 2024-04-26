package com.br.spring.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class NoticeDto {
	private String noticeNo;
	private String noticeTitle;
	private String noticeWriter; 
	private String noticeContent; 
	private String registDate; 
	
	private List<AttachDto> attachList;
}
