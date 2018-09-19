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
	<table align="center">
		<tr>
			<th>user_id</th>
			<th>user_name</th>
			<th>user_type</th>
		</tr>
	<%
		ResultSet rs = (ResultSet) request.getAttribute("rs");
		while(rs.next()){
			%>
				<tr>
					<td><%= rs.getString(1) %></td>
					<td><%= rs.getString(2) %></td>
					<td><%= rs.getString(4) %></td>
				</tr>
			<%
		}
	%>
	</table></center>
</body>
</html>