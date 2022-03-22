package com.oracle.oBootApi01.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
//Controller+ResponseBody =뷰리절브를안탐 즉 - 자동으로 jsp html로 가는걸안탐 - 대신 HttpMessageConverter를 탐 -객체일시 JsonConverter 스트링일시 StringConverter를탐

import com.oracle.oBootApi01.domain.Member;
import com.oracle.oBootApi01.service.MemberService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
@RestController
@RequiredArgsConstructor
//RequiredAgrsConstrucort - 멤버변수를 가진 생성자를 자동으로 만들어줌 - 롬북
public class JpaRestApiController {
	private final MemberService memberService;

//	public JpaRestApiController(MemberService memberService) {
//		this.memberService = memberService;
//	}
	//Bad Api임 원래 컨트롤러에선 - 스트링타입으로 리턴해서 페이지로가게했는데 restcontroller에선 리턴값을 모델에넣는게아니고 그대로 리턴해줌
	//http://jsonviewer.stack.hu/

	@GetMapping("/restApi/v1/members")
	public List<Member> membersV1(){
		System.out.println("JapRestApiController/restApi/v1/members start...");
		return memberService.getListAllMember();
	}
	
	//Good Api
	//http://jsonviewer.stack.hu/
	@GetMapping("/restApi/v2/members")
	public Result membersV2() {
		List<Member> findMembers = memberService.getListAllMember();
		//자바 8 에서 추가한 스트림(Streams)은 람다를 활용할수 있는 기술중 하나 리스트형식의 파인드멤버안에있는 여러가지를 계속돌림 - 새로운객체 rtndto로 각각 원하는거만 뽑아냄
		List<MemberRtnDto> memberCollect = findMembers.stream()//stream - while과같은 역할 - 람다식에서 사용하는 반복문 .stream
							.map(m-> new MemberRtnDto(m.getId(),m.getName()))//파인드멤버의 값들중 rtndto의 이름으로만 받아서 여러개 객체생성 한후 컬렉터로 통합
								//여기서 m은 db에서받아온값을 서비스에서 리턴한 리스트형식의 멤버값임	
							.collect(Collectors.toList());//그 각자의 객체를 하나의 리스트로 만드는과정
		return new Result(memberCollect.size(),memberCollect);
		//return new Result(memberCollect);
	}
	
	// 1. Entity보안
	// 2. 유연성 --> Entity가 API에 의존적 X, 원하는 Data 생성 , 전달 
	// T는 인스턴스를 생성할 때 구체적인 타입으로 변경
	
	@Data
	@AllArgsConstructor
	class Result<T>{//<T>는 타입을 알아서 찾아서해줌 -  클래스리턴할시 유용함
		private int totCount;//t는 사이즈니까 - 몇개인지
		private T date;//date타입은 T이니까 들어온 date값으로 자동세팅 - MemberRtnDto를 리스트형식의 memberCollect에 값을넣어준걸 받아서 그 타입으로넣어줌
	}
	@Data
	@AllArgsConstructor
	class MemberRtnDto{
		private Long id;
		private String name;
	}

}
