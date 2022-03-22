package com.oracle.tour.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oracle.tour.dao.HJDao;

@Service
@Transactional
public class HJServiceImpl implements HJService {
	private final HJDao hjDao;
	@Autowired
	public HJServiceImpl(HJDao hjDao) {
		this.hjDao = hjDao;
	}
	

}
