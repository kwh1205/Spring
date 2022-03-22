package com.oracle.oBootApi01.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oracle.oBootApi01.domain.Member;
import com.oracle.oBootApi01.repository.MemberRepository;

@Service
@Transactional
public class MemberService {
	private final MemberRepository memberRepository;

	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}
	
	//회원가입
	public Long join(Member member) {
		System.out.println("MemberService join member.getName()->"+member.getName());
		Long id = memberRepository.save(member);//JpaMemberRepository에서 save함수가 id를 리턴하기때문에 값받기
		return id;
	}
	
	//전체회원 조회
	public List<Member> getListAllMember(){
		List<Member> listMember = memberRepository.findAll();//jpaMemberRepository에서 finaAll함수가 리턴값으로 리스트를넘겨주니까 이걸 서비스에서받아서 컨트롤러로넘김
		System.out.println("MemberService GetListAllMember listMember.size() ->"+listMember.size());
		return listMember;
	}
	
}
