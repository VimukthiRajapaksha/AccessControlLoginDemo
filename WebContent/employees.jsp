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
	<table>
		<tr>
			<th>user_id</th>
			<th>user_name</th>
			<th>user_type</th>
		</tr>
		<%! String uString = ""; 
			String dString = "";
			String cString = "";
			ResultSet rs = null;
			ResultSet rs1 = null;
			boolean uVal = false;
			boolean dVal = false;
		%>
	<%
		rs = (ResultSet) request.getAttribute("rs");
		rs1 = (ResultSet) request.getAttribute("rs1");
		if(rs1.next()){
			uString = rs1.getString(2);
			dString = rs1.getString(3);
			cString = rs1.getString(1);
			uVal = true;
			dVal = true;
		}
		else {
			uVal = false; 
			dVal = false;
		}
		while(rs.next()){
			%>
				<tr>
					<td><%= rs.getString(1) %></td>
					<td><%= rs.getString(2) %></td>
					<td><%= rs.getString(4) %></td>
					<td><% if(uVal){	
							%>
								<a href='<%= uString %>'>Update</a>
							<%
							}
						%>
					</td>
					<td><% if(dVal){	
							%>
								<a href='<%= dString %>'>Delete</a>
							<%
							}
						%>
					</td>
				</tr>
			<%
		}
	%>
	</table>
		<a href='<%= cString %>'>Add Employee</a>
		<a href='index.html'>Log out</a>
</body>
</html>