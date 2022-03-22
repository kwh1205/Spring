package com.oracle.oBootJpa01.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity//이거로 가지고다님 dto명은 테이블명
public class Member {
	//@Id -> db의 테이블의 프라이머리키임
	@Id
	private Long id;
	private String name;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
