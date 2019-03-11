package com.revature.util;

import javax.servlet.http.HttpServletRequest;

import com.revature.model.User;

public final class MailUtil {
	public static final String TEMPORARY_PASSWORD_SUBJECT = "ERS - Registration";
	public static final String REQUEST_RESOLVED_SUBJECT = "ERS - Request Resolved";
	public static final String PASSWORD_RECOVERY_SUBJECT = "ERS - Password Recovery";
	
	public static String getTemporaryPasswordMessage(User user, HttpServletRequest request) {
		String loginUrl = request.getScheme() + "://" + 
						request.getServerName() + ":" + 
						request.getServerPort() + 
						request.getContextPath() +
						"/login.do";
		
		String message = "Hello, "+user.getFirstName()+" "+user.getLastName()+" \n"+
				"Your account was created successfully for ERS platform. \n"+
				"Your username is: "+user.getUsername()+" \n"+
				"Your temporary password is: "+user.getPassword()+" \n"+
				"Please login at: "+ loginUrl +" and then you can change your password afterwards. \n"+
				"Regards,\n"+
				"The ERS Team.";
		
		return message;
	}
	
	public static String getRequestResolvedMessage(User user) {
		String message = "Hello, "+user.getFirstName()+" "+user.getLastName()+" \n"+
				"One of your reimbursement requests was resolved. \n"+
				"Regards,\n"+
				"The ERS Team.";
		
		return message;
	}
	
	public static String getPasswordRecoveryMessage(User user, String token, HttpServletRequest request) {
		String passwordResetUrl = request.getScheme() + "://" + 
				request.getServerName() + ":" + 
				request.getServerPort() + 
				request.getContextPath() +
				"/passwordReset.do?t=" +
				token;
		
		String message = "Hello, "+user.getFirstName()+" "+user.getLastName()+" \n"+
				"You are trying to recover your password. \n"+
				"Please go to: " + passwordResetUrl + " to reset your password. \n"+
				"Regards,\n"+
				"The ERS Team.";
		
		return message;
	}
}
