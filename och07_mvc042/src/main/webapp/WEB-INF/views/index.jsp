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
 		String context = request.getContextPath();
 	%>
	context : <%=context %><br><!-- context - 자기 위치를 알지못할때 넣어줌 내가만든 프로젝트 3번쨰 .mvc042 - 이게 컨테스트 -->
	<%-- <form action="<%=context %>/studentView1" method="post"> --%>
	<form action="<%=context %>/studentView2" method="post">
		이름:<input type="text" name="name"><br>
		나이:<input type="text" name="age"><br>
		학년:<input type="text" name="gradeNum"><br>
		반:<input type="text" name="classNum"><br>
		<input type="submit" value="제출">  
	
	</form>
</body>
</html>