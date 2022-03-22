package com.oracle.oBootDBConnect;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.oracle.oBootDBConnect.repository.JdbcMemberRepository;
import com.oracle.oBootDBConnect.repository.MemberRepository;
import com.oracle.oBootDBConnect.repository.MemoryMemberRepository;

//@레포지토리로 등록하던지 컨피그레이션 안의 빈의로 설정하던지 2중하나
@Configuration//프로그램실행시 최우선으로 실행되는것
public class SpringConfig {
	private final DataSource dataSource;//파이널일경우 생성자에 무조건넣어야함
	public SpringConfig(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Bean//컨피그레이션 설정시 자동으로 끌고가짐
	//@레포지토리로 등록하던지 컨피그레이션 안의 빈의로 설정하던지 2중하나
	public MemberRepository memberRepository() {
		//return new MemoryMemberRepository();   
		return new JdbcMemberRepository(dataSource);
	}

	
}
