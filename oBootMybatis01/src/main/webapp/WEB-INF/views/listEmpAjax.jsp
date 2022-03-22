<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String context = request.getContextPath();
	//컨테스트 - 현재위치를 제대로 모를때 사용하기 이렇게 컨텍스트 젤위쪽에서 선언하고 사용
	//현재위치 즉 src-main-webapp-views 이걸 가져오는거임
%>
<html>
<head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- 밑에가 다운로드방식 -->
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/httpRequest.js"></script>
<!-- 밑에가 cdn 컨텐츠 전송 네트워크 방식 -->
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<!-- ajax는 비동기방식 - j쿼리 필수 cdn 컨텐츠 전송 네트워크 방식(주소로받는건가?) - , 다운로드한거 임포트하는방식 -->
<script type="text/javascript">
    var contextPath='${pageContext.request.contextPath}';
	var src='${pageContext.request.contextPath}/images/';
	var contextPath='${pageContext.request.contextPath}';
	var i=2;
	var str="";
	var str2="";
	
	/* EmpController */
	function getDeptName(Vdeptno){
		console.log(Vdeptno);
		//alert("Vdeptno->"+Vdeptno);
		
		$.ajax(
			{
							//컨트롤러명
				url:"<%=context%>/getDeptName",	
				data:{deptno :Vdeptno},
				dataType:'text',
				//성공시 success함수를타고 data에 결과값을넣어줌 url= 컨트롤러명을넣으면됌
				success:function(data){
					//alert("success ajax Data"+data);
					//$=jquery 함수 사용할려면 jquery모듈이있어야함
					  //#=id .=class 자바클래스가아님 jsp의 클래스임 ㅇㅇ;
					  //input 태그에는 val 이유 input에서 value=""이렇게넣기떄문 
					  $('#deptName').val(data);
					 	//span태그에는 .html
					  $('#msg').html(data);
				}
			}
		);
	}
	/* RestController TEST*/
	//자바 스크립트 펑션(함수를 가져올려면) 밑에방식으로 하면됌 확인할때
	function getDept(Vdeptno){
		alert("Vdeptno->"+Vdeptno)
		$.ajax({
			url:"<%=context%>/sample/sendVO2",
			data:{deptno : Vdeptno}, //집어넣을 값지정
			dataType:'json', //받는 방법 설정 String이면 text 객체면 json으로
			success:function(data){
				str = data.firstName+" "+data.lastName+" "+data.mno;
				alert(".ajax getDept str"+str);
				$('#RestDept').val(str);     //input 태그라서 val 값넣어줄곳
			}
		});
		//객체로가져올땐 .붙혀서 원래 하던방식처럼 객체의 필드값을 표출하면됌
	}
 </script>
</head>
<body>
<h2>회원 정보</h2>
<table>
	<tr><th>사번</th><th>이름</th><th>업무</th><th>부서</th><th>근무지</th></tr>
	<c:forEach var="empDept" items="${listEmp}">
		<tr><td>${empDept.empno }</td><td>${empDept.ename }</td>
			<td>${empDept.job }</td>
			<td>${empDept.deptno} 									<!-- 이벤트임 온마우스오버 - 마우스를올리면 dept이름을갖고옴 -->
			    <input type="button" id="btn_idCheck" value="부서명"  onmouseover="getDeptName(${empDept.deptno })">
			</td>
			<td>${empDept.loc }</td>
		</tr>
	</c:forEach>
</table>
	 
	deptName:  <input type="text" id="deptName"  readonly="readonly"><p>
    Message :  <span id="msg"></span><p>

    RestController sendVO2: <input type="text" id="RestDept"       readonly="readonly"><p>
	RestController sendVO2: sendVO2<input type="button" id="btn_Dept"     value="부서명" 
	 onclick="getDept(10)"><p>
	<!-- onclick - 클릭시 값가져오기 -->

</body>
</html>