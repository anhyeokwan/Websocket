package kr.or.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.or.member.model.vo.Member;

@Component
@Aspect
public class PasswordEncAdvice {
	@Autowired
	private SHA246Util passEnc;
	
	@Pointcut(value="execution(* kr.or.member.model.service.MemberService.*Member(kr.or.member.model.vo.Member))") // 메소드명이 member로 끝나면서 매개변수가 Member인 메소드
	public void encPointcut() {}
	
	@Pointcut(value="execution(* kr.or.member.model.service.MemberService.updatePassword(..))")
	public void updatePassword() {}
	
	@Before(value="encPointcut()")
	public void passwordEnc(JoinPoint jp) throws Exception {
		String methodName = jp.getSignature().getName();
		System.out.println("비밀번호 암화화 적용 메소드 : " + methodName);
		Object[] args = jp.getArgs();
		Member m = (Member)args[0];
		String memberPw = m.getMemberPw();
		System.out.println("사용자 입력 비밀번호 : " + memberPw);
		if(memberPw != null) {
			String encPw = passEnc.encData(memberPw);
			m.setMemberPw(encPw);
		}
		
	}
	
	@Before(value="updatePassword()")
	public void passwordLock(JoinPoint jp) throws Exception{
		String methodName = jp.getSignature().getName();
		System.out.println("비밀번호 암호화 적용 메소드 : " + methodName);
		
		Object[] args = jp.getArgs();
		Member m = (Member)args[0];
		String memberPw = m.getMemberPw();
		
		if(memberPw != null) {
			String encPw = passEnc.encData(memberPw);
			m.setMemberPw(encPw);
		}
	}
	
}






















