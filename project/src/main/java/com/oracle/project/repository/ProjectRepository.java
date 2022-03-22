package com.oracle.project.repository;

import java.util.List;

import com.oracle.project.domain.Tour;

public interface ProjectRepository {

	List<Tour> search();

}
