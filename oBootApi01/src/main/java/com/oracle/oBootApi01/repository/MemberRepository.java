package com.oracle.oBootApi01.repository;

import java.util.List;

import com.oracle.oBootApi01.domain.Member;


public interface MemberRepository {
	Long save(Member member);
	List<Member> findAll();
	
	
}
