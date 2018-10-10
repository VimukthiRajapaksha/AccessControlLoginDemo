<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.acl.bean.*"%>
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
						<h3 class="mb-0">Update user</h3>
					</div>
					<div class="card-body">
						<form action="${page}_update" method="post"
							onsubmit="return confirm('Update?');" class="form" role="form">
							<div class="form-group row">
								<label class="col-lg-3 col-form-label form-control-label">User
									id</label>
								<div class="col-lg-9">
									<input class="form-control" type="text" name="userid"
										value="${user_id}" disabled>
								</div>
							</div>
							<div class="form-group row">
								<label class="col-lg-3 col-form-label form-control-label">Username</label>
								<div class="col-lg-9">
									<input class="form-control" type="text" name="username"
										value="${ub.getUsername()}">
								</div>
							</div>
							<div class="form-group row">
								<label class="col-lg-3 col-form-label form-control-label">Email</label>
								<div class="col-lg-9">
									<input class="form-control" type="text" name="email"
										value="${ub.getEmail()}">
								</div>
							</div>
							<div class="form-group row">
								<label class="col-lg-3 col-form-label form-control-label">Phone</label>
								<div class="col-lg-9">
									<input class="form-control" type="text" name="phone"
										value="${ub.getPhone()}">
								</div>
							</div>
							<div class="form-group row">
								<label class="col-lg-3 col-form-label form-control-label">User
									Role</label>
								<div class="col-lg-9">
									<select class="form-control" name="user_role">
										<c:forEach items="${roles}" var="r">
											<option value="${r}"
												<c:if test="${r==ub.getRole_name()}">
														<c:out value="selected"></c:out>
													</c:if>><c:out
													value="${r}" /></option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="form-group row">
								<label class="col-lg-3 col-form-label form-control-label"></label>
								<div class="col-lg-9">
									<input class="btn btn-info" type="submit" value="Update" />
								</div>
							</div>
						</form>
						<div class="form-group row">
							<label class="col-lg-3 col-form-label form-control-label"></label>
							<div class="col-lg-9">
								<form action="employee" method="post">
									<input type="hidden" name="page" value="${page}"> <input
										class="btn btn-secondary" type="submit" value="Back"
										style="padding-left: 3%; padding-right: 3%;">
								</form>
							</div>
						</div>
						<c:choose>
							<c:when test="${result==true}">
								<div class="alert alert-success" role="alert">user updated
									successfully !</div>
							</c:when>
							<c:when test="${result==false}">
								<div class="alert alert-warning" role="alert">Sorry
									something went wrong, try again !</div>
							</c:when>
							<c:otherwise>

							</c:otherwise>
						</c:choose>
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
	</div>
</body>
</html>