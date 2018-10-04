<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.acl.userBean.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div style="float: left; width: 20%;">
		<jsp:include page="home.jsp" />
	</div>
	<div style="float: right; width: 80%;">
		<form action="${page}_update" method="post"
			onsubmit="return confirm('Update?');">
			<p>User id : <input type="text" name="userid" value="${user_id}" disabled></p>
			<p>Username : <input type="text" name="username" value="${ub.getUsername()}"></p>
			<p>Email : <input type="text" name="email" value="${ub.getEmail()}"></p>
			<p>Phone : <input type="text" name="phone" value="${ub.getPhone()}"></p>
			<p>User Role : 
			<select name="user_role">
			   <c:forEach items="${roles}" var="r">
			       <option value="${r}" 
			       		<c:if test="${r==ub.getRole_name()}">
							<c:out value="selected"></c:out>
						</c:if>
			       ><c:out value="${r}"/></option>
			   </c:forEach>
			</select></p>
			<input type="submit" value="Update" />
		</form>
		<form action="employee_view" method="post">
			<input type="hidden" name="page" value="${page}"> 
			<input type="submit" value="Back">
		</form>
		<div>
			<c:choose>
				<c:when test="${result==true}">
					<c:out value="user updated successfully !"></c:out>
				</c:when>
				<c:when test="${result==false}">
					<c:out value="Sorry something went wrong, try again !"></c:out>
				</c:when>
				<c:otherwise>
				
				</c:otherwise>
			</c:choose>
		</div>
		
	</div>
</body>
</html>