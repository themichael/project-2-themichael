package com.revature.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.revature.exception.InvalidInputException;
import com.revature.model.Reimbursement;
import com.revature.model.ReimbursementType;
import com.revature.model.User;
import com.revature.service.ReimbursementService;
import com.revature.util.FinalUtil;

public class SubmitRequestController {

	public static String submitRequest(HttpServletRequest request) {
		User loggedUser = (User) request.getSession().getAttribute("loggedUser");

		//Check if user is authenticated
		if(loggedUser == null) {
			return "/ERS/login.do" + FinalUtil.DEFAULT_REGEX + FinalUtil.VALUE_REDIRECT;
		}
		// Check if user is authorized
		else if(loggedUser.getUserRole().equals(FinalUtil.USER_TYPE_MANAGER)) {
			return "403.jsp" + FinalUtil.DEFAULT_REGEX + FinalUtil.VALUE_FORWARD;
		}		
		
		// Check type of HTTP request
		if(request.getMethod().equals(FinalUtil.HTTP_GET)) {
			List<ReimbursementType> reimbursementTypes =  ReimbursementService.getReimbursementService().getReimbursementTypes();
			request.getSession().setAttribute("reimbursementTypes", reimbursementTypes);
			if(reimbursementTypes.size() > 0) {
				request.getSession().setAttribute("defaultReimbursementType", reimbursementTypes.get(0).getType());
			}
			request.getSession().setAttribute("isSubmitted", FinalUtil.EMPTY_STRING);
			return "submitRequest.jsp" + FinalUtil.DEFAULT_REGEX + FinalUtil.VALUE_FORWARD;
		}
				
		Reimbursement reimbursementRequest = new Reimbursement(
				request.getParameter("description"),
				FinalUtil.REIMBURSEMENT_TYPE_PENDING,
				(User) request.getSession().getAttribute("loggedUser"),
				new ReimbursementType(request.getParameter("reimbursementType")),
				Double.parseDouble(request.getParameter("amount")),
				new StringBuilder(request.getParameter("receiptBlob"))
				);

		try {
			if(ReimbursementService.getReimbursementService().submitReimbursementRequest(reimbursementRequest)) {
				request.getSession().setAttribute("isSubmitted", FinalUtil.TRUE_STRING);
				return "submitRequest.jsp" + FinalUtil.DEFAULT_REGEX + FinalUtil.VALUE_REDIRECT;
			}
			request.getSession().setAttribute("isSubmitted", FinalUtil.FALSE_STRING);
			return "submitRequest.jsp" + FinalUtil.DEFAULT_REGEX + FinalUtil.VALUE_FORWARD;			
		} catch (InvalidInputException e) {
			request.getSession().setAttribute("isSubmitted", FinalUtil.FALSE_STRING);
			return "submitRequest.jsp" + FinalUtil.DEFAULT_REGEX + FinalUtil.VALUE_FORWARD;
		}
	}
}
