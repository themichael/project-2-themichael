<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<nav class="navbar navbar-default">
	<div class="container-fluid">
		<div class="navbar-header">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
				  <span class="sr-only">Toggle navigation</span>
				  <span class="icon-bar"></span>
				  <span class="icon-bar"></span>
				  <span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="/ERS/managerHome.do"><strong>${ loggedUser.username }</strong></a>
			</div>
		</div>
		<div class="collapse navbar-collapse">
		 <ul class="nav navbar-nav">
	        <li class="dropdown">
	          <a class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Information<span class="caret"></span></a>
	          <ul class="dropdown-menu">
	            <li><a href="/ERS/updateInfo.do">Update</a></li>
	            <li><a href="/ERS/changePassword.do">Change Password</a></li>
	          </ul>
	        </li>
	      </ul>
	      <ul class="nav navbar-nav">
	        <li class="dropdown">
	          <a class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Employees<span class="caret"></span></a>
	          <ul class="dropdown-menu">	            
	            <li><a href="/ERS/viewAllEmployees.do">View All</a></li>
	            <li><a href="/ERS/registerEmployee.do">Register</a></li>
	          </ul>
	        </li>
	      </ul>
	      <ul class="nav navbar-nav">
	        <li class="dropdown">
	          <a class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Requests<span class="caret"></span></a>
	          <ul class="dropdown-menu">	            
	            <li><a href="/ERS/viewAllPending.do">Pending</a></li>
	            <li><a href="/ERS/viewAllResolved.do">Resolved</a></li>
	          </ul>
	        </li>
	      </ul>
	      <ul class="nav navbar-nav navbar-right">
	        <li><a href="/ERS/logout.do">Logout</a></li>
	      </ul>
	    </div><!-- /.navbar-collapse -->
	  </div><!-- /.container-fluid -->
</nav>