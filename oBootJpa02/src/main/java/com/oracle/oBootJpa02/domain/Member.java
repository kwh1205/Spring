package com.oracle.oBootJpa02.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;
//getter,setter을 롬북에서 제공해주기때문에 @getter @setter 하면  자동으로 내부적으로만듬
//테이블도 프로그램시작할때 자동으로 만들어짐--entity,table을 적어놨으면
@Entity
@Getter
@Setter
@SequenceGenerator(name = "member_seq_gen",//내가 실질적으로 여기서 사용할 이름
				   sequenceName = "member_seq_generator",//매핑할db에서 쓸 시퀀스이름
				   initialValue = 1,//시작번호
				   allocationSize = 1//한번에증가할번호 즉 1씩증가함
		            )
@Table(name = "member1")
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,//strategy는 전략은 제네레이션타입 시퀀스를 쓸거다 라는뜻
					generator = "member_seq_gen"//제네레이터는 어떤 시퀀스 사용할거냐? 내가 위에서 적용한 name시퀀스를 쓰겟다
			)
	@Column(name = "member_id",precision = 10)
	private Long id; //length는 바이트단위 , 스트링타입에만적용 , 여기에 시퀀스적용 = 제네레이터벨류로
	@Column(name = "user_name",length = 50)
	private String name;
	
	//fk 외래키 지정 연관관계 맵핑 다대일/다수인쪽에다가 잡아줘야함(멤버(다)-팀(일))이렇게
	//joincolumn --조인 외래키로 지정할거임 하는거 폴인키있는곳에서 매니투원 걸어줌
	@ManyToOne
	@JoinColumn(name = "team_id")
	private Team team;
	
	@Transient
	private String teamname;
	@Transient
	private Long teamid;
	
}
