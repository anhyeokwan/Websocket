package kr.or.common;

import java.util.ArrayList;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import kr.or.member.model.vo.Member;

@Component // 스프링객체를 사용하기 위한 어노테이션
@Aspect // aop용 어노테이션
public class Advice {
	// 포인트컷 생성
	@Pointcut(value="execution(* kr.or.member.model.service.MemberService.*(..))")
	public void allPointcut() { //메소드명이 아이디값 메소드 내용은 없음
		
	}
	
	@Pointcut(value="execution(* kr.or.member.model.service.MemberService.selectOneMember(..))")
	public void selectOne() {
		
	}
	
	@Pointcut(value="execution(* kr.or.member.model.service.MemberService.insertMember(..))")
	public void insertPointcut() {}
	
	@Pointcut(value="execution(* kr.or.member.model.service.MemberService.*Member())")
	public void selectAll() {}
	
	@Before(value="insertPointcut()") // 메소들 이름이 들어간다.
	public void pwChange(JoinPoint jp) { //JoinPoint는 매개변수를 꺼내온다
		Object[] args = jp.getArgs();
		Member m = (Member)args[0];
		m.setMemberPw("1234");
	}
	
	@AfterReturning(value="selectAll()", returning="returnObj")
	public void pwChange2(JoinPoint jp, Object returnObj) { // returnObj는 반환하는 객체, jp는 메소드 정보들어엤는 객체
		ArrayList<Member> list = (ArrayList<Member>)returnObj;
		for(Member m : list) {
			m.setMemberPw("비밀번호 숨겨!");
		}
	}
}





















