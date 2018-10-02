<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript">
	function roleFunc(val) {
		var res = val.concat("_roles");
		var checkVal = document.getElementById(val);
		var divVal = document.getElementById(res);
		if (checkVal.checked == true) {
			divVal.style.display = "block";
		} else {
			divVal.style.display = "none";
		}
	}
</script>
</head>
<body>
	Role name:
	<input type="text" name="role_name">
	<p>accessible Pages:</p>
	<c:forEach var="p" items="${pages}">
		<div>
			<input type="checkbox" id="${p.key}" onclick="roleFunc('${p.key}');">${p.key}</div>

		<div id="${p.key}_roles" style="display: none;">
			<ul>
				<c:forEach var="f" items="${functions}">
					<li><input type="checkbox" id="${f.key}">${f.key}</li>
				</c:forEach>
			</ul>
		</div>
	</c:forEach>
</body>
</html>