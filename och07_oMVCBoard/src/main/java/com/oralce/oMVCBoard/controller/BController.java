package com.oralce.oMVCBoard.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.oralce.oMVCBoard.command.BCommand;
import com.oralce.oMVCBoard.command.BContentCommand;
import com.oralce.oMVCBoard.command.BDeleteCommand;
import com.oralce.oMVCBoard.command.BModifyCommand;
import com.oralce.oMVCBoard.command.BReplyCommand;
import com.oralce.oMVCBoard.command.BReplyViewCommand;
import com.oralce.oMVCBoard.command.BWriteCommand;
import com.oralce.oMVCBoard.command.BlistCommand;


@Controller
public class BController {
	private static final Logger logger = LoggerFactory.getLogger(BController.class);
	BCommand command = null;
	
	
	@RequestMapping("/list")
	public String list(Model model) {
		logger.info("list start...");
		command = new BlistCommand();
		command.execute(model);
		return "list";
	}
	
	@RequestMapping("content_view")
	public String content_view(HttpServletRequest request,Model model) {
		System.out.println("content_view()");
		
		model.addAttribute("request",request);//모델에 리퀘스트담아서 커맨드로보냄
		command = new BContentCommand();
		command.execute(model);
		
		return "content_view";
	
	}
	
	@RequestMapping(value= "/modify" ,method = RequestMethod.POST)
	public String update(HttpServletRequest request,Model model) {
		logger.info("modify start...");
		model.addAttribute("request",request);
		command= new BModifyCommand();
		command.execute(model);
		
		return "redirect:list";
		
	}
	
	@RequestMapping("/write_view")
	public String write_view(Model model) {
		logger.info("write_view strat..");
		return "write_view";
	}
	
	//@RequestMapping(value ="/write",method=RequestMethod.POST)
	@PostMapping(value="/write")//post방식일시 이렇게 받아와도됌
	public String write(HttpServletRequest request,Model model) {
		logger.info("write start...");
		
		model.addAttribute("request",request);
		command= new BWriteCommand();
		command.execute(model);
		return "redirect:list"; //redirect 는 같은컨트롤러 내에서 이동할때 사용
	}
	
	@RequestMapping("/reply_view")
	public String reply_view(HttpServletRequest request,Model model) {
		System.out.println("reply_view start..");
		
		model.addAttribute("request",request);
		command = new BReplyViewCommand();
		command.execute(model);
		
		return "reply_view";
	}
	
	@RequestMapping(value="/reply",method=RequestMethod.POST)
	public String reply(HttpServletRequest request,Model model) {
		System.out.println("reply start..");
		
		model.addAttribute("request",request);
		command = new BReplyCommand();
		command.execute(model);
		
		return "redirect:list";
	}
	
	@RequestMapping(value = "/delete")
	public String delete(HttpServletRequest request,Model model) {
		System.out.println("delete()실행중");
		
		model.addAttribute("request", request);
		command = new BDeleteCommand();
		command.execute(model);
		
		return "redirect:list";
	}
}
