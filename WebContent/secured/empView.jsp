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
/* table {
	border-collapse: collapse;
	width: 100%;
} */
</style>
</head>
<body>
	<div class="container-fluid full-width ww">
		<div class="row row-no-gutter">
			<div class="col-lg-3">
				<jsp:include page="home.jsp" />
			</div>
			<div class="col-lg-9">
				<nav class="navbar navbar-light bg-default justify-content-between">
					<c:forEach items="${bean.getFunctions()}" var="func">
						<c:if test="${func.key=='Search'}">
							<form action="employee_view" method="get" class="form-inline my-2 my-lg-0">
								<input type="hidden" name="page" value="${page}"><input
									type="text" name="keyWord" class="form-control mr-sm-2 border-info"><input type="submit"
									value="Search" class="btn btn-outline-info my-2 my-sm-0">
							</form>
						</c:if>
						<c:if test="${func.key=='Create'}">
							<input type="button"
								onclick="window.location.href='${page}_${func.value}';"
								value="New User" class="btn btn-outline-info">
						</c:if>
					</c:forEach>
					</nav>
					<table class="table table-hover">
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
											<form action="${page}_${func.value}" method="post"
												onsubmit="return confirm('Delete?');">
												<input type="hidden" name="userid"
													value="${view.getUserid()}"> <input type="hidden"
													name="page" value="${page}"> <input type="submit"
													value="Delete" class="btn btn-outline-danger"/>
											</form>
										</td>
									</c:if>
									<c:if test="${func.key=='Update'}">
										<td>
											<form action="${page}_${func.value}" method="get">
												<input type="hidden" name="userid"
													value="${view.getUserid()}"> <input type="submit"
													value="Update" class="btn btn-outline-success"/>
											</form>
										</td>
									</c:if>
								</c:forEach>
							</tr>
						</c:forEach>
					</table>
					<c:choose>
						<c:when test="${result==true}">
							<script>
								alert("user deleted successfully");
							</script>
						</c:when>
						<c:when test="${result==false}">
							<script>
								alert("Sorry something went wrong, try again !");
							</script>
						</c:when>
					</c:choose>
			</div>
		</div>
	</div>
</body>
</html>