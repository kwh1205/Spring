package com.oracle.oBootMybatis01.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.oracle.oBootMybatis01.model.Emp;
import com.oracle.oBootMybatis01.model.EmpDept;
//빈으로등록 - 컴포넌트
@Repository
public class EmpDaoImpl implements EmpDao {
	//필드인젝션 연결하는법임 마이바티스 연결객체를 오토와일드로 잡아줌
	@Autowired
	private SqlSession session;//매퍼에서 설정한값을 가져오기위해 마이바티스에선 sqlsession사용
	
	@Override
	public int total() {
		int tot=0;
		System.out.println("EmpDaoImp1 Start total..");
		
		//Result => 14
		try {			//하나의 행을받을때 셀렉트원 여러개를받을때 셀렉트리스트
			tot=session.selectOne("tKEmpTotal");//맵퍼즈안의 하나의값을가져옴 id가 저거인걸로
						//매퍼에서 적은 sql문을 가져오는것 거기서 설정한 id와 리턴값을가지고
				//session.
			System.out.println("EmpDaoImp1 total tot->"+tot);
		
		}catch(Exception e){
			System.out.println("EmpDaoImp1 total Exception ->"+e.getMessage());
		}
		return tot;
		}

	@Override
	public List<Emp> listEmp(Emp emp) {
		List<Emp> empList = null;
		System.out.println("EmpDaoImpl listEmp start..");
		try {
		//       naming rule         Map Id        parameter
		//empList = session.selectList("tkEmpListAll3",emp);
		empList= session.selectList("tkEmpListAll2",emp);
		}catch(Exception e) {
			System.out.println("EmpDaoImp1 list Emp Exception->"+e.getMessage());
		}
		return empList;
	}

	@Override
	public Emp datail(int empno) {
		Emp emp = null;
		System.out.println("EmpDaoImpl detail start..");
		try {
			emp=session.selectOne("tkEmpSelOne",empno);
		}catch(Exception e) {
			System.out.println("EmpDaoImp1 detail Emp Exception->"+e.getMessage());
		}
		
		return emp;
	}

	@Override
	public int update(Emp emp) {
		int kkk=0;
		System.out.println("EmpDaoImpl update start...");
		try {
			kkk =session.update("TKempUpdate",emp);
		}catch(Exception e) {
			System.out.println("EmpDaoImp1 update Emp Exception->"+e.getMessage());
		}
		return kkk;
	}

	@Override
	public List<Emp> listManager() {
		List<Emp> empList =null;
		System.out.println("EpDaoImpl listManager Start...");
		try {
			//emp 관리자만 select -
			empList=session.selectList("tkSelectManager");//sql문에 내가 값을넣어줘야하면 콤마를붙히고 넣을값세팅
		}catch(Exception e) {
			System.out.println("EpDaoImpl listManager Exception->"+e.getMessage());
		}
		return empList;
	}

	@Override
	public int insert(Emp emp) {
		int result = 0;
		System.out.println("EpDaoImpl insert start...");
		try {
		result= session.insert("insertEmp",emp);
		System.out.println("EpDaoImpl insert k="+result);
		}catch(Exception e) {
			System.out.println("EpDaoImpl insert Exception->"+e.getMessage());
		}
		return result;
	}

	@Override
	public int delete(int empno) {
		System.out.println("EpDaoImpl delete start..");
		int result = 0;
		try {
			result=session.delete("delete",empno);
		}catch(Exception e) {
			System.out.println("EpDaoImpl delete Exception->"+e.getMessage());
		}
		return result;
	}

	@Override
	public List<EmpDept> listEmpDept() {
		System.out.println("EpDaoImpl listEmpDept start..");
		List<EmpDept> listEmpDept = null;
		try {
		listEmpDept = session.selectList("TKlistEmpDept");
		}catch(Exception e) {
			System.out.println("EpDaoImpl listEmpDept Exception->"+e.getMessage());
		}
		return listEmpDept;
	}

	@Override
	public List<EmpDept> listEmp(EmpDept empDept) {
		System.out.println("EmpServiceImpl listEmp Start...");
		return session.selectList("TKlistEmpDept",empDept);
	}

	@Override
	public String deptName(int deptno) {
		System.out.println("EpDaoImpl deptName start..");
		String resultStr = "";
		try {
			resultStr=session.selectOne("TKdeptName",deptno);
			System.out.println("EpDaoImpl deptName resultStr->"+resultStr);
		}catch(Exception e) {
			System.out.println("EpDaoImpl deptName Exception->"+e.getMessage());
		}
		return resultStr;
	}
	
}
