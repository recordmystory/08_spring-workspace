package com.br.mvc.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.br.mvc.dao.NoticeDao;
import com.br.mvc.dto.NoticeDto;

import lombok.RequiredArgsConstructor;

//@AllArgsConstructor
@RequiredArgsConstructor
@Service
public class NoticeServiceImpl implements NoticeService {

	/*
	 * DI (의존성주입)
	 * - IoC 컨테이너에 등록되어있는 Bean 객체들을 주입받아 사용하는 개념
	 * 
	 * * IoC (제어역전)
	 * - 프로그램의 흐름을 개발자가 아닌 프레임워크가 주도함
	 * 모든 객체의 생명주기 관련한 것들을 프레임워크가 관리
	 * 
	 * * IoC 컨테이너에 빈 등록할 수 있는 방법
	 * - xml 방식 : Spring Bean Configuration File(xml)에 <bean>태그로 등록
	 * 			  단, 해당 xml 파일이 Spring Container 생성시 읽혀져야됨
	 * 			  root-context.xml, servlet-context.xml은 이미 읽혀지도록 등록
	 * 
	 * - 어노테이션 방식 : @Configuration 역할의 자바 클래스에서 @Bean 어노테이션으로 등록
	 * - 빈스캐닝에 의한 방식 : servlet-context.xml <context:component-scan>에 의해 
	 * 					모든 클래스들 다 스캔하면서 @Component와 관련된 어노테이션이 붙은 클래스들을 자동으로 Bean으로 등록해줌
	 * 
	 * @Component : @Controller, @Service, @Repository
	 * 
	 * @Autowired 이용한 DI 방법
	 * - 필드주입
	 * - 생성자주입
	 * - 메소드주입
	 */
	
	/* 필드 주입
	 * @Autowired 
	 * private NoticeDao nDao;
	 */
	
	// 메소드 주입
	
	/*
	private NoticeDao nDao;
	
	@Autowired
	public void setnDao(NoticeDao nDao) {
		this.nDao = nDao;
	}
	
	@Override
	public List<NoticeDto> selectNoticeList() {
		return null;
	}
	*/
	
	/*
	 * * 생성자 주입 주로 사용 
	 * - @Autowired 생략 가능
	 * - 롬복을 이용하면 생성자도 편하게 작성 가능(@AllArgsConstructor, @RequiredArgsConstructor)
	 * 
	 * 1) 주입받고자 하는 필드만 final 필드로 작성
	 * 2) 해당 클래스 상단에 @RequiredArgsConstructor 붙이기
	 * 			=> final 필드에 대해서만 매개변수 생성자로 만들어줌
	 */
	
	// 생성자 주입
	// 의존성 주입을 받고자하는 필드에만 final을 붙임
	private final NoticeDao nDao;
	
	// private String a;
	// 롬복에 의해서 생성자 만들어짐
	
	/*
	public NoticeServiceImpl(NoticeDao nDao) {
		this.nDao = nDao;
	}
	*/
	
	
	@Override
	public NoticeDto selectNoticeByNo(int noticeNo) {
		return nDao.selectNoticeByNo(noticeNo);
	}

	@Override
	public int insertNotice(NoticeDto n) {
		return nDao.insertNotice(n);
	}

	@Override
	public List<NoticeDto> selectNoticeList() {
		return nDao.selectNoticeList();
	}

}
