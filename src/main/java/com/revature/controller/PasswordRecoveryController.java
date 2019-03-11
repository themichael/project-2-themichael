package com.revature.controller;

import javax.servlet.http.HttpServletRequest;

import com.revature.model.User;
import com.revature.service.SendEmailService;
import com.revature.service.UserService;
import com.revature.threads.SendEmailThread;
import com.revature.util.FinalUtil;
import com.revature.util.MailUtil;

public class PasswordRecoveryController {
	
	public static String recoverPassword(HttpServletRequest request) {
		// Check type of HTTP request
		if(request.getMethod().equals(FinalUtil.HTTP_GET)) {
			request.getSession().setAttribute("isFound", FinalUtil.EMPTY_STRING);
			return "passwordRecovery.jsp" + FinalUtil.DEFAULT_REGEX + FinalUtil.VALUE_FORWARD;
		}
		
		User user = UserService.getUserService().getUserInfo(request.getParameter("username"));
		
		//If the user exists we create a token and send him an email
		if(!user.getUsername().equals(FinalUtil.EMPTY_STRING)) {
			String token = UserService.getUserService().createPasswordToken(user);
			
			//Send password reset link to user
			new SendEmailThread(
					new SendEmailService(user.getEmail()), 
					MailUtil.PASSWORD_RECOVERY_SUBJECT, 
					MailUtil.getPasswordRecoveryMessage(user, token, request)
					).start();
		}
		
		request.getSession().setAttribute("isFound", FinalUtil.TRUE_STRING);
		return "passwordRecovery.jsp" + FinalUtil.DEFAULT_REGEX + FinalUtil.VALUE_FORWARD;
	}
}
