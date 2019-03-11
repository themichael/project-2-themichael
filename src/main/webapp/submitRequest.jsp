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
<!--  SubmitRequest JavaScript  -->
<script src="resources/js/submitRequest.js"></script>
</head>
<body onsubmit="findImageBlob('panel','img')">
<%@ include file="shared/employeeNavbar.jsp"%>
<c:choose>
	<c:when test="${isSubmitted =='TRUE'}">
	<div class="form-group">
		<span class="label label-success label-panel">Request submitted successfully.</span>
	</div>
	</c:when>
	<c:when test="${isSubmitted =='FALSE'}">
	<div class="form-group">
		<span class="label label-danger label-panel">There was an error processing the request.</span>
	</div>
	</c:when>
</c:choose>
	<div id="panel" class="col-md-4 col-md-offset-4">
		<div class="panel panel-default">
			<div class="panel-heading">
				<span class="label label-primary label-title label-panel">Submit a Request</span>
			</div>
			<div class="panel-body">
				<form class="form-horizontal" method="POST" action="submitRequest.do">
					<div class="form-group">
						<div class="dropdown">
							<label for="dropdown-type" class="control-label">Reason</label>
							<button class="btn btn-primary dropdown-toggle dropdown-btn" type="button" id="dropdown-type" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
						 		${ defaultReimbursementType }
						 		 <span class="caret"></span>					 	
							</button>
						 	<ul class="dropdown-menu dropdown-panel" aria-labelledby="dropdown-type">
								<c:forEach var="r" items="${ reimbursementTypes }" >
									<li onclick="changeDropdown('${ r.type }')"><a>${ r.type }</a></li>
								</c:forEach>
							</ul>
						</div>
					</div>
					<div class="form-group">
					  <label for="amount_input" class="control-label">Amount</label>
					  <input type="number" step="0.01" name="amount" class="form-control" id="amount_input" required>
					</div>
					<div class="form-group">
					  <label for="description_input" class="control-label">Description</label>
					  <textarea name="description" class="form-control" id="description_input" required></textarea>
					</div>
					<div class="form-group">
						<label for="receipt_input" class="control-label">Proof</label>
						<input name="receipt" id="receipt_input" type="file" class="file" accept="image/*" data-show-upload="false" data-allowed-file-extensions='["jpg", "png"]' required>
					</div>
					<input id="rType" name="reimbursementType" hidden=hidden value="${ defaultReimbursementType }"/>
					<input id="rBlob" name="receiptBlob" hidden=hidden />
					<div class="form-group">
						<button type="submit" class="btn btn-lg btn-success btn-block">Submit</button>
					</div>										
				</form>				
			</div>
			<div class="panel-footer">
			</div>
		</div>
	</div>
</body>
</html>