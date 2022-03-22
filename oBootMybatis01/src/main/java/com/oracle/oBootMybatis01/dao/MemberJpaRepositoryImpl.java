package com.oracle.oBootMybatis01.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.oracle.oBootMybatis01.domain.Member;
//여기서 레포지터리 지정하거나 컨피그레이션 파일만들어서 생성자 인젝션하거나
@Repository
public class MemberJpaRepositoryImpl implements MemberJpaRepository {

	private final EntityManager em;
	    
	public MemberJpaRepositoryImpl(EntityManager em) {
	    	this.em = em;
	}

	@Override
	public Member save(Member member) {
		em.persist(member);
		return member;
	}

	@Override
	public List<Member> findAll() {
		List<Member> memberList = em.createQuery("select m from Member m", Member.class)
                .getResultList();
		return memberList;
	}

	@Override
	public Optional<Member> findById(Long id) {
		Member member = em.find(Member.class, id);
		return Optional.ofNullable(member);
	}

	@Override
	public void updateByMember(Member member) {
		//em.persist(member);//버퍼에있는거면 이걸로가능 하지만 member가 새로들어올땐 머지로 수정	
		em.merge(member);	
		return;
	}

}
