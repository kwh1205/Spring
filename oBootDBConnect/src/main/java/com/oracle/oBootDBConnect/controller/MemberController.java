package com.oracle.oBootDBConnect.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.oracle.oBootDBConnect.domain.Member1;
import com.oracle.oBootDBConnect.service.MemberService;



@Controller
public class MemberController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	private final MemberService memberService;
	//MemberService memberservice = new MemberService()
//  생성자에 @Autowired 가 있으면 스프링이 연관된 객체를 스프링 컨테이너에서 찾아서 넣어줌
  //    객체 의존관계를 외부에서 넣어주는 것을 DI (Dependency Injection), 의존성 주입이라 함 , 3가지 인젝션이잇음 , 세터인젝션 ,컨스트록터인젝션, 필드인젝션 - 외부에서 의존성을주입해줌
  //    이전 테스트에서는 개발자가 직접 주입했고, 여기서는 @Autowired에 의해 스프링이 주입

  //    스프링 빈을 등록하는 2가지 방법
  //    컴포넌트 스캔과 자동 의존관계 설정
  //    자바 코드로 직접 스프링 빈 등록하기
	
  //    
  //    @Component 를 포함하는 다음 애노테이션도 스프링 빈으로 자동 등록된다.
  //    @Controller
  //    @Service
  //    @Repository

	//MemberService memberService = new MemberService(); 이렇게 호출했음 기존방법 서비스(커맨드) 부른다음 memberSerivce.메소드 이렇게 호출햇엇음 이걸 아래방법ㅇ로 바뀜
	@Autowired//컴퍼넌트 - 컨테이너 박스에 자동으로쌓임 - 메모리로 올라감
	public MemberController(MemberService memberService) {
		//MemberService memberservice = new MemberService()
		//이렇게안해도됀다는거 걍 컨트롤러 생성자에 매개변수로 -- 이건 생성자인젝션,나중에 세터인젝션 필드인젝션가능
		//멤버서비스를넣고 오토 와이드하면됌 -- di 의존성주입 --컴포넌트 - 컨테이너에 자동으로 메모리로 쌓이기때문에 이게 의존성주입임 외부에서 객채에 값넣어줌
		this.memberService = memberService;
	}
	
	@GetMapping(value= "/members/new")
	public String inputForm() {
		System.out.println("MemberController / members / new Start...");
		return "members/createMemberForm";
	}
	
	@PostMapping(value = "/members/new")//post - 저장,수정할때는 post
	public String save(Member1 form) {//보내줄때 input태그에 name의 값이 dto member1 의 구성요소와 이름이같다면 자동으로 할당할수있음 매개변수에 dto를넣으면 자동으로값가져옴
		Member1 member = new Member1();
		member.setName(form.getName());//이렇게해도됌
		memberService.join(member);//생성자인젝션으로 연결되서 인스턴스생성안해도 .을찎고 사용가능함 - 컴퍼넌트에 들어가있으니까 메모리로바로올라가잇어서
		return "redirect:/";
	}
	
	@GetMapping(value="/members")//nav - 화면왓다갓다할때는 겟매핑
	public String memberList(Model model) {
		logger.info("memberList start..");
		List<Member1> memberLists= memberService.allMembers();
		model.addAttribute("memberLists",memberLists);
		logger.info("memberLists.size()->{}",memberLists.size());
		
		return "members/memberList";
	}
}
