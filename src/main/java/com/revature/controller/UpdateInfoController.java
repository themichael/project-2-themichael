package com.revature.controller;

import javax.servlet.http.HttpServletRequest;

import com.revature.exception.LoginFailedException;
import com.revature.model.User;
import com.revature.service.UserService;
import com.revature.util.FinalUtil;

public class UpdateInfoController {

	public static String updateInfo(HttpServletRequest request) {
		User loggedUser = (User) request.getSession().getAttribute("loggedUser");

		//Check if user is authenticated
		if(loggedUser == null) {
			return "/ERS/login.do" + FinalUtil.DEFAULT_REGEX + FinalUtil.VALUE_REDIRECT;
		}

		// Check type of HTTP request
		if(request.getMethod().equals(FinalUtil.HTTP_GET)) {			
			request.getSession().setAttribute("isUpdated", FinalUtil.EMPTY_STRING);
			request.getSession().setAttribute("updatedUser", loggedUser);
			return "updateInfo.jsp" + FinalUtil.DEFAULT_REGEX + FinalUtil.VALUE_FORWARD;
		}
		
		User updatedUser = new User(
				loggedUser.getId(),
				request.getParameter("firstname"),
				request.getParameter("lastname"),
				request.getParameter("username"),
				loggedUser.getPassword(),
				request.getParameter("email"),
				loggedUser.getUserRole()
				);	
		
		//Check if username is taken
		if(!loggedUser.getUsername().equals(updatedUser.getUsername())) {
			if(UserService.getUserService().isUsernameTaken(request.getParameter("username"))) {
				request.getSession().setAttribute("isUpdated", FinalUtil.INVALID_STRING);				
				request.getSession().setAttribute("updatedUser", updatedUser);
				return "updateInfo.jsp" + FinalUtil.DEFAULT_REGEX + FinalUtil.VALUE_FORWARD;
			}
		}	

		if(UserService.getUserService().updateInfo(updatedUser)) {
			request.getSession().setAttribute("isUpdated", FinalUtil.TRUE_STRING);
			request.getSession().setAttribute("updatedUser", updatedUser);
			request.getSession().setAttribute("loggedUser", updatedUser);
			return "updateInfo.jsp" + FinalUtil.DEFAULT_REGEX + FinalUtil.VALUE_FORWARD;
		}
		else {
			request.getSession().setAttribute("isUpdated", FinalUtil.FALSE_STRING);
			request.getSession().setAttribute("updatedUser", updatedUser);
			return "updateInfo.jsp" + FinalUtil.DEFAULT_REGEX + FinalUtil.VALUE_FORWARD;
		}
	}

	public static String changePassword(HttpServletRequest request) {
		User loggedUser = (User) request.getSession().getAttribute("loggedUser");

		//Check if user is authenticated
		if(loggedUser == null) {
			return "/ERS/login.do" + FinalUtil.DEFAULT_REGEX + FinalUtil.VALUE_REDIRECT;
		}

		// Check type of HTTP request
		if(request.getMethod().equals(FinalUtil.HTTP_GET)) {			
			request.getSession().setAttribute("isUpdated", FinalUtil.EMPTY_STRING);
			return "changePassword.jsp" + FinalUtil.DEFAULT_REGEX + FinalUtil.VALUE_FORWARD;
		}

		loggedUser.setPassword(request.getParameter("currentPassword"));		
		try {
			// Check first if user input correct password
			loggedUser = UserService.getUserService().login(loggedUser);

			loggedUser.setPassword(request.getParameter("newPassword"));
			if(UserService.getUserService().changePassword(loggedUser)) {
				request.getSession().setAttribute("isUpdated", FinalUtil.TRUE_STRING);
				return "changePassword.jsp" + FinalUtil.DEFAULT_REGEX + FinalUtil.VALUE_FORWARD;
			}
			else {
				request.getSession().setAttribute("isUpdated", FinalUtil.FALSE_STRING);
				return "changePassword.jsp" + FinalUtil.DEFAULT_REGEX + FinalUtil.VALUE_FORWARD;	
			}
		} catch (LoginFailedException e) {
			request.getSession().setAttribute("isUpdated", FinalUtil.INVALID_STRING);
			return "changePassword.jsp" + FinalUtil.DEFAULT_REGEX + FinalUtil.VALUE_FORWARD;			
		}
	}
}
