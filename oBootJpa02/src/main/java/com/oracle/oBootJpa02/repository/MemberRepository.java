package com.oracle.oBootJpa02.repository;

import java.util.List;

import com.oracle.oBootJpa02.domain.Member;

public interface MemberRepository {
	Member save(Member member);
	List    <Member> findAll();
	Member findByMember(Long id);
	int   updateByMember(Member member);//update나 delete 할때는 int값으로 넣는게 좋다 - 나중을위하여 
}
