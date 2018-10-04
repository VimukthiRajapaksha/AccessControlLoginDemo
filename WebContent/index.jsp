<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Access Control</title>
</head>
<body>
	<table align="center">
		<tr>
			<td>
				<form action="home" method="post">
					<table>
						<tr>
							<td>user name:</td>
							<td><input type="text" name="username" value="admin1"></td>
						</tr>
						<tr>
							<td>password:</td>
							<td><input type="password" name="password" value="admin1"></td>
						</tr>
						<tr>
							<td><input type="submit" name="submit" value="Login"></td>
						</tr>
					</table>
				</form>
				<c:if test="${valid_user==false}">
					<c:out value="Invalid username or password !"></c:out>
				</c:if>
				<a href="https://tomcat.apache.org/tomcat-8.0-doc/jndi-datasource-examples-howto.html">connection pool</a>
			</td>
		</tr>
	</table>
</body>
</html>