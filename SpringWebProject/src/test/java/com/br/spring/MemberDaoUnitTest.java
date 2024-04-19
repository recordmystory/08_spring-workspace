package com.br.spring;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.br.spring.dao.MemberDao;
import com.br.spring.dto.MemberDto;

@RunWith(SpringJUnit4ClassRunner.class) // JUnit4를 이용해서 테스트
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/root-context.xml", "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
/*
 * @ContextConfiguration : 사용할 객체가 빈으로 등록되어있는 문서의 경로 작성
 * 
 * 1) <bean> location="file:src/main/webapp/WEB-INF/spring/root-context.xml"
 * 2) @Component location="file:src/main/webapp/WEB-INF/appServlet/servlet-context.xml" 
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING) // @FixMethodOrder(MethodSorters.NAME_ASCENDING) : 메소드의 이름 순으로 테스트를 수행
public class MemberDaoUnitTest {
	
	@Autowired
	private MemberDao memberDao; // 필드 주입
	
	/*
	 * * 테스트 작성 패턴
	 * given (준비) : 어떠한 데이터를 가지고 할건지
	 * when (실행) : 어떤 메소드 실행시
	 * then (검증) : 어떤 결과가 나와야되는지
	 * 
	 * 
	 * assertEquals(예상값, 실행값) : 실행값과 예상값이 일치하는지 확인해주는 메소드
	 * assertNotNull(실행값) : 실행값이 null이 아닌지를 확인해주는 메소드
	 * assertTrue(조건) : 해당 조건이 참인지 확인해주는 메소드 
	 */
	
	@Test
	public void test01_로그인테스트() {
		// given 
		MemberDto m = MemberDto.builder()
								.userId("user01")
								.userPwd("pass01")
							    .build();
		
		// when
		MemberDto loginUser = memberDao.selectMember(m);
		
		// then
		assertNotNull(loginUser);
	}
	
	@Test
	public void test02_일치하는아이디수조회테스트() {
		// given
		String checkId = "user02";
		
		// when
		int count = memberDao.selectUserIdCount(checkId);
		
		// then
		assertEquals(1, count);
	}
	
	@Ignore // => @Ignore로 변경
	public void test03_회원가입테스트() {
		// given
		MemberDto m = MemberDto.builder()
							   .userId("test01")
							   .userPwd("pass01")
							   .userName("test")
							   .email("test@br.com")
							   .address("서울시 서초구 양재동")
							   .build();
		
		// when
		int result = memberDao.insertMember(m);
		
		// then
		assertEquals(1, result);
	}
	
	@Test
	public void test04_회원정보수정테스트() {
		// given
		MemberDto m = MemberDto.builder()
							   .userId("user02")
							   .userName("김디디")
							   .email("updateTest@naver.com")
							   .address("서울시 중랑구 면목동")
							   .gender("M")
							   .build();
		
		// when
		int result = memberDao.updateMember(m);
		
		// then
		assertEquals(1, result);
	}
	
	@Test
	public void test05_회원탈퇴테스트() {
		// given
		String userId = "user01";
		
		// when
		int result = memberDao.deleteMember(userId);
		
		// then
		assertEquals(1, result);
	}
}
