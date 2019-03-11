<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="shared/header.jsp"%>
</head>
<body class="ers">
<%@ include file="shared/managerNavbar.jsp"%>
<div class="col-md-4 col-md-offset-4">
	<div class="panel panel-default">
		<div class="panel-heading">
			<span class="label label-primary label-title label-panel">As a Manager you can</span>
		</div>
			<div class="panel-body">
				<div class="form-group">	
					<span class="label label-success label-sub-title label-panel-body">View/Resolve all pending requests</span>
					<a href="/ERS/viewAllPending.do"><img src="resources/images/pending.png" class="center-image"/></a>
				</div>
				<hr>
				<div class="form-group">	
					<span class="label label-success label-sub-title label-panel-body">View all resolved requests</span>
					<a href="/ERS/viewAllResolved.do"><img src="resources/images/resolved.png" class="center-image"/></a>
				</div>
				<hr>
				<div class="form-group">		
					<span class="label label-success label-sub-title label-panel-body">View/Update your information</span>
					<a href="/ERS/updateInfo.do"><img src="resources/images/update-info.png" class="center-image"/></a>
				</div>
				<hr>
				<div class="form-group">	
					<span class="label label-success label-sub-title label-panel-body">View all employees</span>
					<a href="/ERS/viewAllEmployees.do"><img src="resources/images/employees.png" class="center-image"/></a>
				</div>
				<hr>				
				<div class="form-group">		
					<span class="label label-success label-sub-title label-panel-body">Register an employee</span>
					<a href="/ERS/registerEmployee.do"><img src="resources/images/register.png" class="center-image"/></a>
				</div>			
			</div>		
		<div class="panel-footer">
		</div>		
	</div>
</div>
</body>
</html>