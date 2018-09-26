<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.acl.userBean.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
		if (session.getAttribute("uname") == null) {
			response.sendRedirect("index.html");
		}
	%>
	<center>
		<span>
			<input type="text" value="User_id" disabled>
			<input type="text" value="User_name" disabled>
			<input type="text" value="User_role" disabled>
			<input type="submit" value="--------" disabled>
		</span>
		<c:forEach items="${emp_bean}" var="view">
				<form action="Manage Employees-update" method="post">
					<span>
						<input type="text" name="User_id" value="${view.getUserid()}" disabled>
						<input type="text" name="userName" value="${view.getUsername()}">
						<input type="text" name="roleName" value="${view.getRole_name()}"> 
						<input type="hidden" name="userid" value="${view.getUserid()}">
						<input type="submit"value="Update" />
					</span>
				</form>
		</c:forEach>
		<br>
		<br>
		<br>
		<br>
		<br>
		<form action="emp" method="post">
			<input type="hidden" name="username" value="${uname}"> <input
				type="hidden" name="page" value="${page}"> <input
				type="submit" value="Back">
		</form>
		<a href='logout'>Log out</a>
	</center>
</body>
</html>