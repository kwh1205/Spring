package com.oracle.oBootJpa02.repository;

import java.util.List;

import javax.persistence.EntityManager;

import com.oracle.oBootJpa02.domain.Member;
import com.oracle.oBootJpa02.domain.Team;

public class JpaMemberRepository implements MemberRepository {
	private final EntityManager em; //파이널상수는 생성자로 값초기화시켜줘야함
	
	
	public JpaMemberRepository(EntityManager em) {
		this.em = em;
	}


	@Override
	public Member save(Member member) {
		//팀저장
		Team team = new Team();
		team.setName(member.getTeamname());
		em.persist(team);//team_id는 제네레이트 시퀀스로 자동으로 생성
		//회원 저장
		member.setTeam(team); // 다대일,단방향 연관관계 설정, 참조 저장
		em.persist(member);
		return member;
	}


	@Override
	public List<Member> findAll() {						//m을      클래스멤버임 db멤버가아님	 //멤버.class 클래스타입으로 가져옴
		List<Member> memberList = em.createQuery("select m from Member m",Member.class).getResultList();
		return memberList;
	}


	@Override
	public Member findByMember(Long id) {
		//			             entity 타입 - dto ,pk - 알아서 맞춰줌
		Member member = em.find(Member.class, id);
		return member;
	}


	@Override
	public int updateByMember(Member member) {//변한후의값
		int result = 0;
		System.out.println("JpaMemberRepository updateByMember member.getId()->"+member.getId());
		Member member3 = em.find(Member.class, member.getId());//변하기전의 값 기존의값
		if(member3 !=null) {
			//팀저장
			Team team = em.find(Team.class, member.getTeamid());
			if(team !=null) {
				System.out.println("JpaMemberRepository updateByMember member.getTeamid()->"+member.getTeamid());
				team.setName(member.getTeamname());//팀에 바뀐값을 넣어줌
				em.persist(team);//바뀐값 db에 업데이트/저장
			}
			//회원저장
			System.out.println("JpaMemberRepository updateByMember member.getName()->"+member.getName());
			member3.setTeam(team);//단방향 연관관계설정, 참조저장 -- 다대일 관계일때 일부터 저장하고 다쪽을 일을 참조해서 저장가능 - 이유는 일쪽이 - 다쪽에 외래키로지정됌
			member3.setName(member.getName());//단방향 연관관계 설정 ,참조저장
			em.persist(member3);
			System.out.println("JpaMemberRepository updataByMember member.getName()->"+member.getName());
			result=1;
		
		}else {
			result=0;
			System.out.println("JpaMemberRepository updataByMember No Exist..");
		
		}
		return result;
		
			
	}

}
