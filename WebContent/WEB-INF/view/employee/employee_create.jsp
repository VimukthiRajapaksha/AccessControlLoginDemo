<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Access Control</title>
</head>
<body>
	<div class="container-fluid full-width ww">
		<div class="row row-no-gutter">
			<div class="col-lg-3">
				<jsp:include page="../home.jsp" />
			</div>
			<div class="col-lg-9" style="padding-top: 5%;">
				<div class="card card-outline-secondary">
					<div class="card-header">
						<h3 class="mb-0">Add User</h3>
					</div>
					<div class="card-body">
						<form action="employee_add" method="post"
							onsubmit="return confirm('Save?');" class="form" role="form">
							<div class="form-group row">
								<div class="col-lg-2"></div>
								<div class="col-lg-8">
									<input class="form-control" type="text" name="username"
										value="" placeholder="username">
								</div>
							</div>
							<div class="form-group row">
								<div class="col-lg-2"></div>
								<div class="col-lg-8">
									<input class="form-control" type="text" name="password"
										value="" placeholder="password">
								</div>
							</div>
							<div class="form-group row">
								<div class="col-lg-2"></div>
								<div class="col-lg-8">
									<input class="form-control" type="text" name="email"
										value="" placeholder="email">
								</div>
							</div>
							<div class="form-group row">
								<div class="col-lg-2"></div>
								<div class="col-lg-8">
									<input class="form-control" type="text" name="phone"
										value="" placeholder="phone">
								</div>
							</div>
							<div class="form-group row">
								<div class="col-lg-2"></div>
								<div class="col-lg-8">
									<select name="type" class="form-control">
									   <c:forEach items="${roles}" var="r" varStatus="s">
									       <option value="${(s.index)+1}"><c:out value="${r}"/></option>
									   </c:forEach>
									</select>
								</div>
							</div>
							<div class="form-group row">
							<div class="col-lg-5"></div>
								<div class="col-lg-7">
									<input class="btn btn-info" type="submit" value="Save" style="padding-left: 5%; padding-right: 5%;"/>
								</div>
							</div>
						</form>
						<div class="form-group row">
							<div class="col-lg-5"></div>
							<div class="col-lg-7">
								<form action="employee" method="post">
									<input type="hidden" name="page" value="${page}"> <input
										class="btn btn-secondary" type="submit" value="Back" style="padding-left: 5%; padding-right: 5%;" />
								</form>
							</div>
						</div>
						<c:choose>
							<c:when test="${result==true}">
								<div class="alert alert-success" role="alert">user added
									successfully !</div>
							</c:when>
							<c:when test="${result==false}">
								<div class="alert alert-warning" role="alert">Sorry
									something went wrong, try again !</div>
							</c:when>
							<c:otherwise>
								<c:out value=""></c:out>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
</body>
</html>