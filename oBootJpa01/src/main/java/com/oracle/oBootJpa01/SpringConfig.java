package com.oracle.oBootJpa01;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.oracle.oBootJpa01.repository.JpaMemberRepository;
import com.oracle.oBootJpa01.repository.MemberRepository;

@Configuration
public class SpringConfig {//여기부터 생성자까지가 이객체를 생성하는 과정임 (인스턴스생성 new 대신 사용)
	//레포지토리 대신 컨피그레이션(프로그램시작시 자동으로 제일먼저 실행되는곳) -
	private final DataSource dataSource;
	private final EntityManager em;
	
	public SpringConfig(DataSource dataSource, EntityManager em) {
		this.dataSource = dataSource;
		this.em = em;
	}
	//레포지토리 - 대신 빈사용해서 db연결객체를 바꿔서 쓸수있음 dao역활
	@Bean
	public MemberRepository memberRepository() {
		return new JpaMemberRepository(em);
	}
	
}
