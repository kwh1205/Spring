package com.oracle.oBootJpa02.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.fasterxml.jackson.annotation.JacksonInject.Value;
import com.oracle.oBootJpa02.domain.Member;
import com.oracle.oBootJpa02.service.MemberService;

import lombok.val;


@Controller
public class MemberController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	private final MemberService memberService;///연결객체를 뉴로만드는게아니라 생성자로만듦 파이널상수로만들고
	@Autowired
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}



	@GetMapping("/home")
	public String home() {
		System.out.println("MemberController / Start...");
		return "redirect:/";
	}
	
	@GetMapping(value = "/members/new")
	public String createForm() {
		System.out.println("MemberController / members/new start...");
		return "members/createMemberForm";
	}
	
	@PostMapping(value = "/members/save")
	public String create(Member member) {//매개변수로 Member을 넣으면 html에서 form에잇는 파라미터들의 name이 member의 name과같으면 자동으로 값을 받아서 넣어줌
		System.out.println("MemberController create start...");
		System.out.println("member.getTeamname()->"+member.getTeamname());
		System.out.println("member.getName()->"+member.getName());
		memberService.join(member);
		
		return "redirect:/home";
	}
	
	@GetMapping(value = "/members")
	public String listMembers(Model model){
		List<Member> memberList = memberService.getListAllMember();
		System.out.println("memberList.get(0).getName(0)->"+memberList.get(0).getName());
		System.out.println("memberList.get(0).getTeam(0)->"+memberList.get(0).getTeam().getName());
		model.addAttribute("members",memberList);
		return "members/memberList";
	}
	
	@GetMapping(value = "/memberModifyForm")
	public String memberModifyForm(Long id, Model model){
		System.out.println("MemberController memberModify id->"+id);
		Member member = memberService.findByMember(id);
		model.addAttribute("member",member);
		System.out.println("member.get().getId()->"+member.getId());
		System.out.println("member.get().getName()->"+member.getName());
		System.out.println("member.get().getTeam()->"+member.getTeam());
		
		return "members/memberModify";
	}
	
	@GetMapping(value = "/members/memberUpdate")
	public String memberUpdate(Member member, Model model) {
		System.out.println("MemberController memberUpdate id->"+member.getId());
		System.out.println("MemberController memberUpdate member.getName->"+member.getName());
		System.out.println("MemberController memberUpdate member.getTeamname->"+member.getTeamname());
		memberService.memberUpdate(member);
		return "redirect:/members";
	}
}
