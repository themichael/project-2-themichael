<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="shared/header.jsp"%>
<!--  PasswordMismatch JavaScript  -->
<script src="resources/js/passwordMismatch.js"></script>
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
		<span class="label label-success label-panel">Password changed successfully.</span>
	</div>
	</c:when>
	<c:when test="${isUpdated =='FALSE'}">
	<div class="form-group">
		<span class="label label-danger label-panel">There was an error processing the request.</span>
	</div>
	</c:when>
	<c:when test="${isUpdated =='INVALID'}">
	<div class="form-group">
		<span class="label label-danger label-panel">The current password is incorrect.</span>
	</div>
	</c:when>
</c:choose>
	<div class="col-md-4 col-md-offset-4">
		<div class="panel panel-default">
			<div class="panel-heading">
				<span class="label label-primary label-title label-panel">Change Password</span>
			</div>
			<div class="panel-body">
				<form class="form-horizontal" method="POST" action="changePassword.do">
					<div class="form-group">
						<label for="current_password_input" class="control-label">Current password</label>
					  	<input type="password" name="currentPassword" class="form-control password-input" id="current_password_input" required>
					</div>
					<div class="form-group">
						<label for="new_password_input" class="control-label">New password</label>
					  	<input type="password" name="newPassword" class="form-control password-input" id="new_password_input" required>
					</div>
					<div class="form-group">
					  <label for="confirm_password_input" class="control-label">Confirm password</label>
					  <input type="password" class="form-control password-input" id="confirm_password_input" required>
					</div>
					<div id="password_mismatch" class="form-group">
						<span class="label label-danger label-center-panel">Password mismatch.</span>
					</div>
					<div class="form-group">
						<button type="submit" class="btn btn-lg btn-success btn-block">Update</button>
					</div>							
				</form>
			</div>
			<div class="panel-footer">
				<a class="anchor-center"><strong>Clear</strong></a>
			</div>
		</div>
	</div>
</body>
</html>