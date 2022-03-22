<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 <script type="text/javascript">
 	function chk() {
		if(!frm.id.value){
			alert("id입력하세요");
			frm.id.focus();
			return false
		}
		return true;
	}	
 	
 </script>
	<form name="frm" action="student" method="post" onsubmit="return chk()">
		student id: <input type="text" name="id">
		<input type="submit" value="제출">  
	
	</form>
</body>
</html>