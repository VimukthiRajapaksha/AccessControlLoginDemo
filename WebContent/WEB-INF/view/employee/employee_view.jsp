<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" errorPage="error.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
			<div class="col-lg-9">
				<nav class="navbar navbar-light bg-default justify-content-between">
				<div class="btn-group col-lg-3">
					<button type="button" class="btn btn-outline-info dropdown-toggle"
						data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						Number of rows</button>
					<div class="dropdown-menu">
						<a class="dropdown-item" href="employee?pageNo=1&limit=3&offset=0">3</a> 
						<a class="dropdown-item" href="employee?pageNo=1&limit=5&offset=0">5</a> 
						<a class="dropdown-item" href="employee?pageNo=1&limit=7&offset=0">7</a>
					</div>
				</div>
				<div class="btn-group col-lg-2">
					<button type="button" class="btn btn-outline-info dropdown-toggle"
						data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						Save as</button>
					<div class="dropdown-menu">
						<a class="dropdown-item" href="PrintController?type=e&limit=${limit}&offset=${(pageNo - 1) * limit}">Excel</a> 
						<a class="dropdown-item" href="PrintController?type=w&limit=${limit}&offset=${(pageNo - 1) * limit}">Word</a>
						<a class="dropdown-item" href="PrintController?type=p&limit=${limit}&offset=${(pageNo - 1) * limit}">PDF</a>
					</div>
				</div>
				<c:forEach items="${bean.getFunctions()}" var="func">
					<c:if test="${func.key=='Search'}">
						<form action="employee" method="get"
							class="form-inline my-2 my-lg-0">
							<input type="hidden" name="page" value="${page}"><input
								type="text" name="keyWord"
								class="form-control mr-sm-2 border-info"><input
								type="submit" value="Search"
								class="btn btn-outline-info my-2 my-sm-0">
						</form>
					</c:if>
					<c:if test="${func.key=='Create'}">
						<form action="${page}_${func.value}" method="get">
							<input type="submit" value="New User"
								class="btn btn-outline-info" />
						</form>
					</c:if>
				</c:forEach> </nav>
				<table class="table table-hover">
					<tr>
						<th>user_id</th>
						<th>user_name</th>
						<th>user_role</th>
						<th>email</th>
						<th>phone</th>
					</tr>
					<c:forEach items="${emp_bean}" var="view">
						<tr>
							<td>${view.getUserid()}</td>
							<td>${view.getUsername()}</td>
							<td>${view.getRole_name()}</td>
							<td>${view.getEmail()}</td>
							<td>${view.getPhone()}</td>
							<c:forEach items="${bean.getFunctions()}" var="func">
								<c:if test="${func.key=='Delete'}">
									<td>
										<form action="${page}_${func.value}" method="post"
											onsubmit="return confirm('Delete?');">
											<input type="hidden" name="userid"
												value="${view.getUserid()}"> <input type="hidden"
												name="page" value="${page}"> <input type="submit"
												value="Delete" class="btn btn-outline-danger" />
										</form>
									</td>
								</c:if>
								<c:if test="${func.key=='Update'}">
									<td>
										<form action="${page}_${func.value}" method="get">
											<input type="hidden" name="userid"
												value="${view.getUserid()}"> <input type="submit"
												value="Update" class="btn btn-outline-success" />
										</form>
									</td>
								</c:if>
							</c:forEach>
						</tr>
					</c:forEach>
				</table>
				<c:choose>
					<c:when test="${Delresult==true}">
						<script>
							alert("user deleted successfully");
						</script>
					</c:when>
					<c:when test="${Delresult==false}">
						<script>
							alert("Sorry something went wrong, try again !");
						</script>
					</c:when>
					<c:when test="${excelResult==true}">
						<script>
							alert("Data saved successfully !");
						</script>
					</c:when>
					<c:when test="${excelResult==false}">
						<script>
							alert("Sorry something went wrong, try again !");
						</script>
					</c:when>
				</c:choose>
				<c:set value="${pageNo-1}" var="pNo"></c:set>
				<c:if test="${pNo<=1}">
					<c:set value="1" var="pNo"></c:set>
				</c:if>
				<c:set value="${count/limit}" var="pageCount"></c:set>
				<fmt:formatNumber value="${pageCount + ( 1 - (pageCount%1)) % 1}"
					var="pageAsInt" maxFractionDigits="0" />
				<nav>
				<ul class="pagination justify-content-center">
					<li class="page-item"><a class="page-link"
						href="employee?limit=${limit}&offset=${(pNo - 1) * limit}"
						tabindex="-1">Previous</a></li>
					<c:forEach begin="0" end="${pageCount}" varStatus="loop">
						<li class="page-item"><a class="page-link"
							href="employee?pageNo=${loop.index+1}&limit=${limit}&offset=${loop.index*limit}">${loop.index+1}</a></li>
					</c:forEach>
					<c:choose>
						<c:when test="${pageNo<pageAsInt}">
							<li class="page-item"><a class="page-link"
								href="employee?pageNo=${pageNo+1}&limit=${limit}&offset=${ pageNo*limit }">Next</a></li>
						</c:when>
						<c:when test="${empty pageNo}">
							<li class="page-item"><a class="page-link"
								href="employee?pageNo=2&limit=${limit}&offset=${ 1*limit }">Next</a></li>
						</c:when>
						<c:otherwise>
							<li class="page-item"><a class="page-link"
								href="employee?pageNo=${pageAsInt}&limit=${limit}&offset=${ (pageAsInt-1) * limit }">Next</a></li>
						</c:otherwise>
					</c:choose>

				</ul>
				</nav>
				<div class="pagination justify-content-center">${count}
					Results - ${pageNo}/${pageAsInt} Pages</div>
			</div>
			<p></p>
		</div>
	</div>
</body>
</html>