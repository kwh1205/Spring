package com.oracle.project.service;

import java.util.List;




import org.springframework.stereotype.Service;

import com.oracle.project.domain.Tour;
import com.oracle.project.repository.ProjectRepository;

@Service
public class ProjectSearchService implements ProjectService {
	private final ProjectRepository pr;
	
	public ProjectSearchService(ProjectRepository pr) {
		this.pr = pr;
	}



	@Override
	public List<Tour> search() {
		System.out.println("List<Tour> search start..");
		List<Tour> toursearch = null;
		toursearch = pr.search();
		System.out.println("toursearch.size()->"+toursearch.size());
		return toursearch;
	}

}
