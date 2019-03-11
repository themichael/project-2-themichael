package com.revature.controller;

import javax.servlet.http.HttpServletRequest;

import com.revature.model.User;
import com.revature.service.UserService;
import com.revature.util.FinalUtil;

public class PasswordResetController {

	public static String resetPassword(HttpServletRequest request) {
		// Check type of HTTP request
		if(request.getMethod().equals(FinalUtil.HTTP_GET)) {
			String username = UserService.getUserService().getUsernameFromToken(request.getParameter("t"));
			request.getSession().setAttribute("isUpdated", FinalUtil.EMPTY_STRING);
			
			// Token exists
			if(!username.equals(FinalUtil.EMPTY_STRING)) {
				request.getSession().setAttribute("isFound", FinalUtil.TRUE_STRING);				
				request.getSession().setAttribute("currentUsernameRecovery", username);
				return "passwordReset.jsp" + FinalUtil.DEFAULT_REGEX + FinalUtil.VALUE_FORWARD;
			}
			
			request.getSession().setAttribute("isFound", FinalUtil.FALSE_STRING);
			return "passwordReset.jsp" + FinalUtil.DEFAULT_REGEX + FinalUtil.VALUE_FORWARD;
		}

		//Reset password
		User user = UserService.getUserService().getUserInfo(request.getSession().getAttribute("currentUsernameRecovery").toString());
		user.setPassword(request.getParameter("newPassword"));
		
		if(UserService.getUserService().changePassword(user)) {
			request.getSession().setAttribute("isUpdated", FinalUtil.TRUE_STRING);
			//Delete current and old tokens
			UserService.getUserService().deleteUserTokens(user);
			return "passwordReset.jsp" + FinalUtil.DEFAULT_REGEX + FinalUtil.VALUE_FORWARD;
		}
		
		request.getSession().setAttribute("isUpdated", FinalUtil.FALSE_STRING);
		return "passwordReset.jsp" + FinalUtil.DEFAULT_REGEX + FinalUtil.VALUE_FORWARD;
	}
}
