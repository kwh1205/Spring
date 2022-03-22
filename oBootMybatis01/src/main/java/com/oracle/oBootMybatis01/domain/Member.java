package com.oracle.oBootMybatis01.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
//entity 를 dto에 하는이유 - 이걸로 바로 테이블을만들거나 없애거나 이런걸 다 이걸로함 테이블롷안함
@Entity
@Getter
@Setter
@Table(name = "member3")
public class Member {
	@Id
	private Long id;
	private String name;
	private String password;
	private Date reg_date; //데이트할땐 자바유틸 데이트사용 -
}
