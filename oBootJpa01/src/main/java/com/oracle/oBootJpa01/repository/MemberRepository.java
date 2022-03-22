package com.oracle.oBootJpa01.repository;

import java.util.List;

import com.oracle.oBootJpa01.domain.Member;

public interface MemberRepository {
	Member save(Member member);//인터페이스에서 추상메소드 만드는방법

	List<Member> findAll();

	List<Member> findByNames(String searchName);
	
}
