<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">>
<title>Insert title here</title>
</head>
<body>
<h1>welcome.jsp</h1>

<c:if test="${not empty pageContext.request.userPrincipal }">
	${pageContext.request.userPrincipal }
<p> is Log-In</p>
</c:if>

<c:if test="${ empty pageContext.request.userPrincipal }">
<p> is Logout</p>
</c:if>
USER ID: ${pageContext.request.userPrincipal.name }<br>
<a href="${pageContext.request.contextPath }/j_spring_security_logout">Log out</a><br>
</body>
</html>