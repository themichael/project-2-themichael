package com.revature.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.revature.model.Reimbursement;
import com.revature.model.User;
import com.revature.service.ReimbursementService;
import com.revature.service.UserService;
import com.revature.util.FinalUtil;

public class ViewEmployeesController {
	
	@SuppressWarnings("unchecked")
	public static String viewAllEmployees(HttpServletRequest request) {
		User loggedUser = (User) request.getSession().getAttribute("loggedUser");

		//Check if user is authenticated
		if(loggedUser == null) {
			return "/ERS/login.do" + FinalUtil.DEFAULT_REGEX + FinalUtil.VALUE_REDIRECT;
		}
		// Check if user is authorized
		else if(loggedUser.getUserRole().equals(FinalUtil.USER_TYPE_EMPLOYEE)) {
			return "403.jsp" + FinalUtil.DEFAULT_REGEX + FinalUtil.VALUE_FORWARD;
		}		

		// Check type of HTTP request
		if(request.getMethod().equals(FinalUtil.HTTP_GET)) {
			request.getSession().setAttribute("employeeList", UserService.getUserService().getAllEmployees());
			return "viewAllEmployees.jsp" + FinalUtil.DEFAULT_REGEX + FinalUtil.VALUE_FORWARD;
		}
		
		int employeeIndex = Integer.parseInt(request.getParameter("employeeIndex"));
		List<User> employeeList = (List<User>) (request.getSession().getAttribute("employeeList"));
		List<Reimbursement> allUserRequests = ReimbursementService.getReimbursementService().getUserPendingRequests(employeeList.get(employeeIndex).getId());
		allUserRequests.addAll(ReimbursementService.getReimbursementService().getUserResolvedRequests(employeeList.get(employeeIndex).getId()));

		request.getSession().setAttribute("reimbursementList", allUserRequests);
		request.getSession().setAttribute("viewingEmployee",employeeList.get(employeeIndex));
		
		return "viewRequestsEmployee.jsp" + FinalUtil.DEFAULT_REGEX + FinalUtil.VALUE_FORWARD;
	}
}
