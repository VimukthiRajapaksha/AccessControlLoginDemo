<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" errorPage="error.jsp"%>
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
		<c:forEach items="${bean.getFunctions()}" var="func">
				<c:if test="${func.key=='Create'}">
					<input type = "button" onclick="window.location.href='${page}_${func.value}';" value="New User">
				</c:if>
				<c:if test="${func.key=='Search'}">
					<form action="employee_view" method="get">
						<input type="hidden" name="page" value="${page}">
						<span><input type="text" name="keyWord"><input type="submit" value="search"></span>
					</form>
				</c:if>
			</c:forEach>
			<table align="center" border="1">
				<tr>
					<th>user_id</th>
					<th>user_name</th>
					<th>user_role</th>
					<th>email</th>
					<th>phone</th>
				</tr>
				<c:forEach items="${emp_bean}" var="view">
					<tr>
						<td>${view.getUserid()}</td>
						<td>${view.getUsername()}</td>
						<td>${view.getRole_name()}</td>
						<td>${view.getEmail()}</td>
						<td>${view.getPhone()}</td>
						<c:forEach items="${bean.getFunctions()}" var="func">
							<c:if test="${func.key=='Delete'}">
								<td>
									<form action="${page}_${func.value}" method="post" onsubmit="return confirm('Delete?');">
											<input type="hidden" name="userid" value="${view.getUserid()}">
											<input type="hidden" name="page" value="${page}">
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
			<c:choose>
				<c:when test="${result==true}">
					<script>alert("user deleted successfully");</script>
				</c:when>
				<c:when test="${result==false}">
					<script>alert("Sorry something went wrong, try again !");</script>
				</c:when>
			</c:choose>
		</center>
	</div>
</body>
</html>