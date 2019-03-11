package com.revature.controller;

import javax.servlet.http.HttpServletRequest;

import com.revature.model.User;
import com.revature.util.FinalUtil;

public class HomeController {
	public static String showEmployeeHome(HttpServletRequest request) {
		User loggedUser = (User) request.getSession().getAttribute("loggedUser");
		
		//Check if user is authenticated
		if(loggedUser == null) {
			return "/ERS/login.do" + FinalUtil.DEFAULT_REGEX + FinalUtil.VALUE_REDIRECT;
		}
		// Check if user is authorized
		else if(loggedUser.getUserRole().equals(FinalUtil.USER_TYPE_MANAGER)) {
			return "403.jsp" + FinalUtil.DEFAULT_REGEX + FinalUtil.VALUE_FORWARD;
		}
		else {
			return "employeeHome.jsp" + FinalUtil.DEFAULT_REGEX + FinalUtil.VALUE_FORWARD;
		}
	}
	
	public static String showManagerHome(HttpServletRequest request) {
		User loggedUser = (User) request.getSession().getAttribute("loggedUser");
		
		//Check if user is authenticated
		if(loggedUser == null) {
			return "/ERS/login.do" + FinalUtil.DEFAULT_REGEX + FinalUtil.VALUE_REDIRECT;
		}
		// Check if user is authorized
		else if(loggedUser.getUserRole().equals(FinalUtil.USER_TYPE_EMPLOYEE)) {
			return "403.jsp" + FinalUtil.DEFAULT_REGEX + FinalUtil.VALUE_FORWARD;
		}
		else {
			return "managerHome.jsp" + FinalUtil.DEFAULT_REGEX + FinalUtil.VALUE_FORWARD;
		}
	}
}
