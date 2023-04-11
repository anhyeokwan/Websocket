package kr.or.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import kr.or.member.model.service.MemberService;

//@Component
public class ScheduleTest {
	
	@Autowired
	private MemberService service;
	
	@Scheduled(fixedDelay = 5000)
	public void scheduleTest1() {
		System.out.println("예악작업 자동 실행 메소드!! -- 5초");
	}
	
	@Scheduled(fixedDelay = 10000)
	public void scheduleTest2() {
		System.out.println("10초마다 동작하는 함수");
	}
	
	@Scheduled(cron = "0 45 10 * * * ")
	public void scheduleTest3() {
		// service.deleteMember(54);
		System.out.println("크론식으로 동작하는 함수");
	}
}
