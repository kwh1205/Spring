package com.oracle.oBootJpa02;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.oracle.oBootJpa02.repository.JpaMemberRepository;
import com.oracle.oBootJpa02.repository.MemberRepository;

@Configuration
public class SpringConfig {
	private final DataSource dataSource;
	private final EntityManager em;
	
	public SpringConfig(DataSource dataSource, EntityManager em) {
		this.dataSource = dataSource;
		this.em = em;
	}
	//빈으로 어떤 레포지터리를 사용할것인지 설정
	@Bean
	public MemberRepository memberRepository() {
		return new JpaMemberRepository(em);
	}
	
}
