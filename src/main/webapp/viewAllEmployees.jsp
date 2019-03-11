<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="shared/header.jsp"%>
</head>
<body onload="disableViewButton()">
<%@ include file="shared/managerNavbar.jsp"%>
	<div class="col-md-4 col-md-offset-4">
		<div class="panel panel-default">
			<div class="panel-heading">
				<span class="label label-primary label-title label-panel">Employees</span>
			</div>
			<div class="panel-body">
				<c:if test="${ employeeList.size() == 0 }">
					<div class="form-group">
						<span class="label label-warning label-panel label-info">There are no employees to show.</span>
					</div>
				</c:if>
				<ul class="list-group">
					<c:forEach var="e" items="${ employeeList }" varStatus="loop" >
						<button class="list-group-item" onclick="setEmployeeId(${ loop.index })">
						<h4 class="list-group-item-heading"><b>User: </b>${ e.username }</h4>
						<p class="list-group-item-text">
							<b>First name: </b>${ e.firstName }
						</p>
						<p class="list-group-item-text">
							<b>Last name: </b>${ e.lastName }
						</p>
						<p class="list-group-item-text">
							<b>Email: </b>${ e.email } 
						</p>
						</button>
					</c:forEach>
				</ul>
			</div>
			<div class="panel-footer">
				<form class="form-horizontal" method="POST" action="viewAllEmployees.do">
					<input id="eIndex" name="employeeIndex" hidden=hidden/>
					<div class="form-group">
						<button id="view_button" type="submit" class="btn btn-lg btn-success btn-block btn-footer">View Requests</button>
					</div>	
				</form>
			</div>
		</div>
	</div>
</body>
</html>