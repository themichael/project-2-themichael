package com.revature.controller;

import javax.servlet.http.HttpServletRequest;

import com.revature.model.User;
import com.revature.service.ReimbursementService;
import com.revature.util.FinalUtil;

public class ViewMultipleRequestsController {
	
	public static String viewRequests(HttpServletRequest request) {
		User loggedUser = (User) request.getSession().getAttribute("loggedUser");

		//Check if user is authenticated
		if(loggedUser == null) {
			return "/ERS/login.do" + FinalUtil.DEFAULT_REGEX + FinalUtil.VALUE_REDIRECT;
		}

		if(request.getRequestURI().equals("/ERS/viewPendingEmployee.do")) {
			request.getSession().setAttribute("reimbursementList", ReimbursementService.getReimbursementService().getUserPendingRequests(loggedUser.getId()));
			return "viewPendingEmployee.jsp" + FinalUtil.DEFAULT_REGEX + FinalUtil.VALUE_FORWARD;
		}
		else if (request.getRequestURI().equals("/ERS/viewResolvedEmployee.do")) {
			request.getSession().setAttribute("reimbursementList", ReimbursementService.getReimbursementService().getUserResolvedRequests(loggedUser.getId()));
			return "viewResolvedEmployee.jsp" + FinalUtil.DEFAULT_REGEX + FinalUtil.VALUE_FORWARD;
		}
		else if (request.getRequestURI().equals("/ERS/viewAllPending.do")) {
			request.getSession().setAttribute("reimbursementList", ReimbursementService.getReimbursementService().getAllPendingRequests());
			return "viewAllPending.jsp" + FinalUtil.DEFAULT_REGEX + FinalUtil.VALUE_FORWARD;
		}
		else if (request.getRequestURI().equals("/ERS/viewAllResolved.do")) {
			request.getSession().setAttribute("reimbursementList", ReimbursementService.getReimbursementService().getAllResolvedRequests());
			return "viewAllResolved.jsp" + FinalUtil.DEFAULT_REGEX + FinalUtil.VALUE_FORWARD;
		}
		else {
			return "/ERS/login.do" + FinalUtil.DEFAULT_REGEX + FinalUtil.VALUE_REDIRECT;
		}
	}
}
