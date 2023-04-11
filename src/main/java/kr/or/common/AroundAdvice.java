package kr.or.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.util.StopWatch;

// 실행흐름에 직접 관여해서 샐행 전/후 모두 처리가능
public class AroundAdvice {
	
	public Object aroundTest(ProceedingJoinPoint pjp) throws Throwable{
		// ProceedingJoinPoint는 JoinPoint를 상속해서 만들어진 객체
		String methodName = pjp.getSignature().getName();
		Object[] args = pjp.getArgs();
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		// 비즈니스로직을 수행하는 메소드(서비스의 메소드를 수행하는 메소드)
		// 비즈니스로직 수행결과를 Object 형태로 반환
		Object obj = pjp.proceed();
		stopWatch.stop();
		System.out.println(methodName + "() 메소드 수행시간 : " + stopWatch.getTotalTimeMillis() + "(ms)");
		
		return obj;
	}

}
