package com.oracle.oBootMybatis01.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.oracle.oBootMybatis01.service.SampleInterceptor;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {//mvc 컨피그레이션 만들때는 스프링에서 기본제공해주는 인터페이스 webmvcconfigurer를 상속받아서 클래스생성
	//interceptor						//registry = 인터셉터 레기스트리인데 인터셉터관련것들을 저장함
	public void addInterceptors(InterceptorRegistry registry) {//InterceptorRegistry -- 스프링에서 제공해주는 인터셉터관련메소드
		//url에 interCeptor이라고 들어오면 이 메소드가 registry에서 인터셉터함(가로챔)
									//레기스트리(저장소)에 클래스 SampleInterceptor - 여기서 패턴으로 인터셉터가들어오면 먼저 가로채서 실행
		registry.addInterceptor(new SampleInterceptor()).addPathPatterns("/interCeptor");
														//.addPathPatterns("kkk").addPathPatterns("controller")
	}

}
