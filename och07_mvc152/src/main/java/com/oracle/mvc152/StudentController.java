package com.oracle.mvc152;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StudentController {
	@RequestMapping("/studentForm")
	public String studentForm() {
		System.out.println("Controller studentForm 실행");
		return "createPage";
	}
	
	@RequestMapping("/student/create")
	public String studentCreate(@ModelAttribute("student") Student student, 	
			                                              BindingResult result) {
		//
		String page = "createDonePage";
		
		StudentValidator validator = new StudentValidator();
		validator.validate(student, result);
		if(result.hasErrors()) {//error에 값이있으면 참 걸림
			System.out.println("StudentValidator 오류 발생");
			page="createPage";
		}
		return page;
	}
}
