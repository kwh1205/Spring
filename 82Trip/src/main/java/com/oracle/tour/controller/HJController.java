package com.oracle.tour.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oracle.tour.service.HJService;

@Controller
public class HJController {
	private static final Logger logger = LoggerFactory.getLogger(HJController.class);
	private final HJService hjService;
	@Autowired
	public HJController(HJService hjService) {
		this.hjService = hjService;
	}	
	@RequestMapping("/Board")
	public String Board() {
		logger.info("Board start");
		
		return "HJview/Board";
	}
	
	@RequestMapping("/QnaBoard")
	public String QnaBoard() {
		logger.info("Board start");
		
		return "HJview/QnaBoard";
	}
}
