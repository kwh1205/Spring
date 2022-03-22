package com.oracle.oBootMybatis01.dao;

import java.util.List;

import com.oracle.oBootMybatis01.model.Member3;

public interface Member1Dao {
	int	memCount(String id);
	List<Member3> listMem(Member3 member3);
	
}
