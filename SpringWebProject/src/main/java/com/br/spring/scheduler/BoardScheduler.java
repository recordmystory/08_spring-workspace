package com.br.spring.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.br.spring.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class BoardScheduler {
	
	private final BoardService boardService;
	
	/*
	 *	* Scheduler
	 *	  특정날짜 | 특정시간 | 일정주기마다 작업을 묵시적으로 실행시켜주는 객체
	 *	  즉, 별도의 액션이 없어도 알아서 특정 시간이 되면 자동으로 실행되는 개념
	 *	  
	 *	* Spring에서의 Scheduler
	 * - 매번 실행시키고자 하는 작업들을 따로 정의해두는 클래스 만들기 (해당 클래스 빈 등록)
	 * - 해당 클래스 내에 각 작업별 메소드 작성
	 * 	  ㄴ 반환형 : void
	 * 	  ㄴ 매개변수 : 없음
	 * 	  ㄴ @Scheduled 부여
	 * 
	 * - servlet-context.xml 스케줄링 사용을 위한 task 추가
	 * 
	 * * cron 표현식
	 * 작업을 실행시키고자하는 날짜 및 시간 또는 일정주기를 지정할 때 사용하는 문법
	 * 
	 * 	1) 형식
	 * 	   초 분 시 일 월 요일 [년도]
	 * 
	 * 	2) 각 형식별 표기가능한 값
	 * 	   초 : 0 ~ 59
	 * 	   분 : 0 ~ 59
	 * 	   시 : 0 ~ 23
	 * 	   일 : 1 ~ 31
	 * 	   월 : 0 ~ 11 (JAN, FEB, MAR, APR, MAY, JUN, JUL, AUG, SEP, OCT, NOV, DEC)
	 * 	   요일 : 0 ~ 6 (SUN, MON, TUE, WES, THU, FRI, SAT)
	 * 
	 * 	3) 각 형식에 작성가능한 키워드
	 * 	   ? : 설정값 없을때 (일|요일)
	 * 	   * : 모든 조건 (초|분|시|일|월)
	 * 	   L : 마지막 (일|요일)
	 * 	   W : 가장 가까운 평일 (일) ex) 10W : 10일이 평일이였을 경우 10일에 실행되고, 10일이 토요일이였을 경우 9일에 실행 (10일과 가장 가까운 평일)
	 * 	   # : 몇주째인지 (요일) ex) 3#2 : 2번째주 수요일
	 * 	   / : 주기 (시작시간/단위) ex) 분 작성하는 곳에 0/10 => 0분부터 10분 간격으로 실행 
	 * 	   - : 범위 ex) 시 작성하는 곳에 1-3 => 1시, 2시, 3시
	 * 	   , : 여러 개 지정
	 * 
	 * 4) 예시
	 * 	  0 0 * * * * : 매일 모든 시간마다
	 * 	  0 0 5 * * * : 매일 5시마다 
	 * 	  0 0 3-5 * * * : 매일 3시, 4시, 5시마다
	 * 	  0 1/30 3,4 * * * : 매일 3시 1분  / 3시 31분 / 4시 1분 / 4시 31분마다 
	 * 	  0 0 12 * * MON-FRI : 평일 12시마다 
	 */
	
	@Scheduled(cron = "0 38 9 * * *")
	public void execute1() {
		log.debug("매일 오전 9시 38분마다 실행됨");
	}
	
	@Scheduled(cron = "0 0/1 * * * *")
	public void execute2() {
		log.debug("1분마다 실행됨");
	}
	
	@Scheduled(cron = "0 0 0/1 * * *")
	public void execute3() {
		log.debug("1시간마다 실행됨");
	}
	
	/*
	 * 통계 정보를 위해 매일 밤 12시에 현재 게시글의 총 개수를 조회해서 로그로 기록을 남기는 스케줄러가 필요하다고 가정
	 */
	@Scheduled(cron = "0 0 0 * * *")
	public void execute4() {
		log.debug("현재 게시글의 총 개수 : {}", boardService.selectBoardListCount());
	}
	
	@Scheduled(cron = "0 0 1 * * 0") // 일요일 새벽 1시마다 현재 status가 N인 댓글을 delete
	public void execute5() {
		int result = boardService.deleteReplyCompletely();
		log.debug("완전 제거한 댓글 개수 : {}", result);
	}
}
