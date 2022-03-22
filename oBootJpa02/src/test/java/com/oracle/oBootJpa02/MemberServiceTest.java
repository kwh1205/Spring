package com.oracle.oBootJpa02;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
/* @RunWith(SpringRunner.class) : 스프링과 테스트 통합
   @SpringBootTest : 스프링 부트 띄우고 테스트(이게 없으면 @Autowired 다 실패)
   @Transactional : 반복 가능한 테스트 지원, 각각의 테스트를 실행할 때마다 트랜잭션을 시작하고 
      테스트가 끝나면 트랜잭션을 강제로 롤백 (이 어노테이션이 테스트 케이스에서 사용될 때만 롤백)
*/

import com.oracle.oBootJpa02.domain.Member;
import com.oracle.oBootJpa02.domain.Team;
import com.oracle.oBootJpa02.repository.MemberRepository;
import com.oracle.oBootJpa02.service.MemberService;

@SpringBootTest
@Transactional
public class MemberServiceTest {
	@Autowired
	MemberService memberService;
	@Autowired
	MemberRepository memberRepository;
	
	@BeforeEach
	public void before1() {
		System.out.println("Test Before1 Start...");
	}
//	@After
//	public void after1() {
//		System.out.println("Test after1 Start...");
//	}
	
	@Test
	//@Rollback(value = false) - 테스트에서 커밋하는법
	public void memberSave() throws Exception {
		//3개생각 - 조건
		//팀저장
		Member member = new Member();
		member.setTeamname("1조");
		member.setName("조병훈");
		//수행
		Member member3= memberService.join(member);//멤버서비스의 조인메소드가 리턴값이있어서 리턴값이 멤버타입이니까 받을려면 이렇게받아야함
		//결과
		System.out.println("MemberServiceTest memberSave member3.getId()->"+member3.getId());
		System.out.println("MemberServiceTest memberSave member3.getName()->"+member3.getName());
		System.out.println("MemberServiceTest memberSave member3.getTeam().getName()->"+member3.getTeam().getName());
	}
}
