package kr.or.common;

import java.util.ArrayList;

import org.aspectj.lang.JoinPoint;

import kr.or.member.model.vo.Member;

// 비즈니스 로직 수행시 결과값이 에러없이 리턴되는 경우
public class AfterReturningAdvice {
	public void afterReturn(JoinPoint jp, Object returnObj) {
		String methodName = jp.getSignature().getName();
		System.out.println(methodName);
		ArrayList<Member> list = (ArrayList<Member>)returnObj;
		
		for(Member m : list) {
			m.setMemberPw("비밀번호는 안알려줄꺼야");
		}
	}
}


























