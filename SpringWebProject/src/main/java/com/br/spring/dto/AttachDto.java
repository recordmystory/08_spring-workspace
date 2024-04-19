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
public class AttachDto {
	private int fileNo;
	private String filePath;
	private String filesystemName;
	private String originalName; 
	private Date uploadDate;
	private String refType;
	private int refNo; 
}
