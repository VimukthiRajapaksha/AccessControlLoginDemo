<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
 <%@ page import="com.acl.userBean.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>HOME</title>
</head>
<body>
	<%
		if(session.getAttribute("uname")==null){
			response.sendRedirect("index.html");
		}
	%>
		<center>
			<h1>Welcome ${uname} !</h1>
			<c:forEach var='item' items='${bean.getFunc()}'>
				<form method="post" action="${item.value}">
					<input type="hidden" name="username" value="${uname}">
					<input type="hidden" name="page" value="${item.value}">
					<input type="submit" value="${item.key}">
				</form>
			</c:forEach>
		<br><br><br>
		<a href='logout'>Log out</a>
		</center>
</body>
</html>