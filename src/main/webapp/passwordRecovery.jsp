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
	<c:if test="${isFound=='TRUE'}">	
	<div class="form-group">
		<span class="label label-success label-center-panel">If the username inserted exists, check your email for further instructions.</span>
	</div>
	</c:if>
	<div class="col-md-4 col-md-offset-4">
		<div class="panel panel-default">
			<div class="panel-heading">
				<span class="label label-primary label-title label-panel">Password Recovery</span>
			</div>
			<div class="panel-body">
				<form class="form-horizontal" method="POST" action="passwordRecovery.do">
					<div class="form-group">
					  <label for="username_input" class="control-label">Username</label>
					  <input type="text" name="username" class="form-control" id="username_input" required>
					</div>
					<div class="form-group">
						<button type="submit" class="btn btn-lg btn-success btn-block">Recover</button>
					</div>							
				</form>
			</div>
			<div class="panel-footer">
			</div>
		</div>
	</div>
</body>
</html>