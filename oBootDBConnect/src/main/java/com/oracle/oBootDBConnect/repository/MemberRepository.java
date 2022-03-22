package com.oracle.oBootDBConnect.repository;

import java.util.List;

import com.oracle.oBootDBConnect.domain.Member1;



public interface MemberRepository {//인터페이스로만든이유 dao를 같은메소드를 받아서 다른식으로 재정의하면 같은 메소드지만 여러가지 연결을할수있음
	Member1 save(Member1 member1);//파라미터값으로 멤버1에 메모리로저장 db가아님
	List<Member1> findAll();
}
