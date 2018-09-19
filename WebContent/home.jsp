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
	<%! String username = null; %>
	<%
		userBean bean = (userBean)request.getAttribute("bean");
		username = bean.getUsername();
	%>
		<center>
			<h1>Welcome <%= username %> !</h1>
			<c:forEach var='item' items='${bean.getFunc()}'>
				<!--<c:out value='Key=${item.key}, Value=${item.value}'/>-->
				<a href='${item.value}?username=${bean.getUsername()}&page=${item.value}'>${item.key}</a><br><br>
			</c:forEach>
		<br><br><br>
		<a href="index.html">Log out</a>
		</center>
</body>
</html>