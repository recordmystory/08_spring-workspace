package com.br.spring.di;

import org.springframework.stereotype.Service;

@Service("pService") // @Service 어노테이션 사용할 경우 빈으로 등록됨
public class PhoneServiceWebImpl implements PhoneService { // 웹 용도

	@Override
	public void selectList() {
		System.out.println("웹용 폰 전체 조회 서비스 실행");
	}

	@Override
	public void selectDetail() {
		System.out.println("웹용 폰 한 개 조회 서비스 실행");
	}

}
