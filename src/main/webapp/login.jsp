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
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="/ERS/login.do"><strong>Login</strong></a>
			</div>
		</div>
	</nav>
	<div class="col-md-4 col-md-offset-4">
		<div class="panel panel-default">
			<div class="panel-heading">
				<img src="resources/images/logo.png" class="center-image"/>
			</div>
			<div class="panel-body">
				<form class="form-horizontal" method="POST" action="login.do">
					<div class="form-group">
					  <label for="username_input" class="control-label">Username</label>
					  <input type="text" name="username" class="form-control" id="username_input" required>
					</div>
					<div class="form-group">
					  <label for="password_input" class="control-label">Password</label>
					  <input type="password" name="password" class="form-control password-input" id="password_input" required>
					</div>
					<c:if test="${isAuthenticated=='FALSE'}">	
						<div class="form-group">
							<span class="label label-danger label-center-panel">Wrong username and/or password.</span>
						</div>
					</c:if>
					<div class="form-group">
						<button type="submit" class="btn btn-lg btn-success btn-block">Login</button>
					</div>							
				</form>
			</div>
			<div class="panel-footer">
				<a class="anchor-center" href="/ERS/passwordRecovery.do"><strong>Forgot your password?</strong></a>
			</div>
		</div>
	</div>
</body>
</html>