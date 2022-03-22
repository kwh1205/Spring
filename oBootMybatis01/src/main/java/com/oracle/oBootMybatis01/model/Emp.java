package com.oracle.oBootMybatis01.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Emp {
	private int empno;  	 private String ename;
	private String job; 	 private int mgr;
	private String hiredate; private int sal;
	private int comm;    	 private int deptno;

	//조회용 실제테이블에는없음 dto에만있는거
	private String search;   private String keyword;
	private String pageNum;  
	private int start;		 private int end;
}
