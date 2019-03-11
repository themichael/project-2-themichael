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
<%@ include file="shared/managerNavbar.jsp"%>
<c:choose>
	<c:when test="${isSubmitted =='TRUE'}">
	<div class="form-group">
		<span class="label label-success label-panel">Employee registered successfully.</span>
	</div>
	</c:when>
	<c:when test="${isSubmitted =='FALSE'}">
	<div class="form-group">
		<span class="label label-danger label-panel">There was an error processing the request.</span>
	</div>
	</c:when>
</c:choose>
	<div class="col-md-4 col-md-offset-4">
		<div class="panel panel-default">
			<div class="panel-heading">
				<span class="label label-primary label-title label-panel">Register Employee</span>
			</div>
			<div class="panel-body">
				<form class="form-horizontal" method="POST" action="registerEmployee.do">
					<div class="form-group">
						<label for="firstname_input" class="control-label">First Name</label>
					  	<input type="text" name="firstname" class="form-control" id="firstname_input" required value="${ currentInfo.firstName }">
					</div>
					<div class="form-group">
						<label for="lastname_input" class="control-label">Last Name</label>
					  	<input type="text" name="lastname" class="form-control" id="lastname_input" required value="${ currentInfo.lastName }">
					</div>
					<div class="form-group">
					  <label for="username_input" class="control-label">Username</label>
					  <input type="text" name="username" class="form-control" id="username_input" required value="${ currentInfo.username }">
					</div>
					<c:if test="${isSubmitted=='INVALID'}">	
						<div class="form-group">
							<span class="label label-danger label-center-panel">This username is taken.</span>
						</div>
					</c:if>
					<div class="form-group">
					  <label for="email_input" class="control-label">Email</label>
					  <input type="email" name="email" class="form-control" id="email_input" required value="${ currentInfo.email }">
					</div>
					<div class="form-group">
					  <label for="temporary_password" class="control-label">Temporary Password</label>
					  <input type="password" name="password" class="form-control password-input" id="temporary_password" required>
					</div>
					<div class="form-group">
						<button type="submit" class="btn btn-lg btn-success btn-block">Register</button>
					</div>							
				</form>
			</div>
			<div class="panel-footer">
			</div>
		</div>
	</div>
</body>
</html>