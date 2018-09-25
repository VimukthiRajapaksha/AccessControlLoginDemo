<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="java.sql.ResultSet"%>
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
	<center>
		<%
			if(session.getAttribute("uname")==null){
				response.sendRedirect("index.html");
			}
			
		%>
		<c:forEach items="${rs1}" var="rs1">
			<a href="${rs1.getPage_name()}-${rs1.getFun_url()}">${rs1.getFun_name()}</a><br>
		</c:forEach>
		<br><br><br><br><br>
		<a href='home.jsp'>Back</a>
		<a href='logout'>Log out</a>
		</center>
</body>
</html>