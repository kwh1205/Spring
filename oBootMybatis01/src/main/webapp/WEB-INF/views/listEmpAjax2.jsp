<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String context = request.getContextPath();
    System.out.println("context->"+context);
%>
<html>
<head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery.js"></script>


<script type="text/javascript" src="js/httpRequest.js"></script>
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<script type="text/javascript">
   
    var contextPath='${pageContext.request.contextPath}';
	var src='${pageContext.request.contextPath}/images/';
	var contextPath='${pageContext.request.contextPath}';
	var i=2;

	

	/* RestController TEST */
	//http://jsonviewer.stack.hu/
	function getListDept(){
		var str="";
		var str2="";
		
		console.log("getListDept Run");
		alert("getListDept Run...");
		$.ajax(
			{
				url:"<%=context%>/sendVO3",
				//보낼데이터가없음 받기만하는곳
				dataType:'json',
				success:function(data){
					//제이슨으로 받은객체를 스트링으로 바로 보여주는함수 아래
					var jsondata = JSON.stringify(data);
					alert("jsondata->"+jsondata);
					//div일시 .append로 값넣기 input = .var span = .html
					$('#Dept_list').append(jsondata);
					str +="<select name='dept'>";
					//data는 그대로 객체임 .each는 반복문 배열처럼 리스트형식의 0번부터 6번까지 알아서 돌림
					$(data).each(
						//익명 함수 한개만듬
						function(){
							//str2=" "+ this.deptno+"'" + this.dname +"<br>";
							  str2="<option value='"+this.deptno+"'>"+this.dname+"</option>";
							  str +=str2 ;
						}
					);
					str +="</select><p>"
					$('#Dept_list3').append(str);//태그안에 문장자체넣기 div에서 - append
				}
			
			}	
		);
	}
		//화면에서만 한건을 없애주는 ajax 새로고침하면 다시 생성됌
	function getDeptDelete(Vindex){//여기의 매개변수 Vindex는 하나의 tr값전부를가져와짐 ${status.index}이거랑같음
		console.log(Vindex);
		alert("empDept->"+Vindex);
		var selEname = $('#ename'+Vindex).val();//벨류값이들어가므로 index0,1이런게아닌 실제 값이들어감
		//ajax로 empnoDelete
		//				 $('#empno'+Vindex) = id의값을 value로 실제 값을불러들임 - 실제값 empno를 delete하면됌
		var selEmpno = $('#empno'+Vindex).val();
		$.ajax({
									//처리할 컨트롤러 물리적 삭제
				url:"<%=context%>/empnoDelete",      
				data:{empno :selEmpno, ename : selEname}, //파라미터 키, 벨류 형태로 보냄  
				dataType:'text',//json이아닌 text니까 text로보냄(그냥 String임) json , text 두가지 방법이있음
				success:function(data){
					alert(".ajax getDeptDelete data ->"+data);
					if(data=='1'){
						//리턴값은 data에 숫자로 1(성공),아니면 0(실패) 으로들어감 정상작동했으면 리턴값이 1이므로 아랫라인수행
						$('#empDept'+Vindex).remove(); // 논리적 삭제 태그
					}
				}
			
			
		});
		alert("selEname->"+selEname);
		$('#empDept'+Vindex).remove();  /* 삭제 태그*/
	}
	
	
	
 </script>
</head>
<body>
<h2>회원 정보</h2>
<table>
	<tr><th>번호</th><th>사번</th><th>이름</th><th>업무</th><th>부서</th><th>근무지</th></tr>
											<!-- 밑에 varStatus - 이걸잡고 status.index - 몇번째 건인지 배열의 [0]이거랑 마찬가지이미 -->
<c:forEach var="empDept" items="${listEmp}" varStatus="status">
	<tr id="empDept${status.index}"><td>empDept${status.index}</td>
		<td><input type="text" id="empno${status.index}" value="${empDept.empno}"> ${empDept.empno }</td>
		<td><input type="text" id="ename${status.index}" value="${empDept.ename }"> ${empDept.ename }</td>
		<td>${empDept.job }</td><td>${empDept.deptno } 
		    <input type="button" id="btn_idCheck2" value="부서Row Delete" onclick="getDeptDelete(${status.index})">
		</td>
		<td>${empDept.loc }</td>
	</tr>
</c:forEach>
</table>
	 
	deptName:  <input type="text" id="deptName"  readonly="readonly"><p>
    Message :  <span id="msg"></span><p>

    RestController RestDept1 sendVO2: <input type="text" id="RestDept1"       readonly="readonly"><p>
    RestController RestDept2 sendVO2: <input type="text" id="RestDept2"       readonly="readonly"><p>
	

    <!--  RestController LISTVO3: <input type="text" id="RestDeptList"   readonly="readonly"><p> -->
	RestController LISTVO3: <input type="button" id="btn_Dept3"     value="부서명 LIST"  onclick="getListDept()"><p>
	                        <span id="RestDeptList"></span><p>
	                        			<div id="board_reply">
	Dept_list:	<div id="Dept_list"></div>

	Dept_list3:
	<div id="Dept_list3">

	</div>
</body>
</html>