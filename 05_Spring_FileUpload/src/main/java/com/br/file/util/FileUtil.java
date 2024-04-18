package com.br.file.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUtil {
	public Map<String, String> fileUpload(MultipartFile uploadFile) {
		String filePath = "/upload/" + new SimpleDateFormat("yyyy/MM/dd").format(new Date());
		File filePathDir = new File(filePath);

		// 해당 경로의 폴더가 존재하지 않을 경우 폴더 생성
		if(!filePathDir.exists()) { 
			filePathDir.mkdirs();
		}

		// 2) 파일명 수정 작업
		String originalName = uploadFile.getOriginalFilename(); // getOriginalFilename : 파일의 원본명
		
		/*
		 *	.tar.gz인 경우 따로 처리해야됨
		 */
		String ext = originalName.endsWith(".tar.gz")? ".tar.gz" : originalName.substring(originalName.lastIndexOf(".")); // 파일의 확장자
		String fileSystemName = UUID.randomUUID().toString().replace("-", "") + ext; // UUID.randomUUID() : 32자리의 랜덤값 + 하이픈(-) 4개 생성
		
		// 3) 업로드 (폴더에 파일 저장)
		try {
			uploadFile.transferTo(new File(filePathDir, fileSystemName));
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		
		// DB의 데이터 기록 시 필요한 데이터(저장경로, 원본명, 수정명) 다시 반환해주기
		Map<String, String> map = new HashMap<>();
		map.put("filePath", filePath);
		map.put("originalName", originalName);
		map.put("fileSystemName", fileSystemName);
		
		return map;
	}
}
