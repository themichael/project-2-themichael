<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="shared/header.jsp"%>
</head>
<body class="ers">
<%@ include file="shared/employeeNavbar.jsp"%>
<div class="col-md-4 col-md-offset-4">
	<div class="panel panel-default">
		<div class="panel-heading">
			<span class="label label-primary label-title label-panel">As an Employee you can</span>
		</div>
			<div class="panel-body">
				<div class="form-group">		
					<span class="label label-success label-sub-title label-panel-body">View/Update your information</span>
					<a href="/ERS/updateInfo.do"><img src="resources/images/update-info.png" class="center-image"/></a>
				</div>
				<hr>
				<div class="form-group">	
					<span class="label label-success label-sub-title label-panel-body">Submit a reimbursement request</span>
					<a href="/ERS/submitRequest.do"><img src="resources/images/request.png" class="center-image"/></a>
				</div>
				<hr>
				<div class="form-group">	
					<span class="label label-success label-sub-title label-panel-body">View your pending requests</span>
					<a href="/ERS/viewPendingEmployee.do"><img src="resources/images/pending.png" class="center-image"/></a>
				</div>
				<hr>
				<div class="form-group">	
					<span class="label label-success label-sub-title label-panel-body">View your resolved requests</span>
					<a href="/ERS/viewResolvedEmployee.do"><img src="resources/images/resolved.png" class="center-image"/></a>
				</div>
			</div>		
		<div class="panel-footer">
		</div>		
	</div>
</div>
</body>
</html>