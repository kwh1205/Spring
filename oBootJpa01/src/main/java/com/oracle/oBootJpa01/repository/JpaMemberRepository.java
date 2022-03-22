package com.oracle.oBootJpa01.repository;

import java.util.List;

import javax.persistence.EntityManager;

import com.oracle.oBootJpa01.domain.Member;

//@Repository 로 지정해도되지만 컨피그파일로 설정 - 컨피그파일은 보통 기본패키지에다가넣음
public class JpaMemberRepository implements MemberRepository {
	private final EntityManager em; //jdbc - datasource/jpa - entitymanager!!
	//객체생성하는법임 dao(레포디토리)쪽에서는 외부객체를 알아서 찾게할필요x 애초애 여기서 쓰는 엔티티매니저는 스프링에 등록되어있는거기때문에 자동으로 찾아줌
	public JpaMemberRepository(EntityManager em) {
		this.em = em;
	}
	//이게 원래 dao에서 sql문으로 db랑연결해서 저장하는방법을 jpa에서 알아서 처리하게한것
	@Override
	public Member save(Member member) {
		//jpa에서의 저장 persist();
		em.persist(member);
		return member;
	}
	//이거도원래 리스트형식으로 dao에서 db연결객체만들고 그안에서 while문으로 rs값을 dto에넣어주고 그 dto리스트값을 리턴하는것을 jpa에서하는법
	@Override
	public List<Member> findAll() {								//이멤버는 db멤버가아님 - 클래스Member임 , a=member클래스를 a로받겠다라는뜻
		List<Member> memberList = em.createQuery("select a from Member a",Member.class).getResultList();//리스트방식- 여러개의 값받을땐 겟리슐트리스트사용
		
		return memberList;
	}
	@Override
	public List<Member> findByNames(String searchName) {
		String pname=searchName+'%';
		System.out.println("JpaMemberRepository findByNames name->"+searchName);
																					//:name = ?이거랑똑같음 name에다가 - pname을집어넣음)
		List<Member> memberList = em.createQuery("select m from Member m where name Like:name",Member.class)
									.setParameter("name", pname).getResultList();
		System.out.println("JpaMemberRepository memberList.size()->"+memberList.size());
		return memberList;
	}

}
