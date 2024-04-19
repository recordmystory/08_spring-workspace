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
public class ReplyDto {
	private int replyNo;
	private String replyContent;
	private int refBoardNo;
	private String replyWriter;
	private String registDate;
	private String status;
}
