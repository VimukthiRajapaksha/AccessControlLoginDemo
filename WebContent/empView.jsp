<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.acl.userBean.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>
table {
	border-collapse: collapse;
	width: 100%;
}

th, td {
	padding: 1%;
	text-align: center;
	border-bottom: 1px solid #ddd;
}
</style>
</head>
<body>
	<div style="float: left; width: 20%;">
		<jsp:include page="home.jsp" />
	</div>
	<div style="float: right; background-color: white; width: 80%;">
		<center>
			<table align="center" border="1">
				<tr>
					<th>user_id</th>
					<th>user_name</th>
					<th>user_type</th>
				</tr>
				<c:forEach items="${emp_bean}" var="view">
					<tr>
						<td>${view.getUserid()}</td>
						<td>${view.getUsername()}</td>
						<td>${view.getRole_name()}</td>
						
						<c:forEach items="${bean.getFunctions()}" var="func">
							<c:if test="${func.key=='Delete'}">
								<td>
									<form action="${page}_${func.value}" method="post" onsubmit="return confirm('Delete?');">
											<input type="hidden" name="userid" value="${view.getUserid()}">
											<input type="submit"value="Delete" />
									</form>
								</td>
							</c:if>
							<c:if test="${func.key=='Update'}">
								<td>
									<form action="${page}_${func.value}" method="get">
											<input type="hidden" name="userid" value="${view.getUserid()}">
											<input type="submit"value="Update" />
									</form>
								</td>
							</c:if>
						</c:forEach>
						
					</tr>
				</c:forEach>
			</table>
			
			<%-- <br>
			<br>
			<br>
			<br>
			<br>
			<form action="emp" method="post">
				<input type="hidden" name="username" value="${uname}"> <input
					type="hidden" name="page" value="${page}"> <input
					type="submit" value="Back">
			</form>
			<a href='logout'>Log out</a> --%>
		</center>
	</div>
</body>
</html>