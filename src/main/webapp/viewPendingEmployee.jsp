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
<%@ include file="shared/employeeNavbar.jsp"%>
	<div class="col-md-4 col-md-offset-4">
		<div class="panel panel-default">
			<div class="panel-heading">
				<span class="label label-primary label-title label-panel">Pending Requests</span>
			</div>
			<div class="panel-body">
				<c:if test="${ reimbursementList.size() == 0 }">
					<div class="form-group">
						<span class="label label-warning label-panel label-info">There are no requests to show.</span>
					</div>
				</c:if>
				<ul class="list-group">
					<c:forEach var="r" items="${ reimbursementList }" >
						<button class="list-group-item" onclick="setReimbursementId(${ r.id })">
						<h4 class="list-group-item-heading"><b>Reason: </b>${ r.type.type }</h4>
						<p class="list-group-item-text">
							<b>On: </b>${ r.requested }
						</p>
						<p class="list-group-item-text">					
							<b>Description: </b>
							<c:if test="${ r.description.length() < 15 }">
								${ r.description }
							</c:if>
							<c:if test="${ r.description.length() >= 15 }">
								${ r.description.substring(0,15) }<b>...</b>
							</c:if>
						</p>						
						</button>
					</c:forEach>
				</ul>
			</div>
			<div class="panel-footer">
				<form class="form-horizontal" method="POST" action="viewRequest.do">
					<input id="rId" name="reimbursementId" hidden=hidden/>
					<div class="form-group">
						<button id="view_button" type="submit" class="btn btn-lg btn-success btn-block btn-footer">View</button>
					</div>	
				</form>
			</div>
		</div>
	</div>
</body>
</html>