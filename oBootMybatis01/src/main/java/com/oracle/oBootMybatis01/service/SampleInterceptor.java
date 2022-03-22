package com.oracle.oBootMybatis01.service;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;



public class SampleInterceptor implements HandlerInterceptor {
	public SampleInterceptor() {}//기본생성자
	//3번
	@Override
	public void postHandle(HttpServletRequest request,
						   HttpServletResponse response,
						   Object			handle,
						   ModelAndView modelAndView)throws Exception{//modelAndView - 모델에있는걸 꺼내기위해 사용
		System.out.println("post handle......");
		//컨트롤러에서 모델에넣은값을 불러옴
		String ID=(String)modelAndView.getModel().get("id");//모델에담겨있는것들을 꺼냄
		int memCnt = (Integer)modelAndView.getModel().get("memCnt");//모델(컨트롤러에서)담겨있는것들을 꺼냄
		System.out.println("SampleInterceptor post handle memCnt:"+memCnt);
		//만약 1보다작으면 즉 0 이면 아이디로그인페이지로
		if(memCnt < 1) {
			System.out.println("memCnt Not exists");
			request.getSession().setAttribute("ID", ID);
			//User가 존재하지 않으면 User interCeptor Page(회원등록) 이동
			response.sendRedirect("doMemberWrite");
		}else {	//정상 Login User 1이면 정상 리스트로
			System.out.println("memCnt exists");
			request.getSession().setAttribute("ID", ID);
			//User가 존재하면 User interCeptor Page(회원 List) 이동
			response.sendRedirect("doMemberList");
		}
	}
	//1번
	@Override
	public boolean preHandle(HttpServletRequest request,
				             HttpServletResponse response,
				             Object handler) throws Exception{
		System.out.println("pre handle...........");
		HandlerMethod method = (HandlerMethod) handler;
		Method methodObj = method.getMethod(); //앞쪽 메소드는 자바랭 메소드 뒤에껀 org 스프링 handlermethod임
		System.out.println("Bean : "+method.getBean());//누가시켰는지
		System.out.println("Method :"+methodObj);//메소드명을 알려줌
		return true;
	}
}
