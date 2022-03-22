package aop2;

import org.aspectj.lang.ProceedingJoinPoint;

public class LogAop {
	//Around Advice에서 사용할 공통기능 메서드는,
	//대부분 파라미터로 전달받은 ProceedingJoinPoint의 proceed() 메서드만 호출
	//횡단관심사에 어라운드로 사용할려면  ProceedingJoinPoint joinpoint이걸 매개변수로 넣어야됌
	//proxy(복덕방) 연계해줌 역활을함
	public Object loggerAop(ProceedingJoinPoint joinpoint) throws Throwable {
		String signatureStr = joinpoint.getSignature().toString();
		//signatureStr = 핵심관심사의 수행 메소드
		System.out.println(signatureStr+"is start");
		long st = System.currentTimeMillis();
		
		try {
			//핵심관심사 수행
			Object obj = joinpoint.proceed();
			return obj;
		} finally {
			long et = System.currentTimeMillis();
			System.out.println(signatureStr+"is finished");
			System.out.println(signatureStr+"경과시간: "+(et-st));
		}
		
		
	}
	
	
	public void beforeAdvice() {
		System.out.println("beforeAdvice() 실행");
	}
	
	public void afterReturningAdvice() {
		System.out.println("afterReturningAdvice() 실행");
	}
	
	public void afterThrowingAdvice() {
		System.out.println("afterThrowingAdvice() 실행");
	}
	
	public void afterAdvice() {
		System.out.println("afterAdvice() 실행");
	}
}
