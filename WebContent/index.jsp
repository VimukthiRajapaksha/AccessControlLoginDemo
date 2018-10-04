<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Access Control</title>
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet" />
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-lg-4 col-md-4 col-sm-2 col-xs-1"></div>
			<div class="col-lg-4 col-md-4 col-sm-8 col-xs-10"
				style="padding-top: 15%">
				<div class="panel panel-info">
					<div class="panel-heading">
						<p class="h4 mb-4">SignIn to the system</p>
					</div>
					<div class="panel-body">
						<div class="input-group input-group-lg h4 mb-4">
							<form action="home" method="post">
								<input type="text" name="username"
									class="form-control mb-4" placeholder="Username" /> 
								<input
									type="password" name="password"
									class="form-control" placeholder="Password"
									style="margin-top: 2%; margin-bottom: 2%;" />
								<input
									type="submit" name="submit" value="Sign In"
									class="btn btn-info btn-block my-4" />
							</form>
						</div>
						<div class="panel-footer">
							<c:if test="${valid_user==false}">
								<div class="alert alert-warning" role="alert">Invalid
									username or password !</div>
							</c:if>
						</div>
					</div>
				</div>
			</div>
			<div class="col-lg-4 col-md-4 col-sm-2 col-xs-1"></div>
		</div>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>