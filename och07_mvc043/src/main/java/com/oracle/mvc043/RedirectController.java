package com.oracle.mvc043;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RedirectController {
	@RequestMapping("/studentConfirm")
	public String studentRedirect(HttpServletRequest request, Model model) {
		String id = request.getParameter("id");
		String pw = "1234";
		System.out.println("RedirectController studenConfirm start...");
		model.addAttribute("id",id);
		model.addAttribute("pw",pw);
		
		if(id.equals("abc")) {
			return "redirect:studentOk";
		}
		return "redirect:studentErr";
	}
	
	@RequestMapping("/studentOk")
	public String studentOk(HttpServletRequest request, Model model) {//서블렛의 request값을 매개변수로받음 - request.값받기 위함
		String id = request.getParameter("id");
		String password= request.getParameter("pw");
		System.out.println("RedirectController studentOk start...");
		System.out.println("paswword->"+password);
		model.addAttribute("id",id);
		model.addAttribute("password",password);
		return "student/studentOk";
	
	}
	@RequestMapping("/studentErr")
	public String studentErr(HttpServletRequest request, Model model) {//에러페이지- 파라미터값 필요x request없앰 받을때 서블렛의 request매개변수로
		String id = request.getParameter("id");
		System.out.println("RedirectController studentErr start...");
		model.addAttribute("id",id);
		return "student/studentErr";
		
	}
}
