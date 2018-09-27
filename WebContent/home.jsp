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
<body style="background-color: powderblue; padding-top: 5%;">
	<%
		if(session.getAttribute("uname")==null){
			response.sendRedirect("index.html");
		}
	%>
			<h2>Welcome ${bean.getUsername()} !</h2>
			<c:forEach var='item' items='${bean.getPages()}'>
				<form method="post" action="${item.value}">
					<input type="hidden" name="page" value="${item.key}">
					<input type="submit" value="${item.key}">
				</form>
			</c:forEach>
		<br><br><br>
		<a href='logout'>Log out</a>
</body>
</html>