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
					<td>user type: </td>
					<td><select name="type">
					  <option value="1">Admin</option>
					  <option value="2">Employee</option>
					  <option value="4">data entry</option>
					  <option value="3">Authorizer</option>
					</select></td> 
				</tr>
				<tr>
					<td></td>
				</tr>
			</table>
			<center><input type="submit" name="submit" value="Sumbit details"></center>
		</form>
	</center>
</body>
</html>