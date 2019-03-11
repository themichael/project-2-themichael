<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="shared/header.jsp"%>
</head>
<body>
<c:if test="${ loggedUser.userRole == 'EMPLOYEE' }">
	<%@ include file="shared/employeeNavbar.jsp"%>
</c:if>
<c:if test="${ loggedUser.userRole == 'MANAGER' }">
	<%@ include file="shared/managerNavbar.jsp"%>
</c:if>
<c:choose>
	<c:when test="${isUpdated =='TRUE'}">
	<div class="form-group">
		<span class="label label-success label-panel">Information updated successfully.</span>
	</div>
	</c:when>
	<c:when test="${isUpdated =='FALSE'}">
	<div class="form-group">
		<span class="label label-danger label-panel">There was an error processing the request.</span>
	</div>
	</c:when>
</c:choose>
	<div class="col-md-4 col-md-offset-4">
		<div class="panel panel-default">
			<div class="panel-heading">
				<span class="label label-primary label-title label-panel">Update Information</span>
			</div>
			<div class="panel-body">
				<form class="form-horizontal" method="POST" action="updateInfo.do">
					<div class="form-group">
						<label for="firstname_input" class="control-label">First Name</label>
					  	<input type="text" name="firstname" class="form-control" id="firstname_input" value="${ updatedUser.firstName }" required>
					</div>
					<div class="form-group">
						<label for="lastname_input" class="control-label">Last Name</label>
					  	<input type="text" name="lastname" class="form-control" id="lastname_input" value="${ updatedUser.lastName }" required>
					</div>
					<div class="form-group">
					  <label for="username_input" class="control-label">Username</label>
					  <input type="text" name="username" class="form-control" id="username_input" value="${ updatedUser.username }" required>
					</div>
					<c:if test="${isUpdated=='INVALID'}">	
						<div class="form-group">
							<span class="label label-danger label-center-panel">This username is taken.</span>
						</div>
					</c:if>
					<div class="form-group">
					  <label for="email_input" class="control-label">Email</label>
					  <input type="email" name="email" class="form-control" id="email_input" value="${ updatedUser.email }" required>
					</div>
					<div class="form-group">
						<button type="submit" class="btn btn-lg btn-success btn-block">Update</button>
					</div>							
				</form>
			</div>
			<div class="panel-footer">
			</div>
		</div>
	</div>
</body>
</html>