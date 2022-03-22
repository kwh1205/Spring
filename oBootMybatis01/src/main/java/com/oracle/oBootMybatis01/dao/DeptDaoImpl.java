package com.oracle.oBootMybatis01.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.oracle.oBootMybatis01.model.Dept;
import com.oracle.oBootMybatis01.model.DeptVO;

@Repository
public class DeptDaoImpl implements DeptDao {
	@Autowired
	private SqlSession	session;
	
	@Override
	public List<Dept> deptSelect() {
		System.out.println("DeptDaoImpl deptselect start..");
		List<Dept> deptList = null;
		deptList = session.selectList("TKselectDept");//sql문에 내가 값을넣어줘야하면 콤마를붙히고 넣을값세팅
													  //단순하게 모든값조회할때는 필요없음
		System.out.println("DeptDaoImpl deptSelect deptList.size()->"+deptList.size());
		return deptList;
	}

	@Override
	public void insertDept(DeptVO deptVO) {
		System.out.println("DeptDaoImpl isertDept start");
		System.out.println("DeptDaoImpl writeDeptIn deptVO.getDeptno()->"+deptVO.getDeptno());
		System.out.println("DeptDaoImpl writeDeptIn deptVO.getDname()->"+deptVO.getDname());
		session.selectOne("ProcDept",deptVO);
		
	}

	@Override
	public void selListDept(HashMap<String, Object> map) {
		System.out.println("DeptDaoImpl selListDept Start...");
		session.selectOne("ProcDeptList",map);
		
		
	}

}
