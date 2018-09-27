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
			<p>Username : <input type="text" name="username" value="${ub.getUsername()}"></p>
			<p>User Role : <input type="text" name="user_role" value="${ub.getRole_name()}"></p>
			<p>Email : <input type="text" name="email" value="${ub.getEmail()}"></p>
			<p>Phone : <input type="text" name="phone" value="${ub.getPhone()}"></p>
			
			<select name="role">
			   <c:forEach items="${roles}" var="r">
			       <option value="${r}"><c:out value="${r}"/></option>
			   </c:forEach>
			</select>
			<input type="submit" value="Update" />
		</form>
	</div>
</body>
</html>