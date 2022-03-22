package com.oracle.oBootHello.repository;

import java.util.List;

import com.oracle.oBootHello.domain.Member1;

public interface MemberRepository {
	Member1 save(Member1 member1);//파라미터값으로 멤버1에 메모리로저장 db가아님
	List<Member1> findAll();
}
