package com.oracle.project.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.oracle.project.domain.Tour;
import com.oracle.project.repository.ProjectRepository;
import com.oracle.project.service.ProjectService;

@Controller
public class ProjectController {
	private final ProjectService projectService;
	


	public ProjectController(ProjectService projectService) {
		this.projectService = projectService;
	}



	@GetMapping(value = "/search")
	public String search(Model model) {
		System.out.println("search Controller start..");
		List<Tour> tours=projectService.search();
		model.addAttribute("tours",tours);
		return "/home";
	}
	

}
