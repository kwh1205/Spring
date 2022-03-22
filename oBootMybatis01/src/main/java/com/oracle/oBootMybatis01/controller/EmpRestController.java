package com.oracle.oBootMybatis01.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oracle.oBootMybatis01.model.Dept;
import com.oracle.oBootMybatis01.model.Emp;
import com.oracle.oBootMybatis01.model.SampleVO;
//@controller + responsebody - 내가실행한결과를 바디에넣기 viewresolver를 타지않음즉 jsp나 html페이지로 가지지않음 - 대신 json or String를 외부에서 호출
import com.oracle.oBootMybatis01.service.EmpService;

@RestController
public class EmpRestController {
	@Autowired
	private EmpService es;
	
	@RequestMapping("/helloText")
	public String helloText() {
		System.out.println("EmpRestController Start...");
		String hello="안녕";
		return hello; //jsp나 page로 안가짐 http메세지 컨버터가받아서 스트링 - 호출한놈한테 보내줌
	}
	
	@RequestMapping("/sample/sendVO2")
	public SampleVO sendVO2(int deptno) {
		System.out.println("@RestController deptno->"+deptno);
		SampleVO vo = new SampleVO();
		vo.setFirstName("길동");
		vo.setLastName("홍");
		vo.setMno(deptno);
		return vo;
	}
	
	@RequestMapping("/sendVO3")
	public List<Dept> sendVO3(){
		System.out.println("@RestController sendVO3 START");
		List<Dept> deptList = es.deptSelcet();
		return deptList;
	}
	
//	@RequestMapping("/empnoDelete")
//	public String empnoDelete(int empno, String ename){ //이렇게 각자받아도되지만 dto로받아도됌
//		System.out.println("@RestController empnoDelete START");
//		int delStatus = es.delete(empno);
//		String delStatusStr= Integer.toString(delStatus);
//		return delStatusStr;
//	}
	
	@RequestMapping("/empnoDelete")
	public String empnoDelete(Emp emp){//이렇게 값2개이상 받을땐 dto로 깔금하게 받기
		System.out.println("@RestController empnoDelete START");
		System.out.println("@RestController empnoDelete emp.getEname()->"+emp.getEname());
		int delStatus = es.delete(emp.getEmpno());
		String delStatusStr= Integer.toString(delStatus);
		return delStatusStr; //아작스에선 str이나 객체로밖에못보냄 - 파싱해서받음
	}
}
