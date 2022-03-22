package com.oracle.oBootMybatis01.controller;

import java.util.HashMap;
import java.util.List;

import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.oracle.oBootMybatis01.model.Dept;
import com.oracle.oBootMybatis01.model.DeptVO;
import com.oracle.oBootMybatis01.model.Emp;
import com.oracle.oBootMybatis01.model.EmpDept;
import com.oracle.oBootMybatis01.model.Member3;
import com.oracle.oBootMybatis01.service.EmpService;
import com.oracle.oBootMybatis01.service.Paging;

@Controller
public class EmpController {
	@Autowired
	private EmpService es;//필드인젝션일경우 생성자를생성할필요x final로 선언안해도됌 
	@Autowired
	private JavaMailSender mailSender;
	@RequestMapping(value = "/list")
	public String list(Emp emp, String currentPage, Model model) {
		System.out.println("EmpController Start list...");
		int total = es.total();	//select count(*) from emp - > 14개
		System.out.println("EmpControleer total->"+total);
		System.out.println("currentPage->"+currentPage);
		//					    14 -db에있는자료수  Null(0,1...)
		Paging pg = new Paging(total,currentPage);
		System.out.println("currentPage->"+currentPage);
		emp.setStart(pg.getStart());
		emp.setEnd(pg.getEnd());
		List<Emp> listemp= es.listEmp(emp);
		System.out.println("EmpController list listEmp.size()->"+listemp.size());
		model.addAttribute("listEmp",listemp);
		model.addAttribute("pg",pg);
		model.addAttribute("total",total);
		
		
		return "list"; //여기는 application.yml에서 지정해준곳으로감 
	}
	
	@GetMapping(value = "/detail")
	public String detail(int empno,Model model) {
		System.out.println("detail controller start..");
		Emp emp = es.detail(empno);
		System.out.println("detail controller emp.ename="+emp.getEname());
		System.out.println("detail controller emp.ename="+emp.getJob());
		model.addAttribute("emp",emp);
		return "detail";
		
	}
	
	@GetMapping(value = "/updateForm")
	public String updateForm(int empno,Model model) {
		System.out.println("updateForm controller start..");
		Emp emp = es.detail(empno);
		System.out.println("updateForm controller emp.ename="+emp.getEname());
		model.addAttribute("emp",emp);
		return "updateForm";
		
	}
	
	@PostMapping(value = "update")
	public String update(Emp emp,Model model) {
		System.out.println("Update controller start..");
		int uptCnt = es.update(emp);
		System.out.println("es.update(emp) k->"+uptCnt);
		model.addAttribute("uptCnt",uptCnt);   //Test Controller간 data 전달
		model.addAttribute("kk3","Message Test"); //Test Controller간 data 전달
		//int k = es.update(emp);
		//dao-> kkk = session.update("TkempUpdate",emp);
		//mapper -> TkempUpdate
		//return "redirect:list"; //모델에저장한거 없어짐 즉 다른컨트롤러로갈때 값을 넣어서줄려면 forward
		return "forward:list"; //forward사용시 모델로저장한값을 포함해서 리스트로감
	}
	
	@RequestMapping(value = "writeForm")
	public String writeForm(Model model) {
		//관리자 사번 만 get
		List<Emp> empList=es.listManager();
		System.out.println("writeForm empList.size()->"+empList.size());
		model.addAttribute("empMngList",empList);
		//부서(코드,부서명)
		List<Dept> deptList = es.deptSelcet();
		System.out.println("deptList.size->"+deptList.size());
		model.addAttribute("deptList",deptList);
		return "writeForm";
	}
	
	@RequestMapping(value="write" ,method = RequestMethod.POST)
	public String write(Emp emp, Model model) {
		System.out.println("EmpController Start write..." );
		//System.out.println("emp.getHiredate->"+emp.getHiredate());
		// Service, Dao , Mapper명[insertEmp] 까지 -> insert
		int result = es.insert(emp);
		if (result > 0) return "redirect:list";
		else {
			model.addAttribute("msg","입력 실패 확인해 보세요");
			return "forward:writeForm";
		}
	    	
	}
	
