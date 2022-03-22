package com.oracle.oBootMybatis01.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.oBootMybatis01.dao.DeptDao;
import com.oracle.oBootMybatis01.dao.EmpDao;
import com.oracle.oBootMybatis01.dao.Member1Dao;
import com.oracle.oBootMybatis01.model.Dept;
import com.oracle.oBootMybatis01.model.DeptVO;
import com.oracle.oBootMybatis01.model.Emp;
import com.oracle.oBootMybatis01.model.EmpDept;
import com.oracle.oBootMybatis01.model.Member3;

@Service
public class EmpServiceImpl implements EmpService {
	@Autowired
	private EmpDao ed;
	@Autowired
	private DeptDao dd;
	@Autowired
	private Member1Dao md;
	
	@Override
	public int total() {
		System.out.println("EmpServiceImpl Start total...");
		int totCnt = ed.total();
		System.out.println("EmpServiceImpl total totCnt->"+totCnt);
		return totCnt;
	}

	@Override
	public List<Emp> listEmp(Emp emp) {
		List<Emp> empList = null;
		System.out.println("EmpServiceImpl listEmp start...");
		empList=ed.listEmp(emp);
		System.out.println("EmpServiceImpl listEmp empList.size()->"+empList.size());
		return empList;
	}

	@Override
	public Emp detail(int empno) {
		Emp emp = null;
		System.out.println("EmpserviceImpl detail start..");
		emp=ed.datail(empno);
	//	System.out.println("EmpServiceImpl detail emp.getEname()->"+emp.getEname());
		return emp;
	}

	@Override
	public int update(Emp emp) {
		System.out.println("EmpServiceImpl update start..");
		int k = 0; //업데이트개수 확인을위한 return값 int
		k =ed.update(emp);
		return k;
	}

	@Override
	public List<Emp> listManager() {
		//empList = ed.listManager();
		//Dao -> empList = session.selectList("tkSelect
		System.out.println("EmpServiceImpl listManager start...");
		List<Emp> empList = null;
		empList =ed.listManager();
		return empList;
	}

	@Override
	public List<Dept> deptSelcet() {
		System.out.println("EmpServiceImpl deptSelect start...");
		List<Dept> deptList = null;
		deptList = dd.deptSelect();
		return deptList;
	}

	@Override
	public int insert(Emp emp) {
		int result=0;
		System.out.println("EmpServiceImpl insert start...");
		result = ed.insert(emp);
		return result;
	}

	@Override
	public int delete(int empno) {
		System.out.println("EmpServiceImpl delete start..");
		int result = 0;
		result = ed.delete(empno);
		return result;
	}

	@Override
	public List<EmpDept> listEmpDept() {
		System.out.println("EmpServiceImpl listEmpDept start..");
		List<EmpDept> listEmpDept = null;
		listEmpDept = ed.listEmpDept();
		return listEmpDept;
	}

	@Override
	public void insertDept(DeptVO deptVO) {
		System.out.println("EmpServiceImpl insertDept start..");
		dd.insertDept(deptVO);
		
	}

	@Override
	public void selListDept(HashMap<String, Object> map) {
		System.out.println("EmpServiceImpl selListDept start...");
		dd.selListDept(map);
		
	}

	@Override
	public int memCount(String id) {
		System.out.println("EmpServiceImpl memCount id->"+id);
		return md.memCount(id);
	}

	@Override
	public List<Member3> listMem(Member3 member3) {
		System.out.println("EmpServiceImpl listMem Start..");
		return md.listMem(member3);
	}

	@Override
	public List<EmpDept> listEmp(EmpDept empDept) {
		System.out.println("EmpServiceImpl listEmp start..");
		return ed.listEmp(empDept);
	}

	@Override
	public String deptName(int deptno) {
		System.out.println("EmpServiceImpl deptName start");
		return ed.deptName(deptno);
	}


	

}
