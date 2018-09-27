<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
		<%
			if(session.getAttribute("uname")==null){
				response.sendRedirect("index.html");
			}
			response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
			response.setHeader("Pragma", "no-cache");
		%>
	<center>
		<form action="addEmployee" method="post">
			<table>
				<tr>
					<td>user name: </td>
					<td><input type="text" name="username"></td>
				</tr>
				<tr>
					<td>Password: </td>
					<td><input type="text" name="password"></td>
				</tr>
				<tr>
					<td>Email: </td>
					<td><input type="text" name="email"></td>
				</tr>
				<tr>
					<td>Phone: </td>
					<td><input type="text" name="phone"></td>
				</tr>
				<tr>
					<td>user type: </td>
					<td><select name="type">
					  <option value="1">Admin</option>
					  <option value="2">Employee</option>
					  <option value="4">data entry</option>
					  <option value="3">Authorizer</option>
					</select></td> 
				</tr>
				<tr>
					<td></td>
				</tr>
			</table>
			<center><input type="submit" name="submit" value="Sumbit details"></center>
		</form>
		<div>
			<c:choose>
				<c:when test="${result==true}">
					<c:out value="User added successfully !"></c:out>
				</c:when>
				<c:when test="${result==false}">
					<c:out value="Sorry something went wrong, try again !"></c:out>
				</c:when>
				<c:otherwise>
					<c:out value=""></c:out>
				</c:otherwise>
			</c:choose>
		</div>
		<br><br><br><br><br>
		<form action="emp" method="post">
			<input type="hidden" name="username" value="${uname}">
			<input type="hidden" name="page" value="${page}">
			<input type="submit" value="Back">
		</form>
		<a href='logout'>Log out</a>
	</center>
</body>
</html>