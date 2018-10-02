<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<%
			if(session.getAttribute("uname")==null){
				response.sendRedirect("index.html");
			}
			response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
			response.setHeader("Pragma", "no-cache");
		%>
<title>Insert title here</title>
</head>
<body>
	<div style="float: left; width: 20%;">
		<jsp:include page="home.jsp" />
	</div>
	<div style="float: right; width: 80%;">
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
					<td>
					<select name="type">
					   <c:forEach items="${roles}" var="r" varStatus="s">
					       <option value="${(s.index)+1}"><c:out value="${r}"/></option>
					   </c:forEach>
					</select>
					</td> 
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
	</center>
	</div>
</body>
</html>