<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="shared/header.jsp"%>
<!-- File input CSS -->
<link rel="stylesheet" href="resources/bootstrap-fileinput/css/fileinput.min.css">
<!--  File input JavaScript  -->
<script src="resources/bootstrap-fileinput/js/fileinput.min.js"></script>
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
		<span class="label label-success label-panel">Request resolved successfully.</span>
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
				<span class="label label-primary label-title label-panel">Request</span>
			</div>
			<div class="panel-body">
				<div class="form-group">
					<label for="reason_input" class="control-label">Reason</label>
				  	<input type="text" class="form-control" id="reason_input" value="${ currentReimbursement.type.type }" disabled>
				</div>
				<div class="form-group">
					<label for="amount_input" class="control-label">Amount</label>
				  	<input type="text" class="form-control" id="amount_input" value="${ currentReimbursement.amount }" disabled>
				</div>
				<div class="form-group">
					<label for="requested_input" class="control-label">Requested In</label>
				  	<input type="text" class="form-control" id="requested_input" value="${ currentReimbursement.requested }" disabled>
				</div>
				<c:if test="${ currentReimbursement.status != 'Pending' }">
					<div class="form-group">
						<label for="resolved_input" class="control-label">Resolved In</label>
						<input type="text" class="form-control" id="resolved_input" value="${ currentReimbursement.resolved }" disabled>
					</div>
					<c:if test="${ loggedUser.userRole == 'MANAGER' }">
						<div class="form-group">
							<label for="manager_input" class="control-label">Resolved By</label>
							<input type="text" class="form-control" id="manager_input" value="${ currentReimbursement.manager.firstName } ${ currentReimbursement.manager.lastName }" disabled>
						</div>
					</c:if>
				</c:if>
				<div class="form-group">
					<label for="description_input" class="control-label">Description</label>
				  	<textarea class="form-control" id="description_input" disabled>${ currentReimbursement.description }</textarea>
				</div>				
				<div class="form-group">
					<label for="status_input" class="control-label">Status</label>					
					<c:if test="${currentReimbursement.status == 'Pending'}">
						<button class="form-control btn btn-warning" id="status_input" value="" disabled>${ currentReimbursement.status }</button>
					</c:if>
					<c:if test="${currentReimbursement.status == 'Approved'}">
						<button class="form-control btn btn-success" id="status_input" value="" disabled>${ currentReimbursement.status }</button>
					</c:if>
					<c:if test="${currentReimbursement.status == 'Declined'}">
						<button class="form-control btn btn-danger" id="status_input" value="" disabled>${ currentReimbursement.status }</button>
					</c:if>
				</div>
				<div class="form-group">
					<label class="control-label">Proof</label>
					<input name="receipt" id="receipt_input" type="file" class="file" data-show-upload="false">
					<input id="imageBlob" value="${currentReimbursement.receipt}" hidden="hidden"/>
					<script src="resources/js/viewRequest.js"></script>
				</div>
			</div>
			<div class="panel-footer">
			<c:if test="${ loggedUser.userRole == 'MANAGER' }">
				<c:if test="${ currentReimbursement.status == 'Pending' }">
					<form  id="submit_form" class="form-horizontal" method="POST" action="viewRequest.do">
						<input id="rStatus" name="reimbursementStatus" hidden=hidden/>
						<div class="form-group">
							<button id="approve_button" class="btn btn-lg btn-success btn-block" onclick="setReimbursementStatus('Approved')">Approve</button>
							<button id="decline_button" class="btn btn-lg btn-danger btn-block btn-footer" onclick="setReimbursementStatus('Declined')">Decline</button>
						</div>	
					</form>
				</c:if>
			</c:if>
			</div>
		</div>
	</div>
</body>
</html>