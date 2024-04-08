package com.br.spring.di;

import org.springframework.stereotype.Service;

@Service
public class PhoneServiceMobileImpl implements PhoneService { // 모바일 용도

	@Override
	public void selectList() {
		System.out.println("모바일용 폰 전체 조회 서비스 실행");
	}

	@Override
	public void selectDetail() {
		System.out.println("모바일용 폰 한 개 조회 서비스 실행");
	}

}
