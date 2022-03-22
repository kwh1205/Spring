package com.oracle.oBootJpa01.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
//트랜젝션 - 원자성 , 일관성, 지속성 ,고유성 -- 하나의 단위로 처리되는것 - 트랜잭션 한번에 !!커밋 롤백됌

import com.oracle.oBootJpa01.domain.Member;
import com.oracle.oBootJpa01.repository.MemberRepository;

@Service
@Transactional
public class MemberService {
	private final MemberRepository memberRepository;
	//내가만든 dao객체를 찾기위해서 오토와이어드
	@Autowired
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}
	
	//회원가입
	public Long join(Member member) {
		System.out.println("MemberService join member.getId()->"+member.getId());
		memberRepository.save(member);
		return member.getId();
	}

	public List<Member> getListAllMember() {
		List<Member> listMember = memberRepository.findAll();
		System.out.println("MemberService getListAllMember listMember.size()->"+listMember.size());
		
		return listMember;
	}
	//전체회원 조건 조회
	public  List<Member> getListSearchMember(String searchName) {
		System.out.println("MemberService getListSearchMember Start...");
		//String pSearchName = searchName +'%';
		System.out.println("MemberService getListSearchMember searchName->"+searchName);
		List<Member> listMember = memberRepository.findByNames(searchName);
		System.out.println("MemberService getListSearchMember listMember.size()->"+listMember.size());
		
		return listMember;
	}
}
































