package com.oracle.oBootApi01.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.oracle.oBootApi01.domain.Member;


@Repository
public class JpaMemberRepository implements MemberRepository {
	private final EntityManager em;//파이널로 필드에 입력시 반드시 생성자로값초기화해야함
	
	
	
	public JpaMemberRepository(EntityManager em) {//생성자로 값초기화시 값이 더이상 변하지않게함(db연결을 여러타입으로하고 생성자값초기화만 다르게하면됌, 세터 게터로받을시는 값이 변경사항이있을때
		this.em = em;
	}

	@Override
	public Long save(Member member) {
		//회원 저장
		em.persist(member);
		return member.getId();
	}

	@Override
	public List<Member> findAll() {
		List<Member> memberList = em.createQuery("select m from Member m",Member.class).getResultList();
		return memberList;
	}

}
