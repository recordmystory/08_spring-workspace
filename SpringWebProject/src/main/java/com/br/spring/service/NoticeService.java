package com.br.spring.service;

import java.util.List;

import com.br.spring.dto.NoticeDto;
import com.br.spring.dto.PageInfoDto;

public interface NoticeService {

	int selectNoticeListCount();

	List<NoticeDto> selectNoticeList(PageInfoDto pi);

	int insertNotice(NoticeDto notice);

	NoticeDto selectNotice(int no);

}
