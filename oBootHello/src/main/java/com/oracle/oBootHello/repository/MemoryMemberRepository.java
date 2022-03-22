package com.oracle.oBootHello.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.oracle.oBootHello.domain.Member1;

@Repository
public class MemoryMemberRepository implements MemberRepository {
	private static Map<Long, Member1> store = new HashMap<>();
	private static long sequence = 0L;//long값에 0을넣음 오라클 시퀀스라고생각
	
	@Override
	public Member1 save(Member1 member1) {//메모리로저장
		System.out.println("MemortMemberRepository start...");
		member1.setId(++sequence);
		store.put(member1.getId(), member1);//멤버1의 아이디를 키로 값은 멤버1자체
		return member1;
	}

	@Override
	public List<Member1> findAll() {
		System.out.println("MemoryMemberRepository findAll start...");
		//store의 value(Member1) 위쪽보면나옴
		List<Member1> listMember = new ArrayList<>(store.values());
		System.out.println("MemoryMemberRepository findAll slistMember.size()->"+listMember.size());
		return listMember;
	}

}
