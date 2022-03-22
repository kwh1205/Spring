package com.oracle.oBootDBConnect.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import com.oracle.oBootDBConnect.domain.Member1;
import com.oracle.oBootDBConnect.repository.MemberRepository;


//컴퍼넌트 - 탐캣서버안에 컨테이너안에자동으로 프로그램이시작핼때 들어감
@Service
public class MemberService {
	private final MemberRepository memberRepository;//인터페이스를 생성자 인젝션하면 원래는 그 자식클래스를 객체로 생성하지않는이상 못찾지만 - @레파지토리를하면 알아서 찾아감
	//MemberRepository memberRepository = new MemoryMemberRepository();==@Autowired랑같음
	@Autowired//서비스와 dao를 연결해줌 인스턴스를안만들어도 @레포지토리가잇는 메모리 멤버 레포지터리를 여기서 생성자 인젝션을시켜줌
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}
	
	//회원가입
	public Long join(Member1 member1) {
		System.out.println("MemberService join start...");
		memberRepository.save(member1);
		return member1.getId();
	}

	public List<Member1> allMembers() {
		System.out.println("MemberService allMembers start...");
		List<Member1> memList = null;
		memList=memberRepository.findAll();
		System.out.println("memList.size()->"+memList.size());
		return memList;
	}

}