	@GetMapping(value = "confirm")
	public String confirm(int empno,Model model) {
		System.out.println("EmpController confirm start");
		Emp emp = es.detail(empno);
		System.out.println("empno->"+empno);
		model.addAttribute("empno",empno);
		if(emp !=null) {
			model.addAttribute("msg","중복된사번입니다");
			return "forward:writeForm";
		}else {
			System.out.println("EmpController confirm 사용가능합니다"+empno);
			model.addAttribute("msg","사용가능합니다 ");
			return "forward:writeForm";
		}
	}
	
	@RequestMapping(value="delete")
	public String delete(int empno, Model model) {
		int result = 0;
		System.out.println("EmpController Start delete");
		result = es.delete(empno);
		System.out.println("result의값은 ->"+result);
		return "redirect:list";
	}
	
	@GetMapping(value = "listEmpDept")
	public String listEmpDept(Model model) {
		EmpDept empDept = null;
		System.out.println("EmpController listEmpDept start...");
		//Seravice, dap = 그대로
		//mapper -> TKlistEmpDept
		List<EmpDept> listEmpDept = es.listEmpDept();
		model.addAttribute("listEmpDept",listEmpDept);
		return "listEmpDept";
	}
	
	@RequestMapping(value = "mailTransport")
	public String mailTransport(HttpServletRequest request,Model model) {
		System.out.println("mailSending...");
		String tomail = "qhtmej@naver.com";
		System.out.println(tomail);
		String setfrom = "tidnj0505@gmail.com";
		String title = "mailTransport 입니다";
		try {
			//Mime 전자우편 Internet 표준 Format - 프로토콜
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(message,true,"UTF-8");
			messageHelper.setFrom(setfrom); //보내는사람 생략하거나 하면 정상작동x//yml. 프로퍼티스 에서 설정한대로들어감 보내는사람
			messageHelper.setTo(tomail);    //받는사람 e메일
			messageHelper.setSubject(title);//메일제목 생략가능
			String tempPassword = (int)(Math.random()*999999)+1+"";
			messageHelper.setText("임시 비밀번호입니다 :"+ tempPassword);   //settext - 메일내용,
			System.out.println("임시 비밀번호입니다: "+tempPassword);
			DataSource dataSource = new FileDataSource("c:\\log\\jung1.jpg");
			messageHelper.addAttachment(MimeUtility.encodeText("airport.png","UTF-8","B"), dataSource);
			mailSender.send(message);
			model.addAttribute("check",1); //정상작동
			//s.tempPw(u_id,tempPassword) ; //db에 비밀번호를 임시비밀번호로 업데이트
		}catch (Exception e) {
			System.out.println(e);
			model.addAttribute("check",2);
		}
		return "mailResult";
	}
	
//  Procedure Test 입력화면 
	@RequestMapping(value = "writeDeptIn", method = RequestMethod.GET)
	public String writeDeptIn(Model model) {
		 System.out.println("writeDeptIn Start..");
	    return "writeDept3";
	}
	
	@RequestMapping(value = "writeDept")
	public String writeDeptIn(DeptVO deptVO,Model model) {
		System.out.println("writeDeptIn start..");
		System.out.println("writeDeptIn deptVO.getDeptno()->"+deptVO.getDeptno());
		System.out.println("writeDeptIn deptVO.getDname()->"+deptVO.getDname());
		es.insertDept(deptVO);//procedure call read only 리턴이없는 void타입으로
		if(deptVO ==null) {
			System.out.println("deptVO==null");
		}else {
			System.out.println("deptVO.getOdeptno()->"+deptVO.getOdeptno());
			System.out.println("deptVO.getOdname()->"+deptVO.getOdname());
			System.out.println("deptVO.getOloc()->"+deptVO.getOloc());
			model.addAttribute("msg","정상입력됨");
			model.addAttribute("dept",deptVO);
			
		}
		return "writeDept3";
	
	}
	@GetMapping(value = "writeDeptCursor")
	public String writeDeptCursor(Model model) {//dto방식으로 받아들이기(파라미터) - 정형화되어있음 누가봐도 파악하기 쉬움 맵방식 - 알아보기힘듦 
		System.out.println("EmpController writeDeptCursor start...");
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("sDeptno", 10); //맵방식 -dto를 만들기 귀찮고 일회성을넣을땐 맵
		map.put("eDeptno", 55);
		es.selListDept(map);
		//위쪽엔 리턴값이없지만 프로시져할 값넣어주기위해 넣어줌 - 아래는 값을 받는거
		List<Dept> deptLists = (List<Dept>) map.get("dept");//dept커서를 꺼냄 어디서? - dept 매퍼에서 이걸로 선언해서 받아오는거임 이게 셀렉트문의 모든값인데 이걸 리스트형식으로 한번에 저장7
		for(Dept dept : deptLists) {//맵에 리턴값이없어도 자동으로 값이 넣어짐 왜? 프로시져로 집어넣었음 rs를
			System.out.println("dept.getDname->"+dept.getDname());
			System.out.println("dept.getLoc->"+dept.getLoc());
		}
		System.out.println("deptList size->"+deptLists.size());
		model.addAttribute("deptList",deptLists);
		
		return "writeDeptCursor";
	}
	
