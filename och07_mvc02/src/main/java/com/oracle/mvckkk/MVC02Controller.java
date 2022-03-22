package com.oracle.mvckkk;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MVC02Controller {
	private static final Logger logger = LoggerFactory.getLogger(MVC02Controller.class);
	
	
	@RequestMapping(value = "/board/view2") //핸들러 맵핑값은 - 알아서정하면됌
	public String view2() {
		logger.info("MVC02Controller logger /board/view2 start...");
		System.out.println("MVC02Controller sysout /board/view2 start...");
		
		return "/board/view2";
		
	}
	
	
	
}
