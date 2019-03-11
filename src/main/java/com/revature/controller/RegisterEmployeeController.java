package com.revature.controller;

import javax.servlet.http.HttpServletRequest;

import com.revature.model.User;
import com.revature.service.SendEmailService;
import com.revature.service.UserService;
import com.revature.threads.SendEmailThread;
import com.revature.util.FinalUtil;
import com.revature.util.MailUtil;

public class RegisterEmployeeController {

	public static String registerEmployee(HttpServletRequest request) {
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
			request.getSession().setAttribute("isSubmitted", FinalUtil.EMPTY_STRING);
			request.getSession().setAttribute("currentInfo", new User());
			return "registerEmployee.jsp" + FinalUtil.DEFAULT_REGEX + FinalUtil.VALUE_FORWARD;
		}

		User employee = new User(0,
				request.getParameter("firstname"),
				request.getParameter("lastname"),
				request.getParameter("username"),
				request.getParameter("password"),
				request.getParameter("email"),
				FinalUtil.USER_TYPE_EMPLOYEE
				);

		//Check if username is taken
		if(UserService.getUserService().isUsernameTaken(employee.getUsername())) {
			request.getSession().setAttribute("isSubmitted", FinalUtil.INVALID_STRING);
			request.getSession().setAttribute("currentInfo", employee);
			return "registerEmployee.jsp" + FinalUtil.DEFAULT_REGEX + FinalUtil.VALUE_FORWARD;
		}
		
		//Store user in database
		if(UserService.getUserService().registerEmployee(employee)) {
			//Send temporary password to user
			new SendEmailThread(
					new SendEmailService(employee.getEmail()), 
					MailUtil.TEMPORARY_PASSWORD_SUBJECT, 
					MailUtil.getTemporaryPasswordMessage(employee, request)
					).start();

			request.getSession().setAttribute("isSubmitted", FinalUtil.TRUE_STRING);
			request.getSession().setAttribute("currentInfo", new User());
			return "registerEmployee.jsp" + FinalUtil.DEFAULT_REGEX + FinalUtil.VALUE_FORWARD;
		}
		else {
			request.getSession().setAttribute("isSubmitted", FinalUtil.FALSE_STRING);
			request.getSession().setAttribute("currentInfo", employee);
			return "registerEmployee.jsp" + FinalUtil.DEFAULT_REGEX + FinalUtil.VALUE_FORWARD;
		}
	}
}
