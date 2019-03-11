package com.revature.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.revature.model.Reimbursement;
import com.revature.model.User;
import com.revature.service.ReimbursementService;
import com.revature.service.SendEmailService;
import com.revature.threads.SendEmailThread;
import com.revature.util.FinalUtil;
import com.revature.util.MailUtil;

public class ViewSingleRequestController {
	
	@SuppressWarnings("unchecked")
	public static String viewRequest(HttpServletRequest request) {
		User loggedUser = (User) request.getSession().getAttribute("loggedUser");

		//Check if user is authenticated
		if(loggedUser == null) {
			return "/ERS/login.do" + FinalUtil.DEFAULT_REGEX + FinalUtil.VALUE_REDIRECT;
		}

		if(request.getParameter("reimbursementStatus") == null) {
			request.getSession().setAttribute("isUpdated", FinalUtil.EMPTY_STRING);
			
			List<Reimbursement> reimbursementList = (List<Reimbursement>) request.getSession().getAttribute("reimbursementList");
			
			int reimbursementId = Integer.parseInt(request.getParameter("reimbursementId"));
			for(Reimbursement reimbursement : reimbursementList) {
				if(reimbursement.getId() == reimbursementId) {				
					request.getSession().setAttribute("currentReimbursement", reimbursement);
					break;
				}
			}
			
			return "viewRequest.jsp" + FinalUtil.DEFAULT_REGEX + FinalUtil.VALUE_FORWARD;
		}

		Reimbursement currentReimbursement = (Reimbursement) request.getSession().getAttribute("currentReimbursement");	
		currentReimbursement.setStatus(request.getParameter("reimbursementStatus"));
		currentReimbursement.setManager(loggedUser);

		if(ReimbursementService.getReimbursementService().resolveRequest(currentReimbursement)) {
			//Send to the user that his request has been resolved
			new SendEmailThread(
					new SendEmailService(currentReimbursement.getEmployee().getEmail()), 
					MailUtil.REQUEST_RESOLVED_SUBJECT, 
					MailUtil.getRequestResolvedMessage(currentReimbursement.getEmployee())
					).start();
			
			request.getSession().setAttribute("isUpdated", FinalUtil.TRUE_STRING);
			request.getSession().setAttribute("currentReimbursement", currentReimbursement);
			return "viewRequest.jsp" + FinalUtil.DEFAULT_REGEX + FinalUtil.VALUE_FORWARD; 
		}
		else {
			request.getSession().setAttribute("isUpdated", FinalUtil.FALSE_STRING);
			return "viewRequest.jsp" + FinalUtil.DEFAULT_REGEX + FinalUtil.VALUE_FORWARD;
		}		
	}
}
