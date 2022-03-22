<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>loginForm.jsp</h1>
<c:url value="j_spring_security_check" var="loginUrl"/><!-- j_spring_security_check 이걸넣으면 - 중간에가로챔 폼화면으로 -->

${loginUrl }
<form action="${loginUrl }" method="post">
	<c:if test="${param.error !=null }">
	<br>
		login Error! <br/>
		<c:if test="${SPRING_SECURITY_LAST_EXCEPTION !=NULL }"><!-- 에러메시지를 담음 -->
			message : <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message }"/>
		</c:if>
	<p>
	</c:if>
	ID:<input type="text" name="j_uesrname"><br>
	PW:<input type="text" name="j_password"><br>
	<input type="submit" value="Login">
</form>
</body>
</html>