	//interCepter 시작화면
	@RequestMapping(value = "interCeptorForm", method = RequestMethod.GET)
	public String interCepterForm(Model model) {
		System.out.println("interCeptorForm start..");
		return "interCeptorForm";
	}
	
	@RequestMapping(value = "interCeptor")
	public String interCeptor(String id, Model model) {
		System.out.println("interCeptor Test Start");
		System.out.println("interCeptor id->" + id);
		//존재시 :1 안존해하면 0
		int memCnt = es.memCount(id);
		
		System.out.println("memCnt->" + memCnt);
		//List<MepDept> listEmp = es.listEmp(empDept); User 가져오는 Service
		//member1의 Count 가져오는 Service 수행
		//member.setId("kkk");
		//존재시 :1 안존해하면 0 모델에넣은걸 샘플인터셉터에서 처리
		model.addAttribute("id", id);
		model.addAttribute("memCnt", memCnt);
		System.out.println("interCeptor Test End");
		return "interCeptor"; //User 존재하면 User 이용 조회 Page
	}
	
	//SampleIntercepter 내용을 받아 처리
	@RequestMapping(value = "doMemberWrite",method=RequestMethod.GET)
	public String doMemberWrite(Model model,HttpServletRequest request) {//request적는이유 - 샘플에서 값을저장한걸 꺼내기위해
		String ID=(String) request.getSession().getAttribute("ID");
		System.out.println("doMemberWrite 부터하세요");
		model.addAttribute("id",ID);
		return "doMemberWrite";
	}
	
	//interCeptor 진행 Test
	@RequestMapping(value = "doMemberList")
	public String doMemberList(Model model,HttpServletRequest request)	{
		String ID=(String) request.getSession().getAttribute("ID");
		System.out.println("doMemberList Test Start ID->"+ID);
		Member3 member3 = null;
		//Member1 List Get Service
		List<Member3> listMem = es.listMem(member3);
		System.out.println("listMem.size()->"+listMem.size());
		model.addAttribute("ID",ID);
		model.addAttribute("listMem",listMem);
		System.out.println("ID->"+ID);
		return "doMemberList"; //user존재시 user 이용 조회 page
	}
	
	//Ajax List Test
	@RequestMapping(value="listEmpAjax")
	public String listEmpAjax(Model model) {
		EmpDept empDept = null;
		System.out.println("Ajax List Test START..");
		List<EmpDept> listEmp=es.listEmp(empDept);
		System.out.println("EmpController listEmpAjax listEmp.size()->"+listEmp.size());
		model.addAttribute("result","kkk");
		model.addAttribute("listEmp",listEmp);
		return "listEmpAjax";
	}
	//레스트컨트롤러가아닌 일반 컨트롤러에서 리스폰스바디를 사용할땐 맵핑옆에다가 프로듀시즈넣어주기
	@RequestMapping(value = "getDeptName" , produces = "application/text;charset=UTF-8")//스트링일시 프로듀시즈 인코딩,디코딩해서 보내줌
	@ResponseBody
	public String getDeptName(int deptno,Model model) {
		System.out.println("deptno->"+deptno);
		return es.deptName(deptno);
	}
	
	// Ajax  List Test
	@RequestMapping(value="listEmpAjax2")
	public String listEmpAjax2(Model model) {
		EmpDept empDept = null;
		System.out.println("listEmpAjax2 Start");
		List<EmpDept> listEmp = es.listEmp(empDept);
		model.addAttribute("result","kkk");
		model.addAttribute("listEmp",listEmp);
		return "listEmpAjax2";
	}

}
