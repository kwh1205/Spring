<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	//api나 json형식을 보는곳
	//http://jsonviewer.stack.hu/
	//response.sendRedirect("list");
	//response.sendRedirect("listEmpDept");
	//response.sendRedirect("writeDeptIn");
	//response.sendRedirect("writeDeptCursor");
	//response.sendRedirect("upLoadFormStart");
	//jpa
	//response.sendRedirect("members");
	//response.sendRedirect("interCeptorForm");
	//response.sendRedirect("helloText");
	//response.sendRedirect("/sample/sendVO2?deptno=10");//RestController Test(VO) helloText hello sendV
	//response.sendRedirect("/sendVO3");
	//response.sendRedirect("listEmpAjax");
	//멀티로우작업
	response.sendRedirect("listEmpAjax2");
	//response.sendRedirect("chat");
%>
</body>
</html>