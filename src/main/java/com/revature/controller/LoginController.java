package com.revature.controller;

import javax.servlet.http.HttpServletRequest;

import com.revature.exception.LoginFailedException;
import com.revature.model.User;
import com.revature.service.UserService;
import com.revature.util.FinalUtil;

public class LoginController {
	
	public static String login(HttpServletRequest request) {
		//It's a get request
		if(request.getMethod().equals(FinalUtil.HTTP_GET)) {
			request.getSession().setAttribute("isAuthenticated", FinalUtil.VALIDATING_STRING);
			return "login.jsp" + FinalUtil.DEFAULT_REGEX + FinalUtil.VALUE_FORWARD;
		}
		
		try {
			User loggedUser = UserService.getUserService().login(new User(request.getParameter("username"), request.getParameter("password")));
			
			request.getSession().setAttribute("loggedUser", loggedUser);
			request.getSession().setAttribute("isAuthenticated", FinalUtil.TRUE_STRING);
			
			if(loggedUser.getUserRole().equals(FinalUtil.USER_TYPE_EMPLOYEE)) {
				return "/ERS/employeeHome.do" + FinalUtil.DEFAULT_REGEX + FinalUtil.VALUE_REDIRECT;
			}
			else {
				return "/ERS/managerHome.do" + FinalUtil.DEFAULT_REGEX + FinalUtil.VALUE_REDIRECT;
			}
		} catch (LoginFailedException e) {
			request.getSession().setAttribute("isAuthenticated", FinalUtil.FALSE_STRING);
			return "login.jsp" + FinalUtil.DEFAULT_REGEX + FinalUtil.VALUE_FORWARD;
		}
	}
	
	public static String logout(HttpServletRequest request) {
		request.getSession().invalidate();
		
		return "/ERS/login.do" + FinalUtil.DEFAULT_REGEX + FinalUtil.VALUE_REDIRECT;
	}
}
