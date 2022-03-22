package com.oracle.oBootMybatis01.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Member3 {
	private String id; 		 
	private String password;
	//not used
	private String name;		 
	private Date   reg_date;

}
