<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<center>
		<%
			ResultSet rs1 = (ResultSet) request.getAttribute("rs1");
			while(rs1.next()){
				%><a href="<%= rs1.getString("page_name")+"-"+rs1.getString("fun_url") %>"><%= rs1.getString("fun_name") %></a><br><%
			}
		%>
		<a href='index.html'>Log out</a>
		</center>
</body>
</html>