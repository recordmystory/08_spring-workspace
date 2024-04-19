package com.br.spring.dto;

import java.sql.Date;

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
public class BoardDto {
	private int boardNo;
	private String boardTitle;
	private String boardWriter;
	private String boardContent;
	private int count;
	private Date registDate;
	private String status;
}
