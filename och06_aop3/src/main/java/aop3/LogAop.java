package aop3;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.hibernate.hql.internal.ast.util.JoinProcessor;

//aop - aspect이란?
@Aspect
public class LogAop {
	//pointcut - xml말고 어노테이션으로할때 위치지정
	@Pointcut("within(aop3.buz.*)")//aop3에있는 모든 메소드
	private void pointcutMethod() {	
	}
	
	@Around("pointcutMethod()")//바로위에있는 pointcut으로 지정해준 pointcutMethod()를 어라운드 방식으로 걸어줌
	public Object loggerAop(ProceedingJoinPoint joinpoint)throws Throwable{
		String signatureStr = joinpoint.getSignature().toShortString();
		System.out.println(signatureStr+"is start");
		long st = System.currentTimeMillis();
		
		try {
			Object obj = joinpoint.proceed();
			return obj;
		}finally {
			long et = System.currentTimeMillis();
			System.out.println(signatureStr+"is finished");
			System.out.println(signatureStr+"경과시간"+(et-st));
		}
	}
	
	@Before("within(aop3.buz.*)")
	public void beforeAdvice() {
		System.out.println("beforeAdvice() 실행");
	}
	@After("within(aop3.buz.*)")
	public void afterAdvice() {
		System.out.println("@AfterAdvice() 실헹");
	}
}
