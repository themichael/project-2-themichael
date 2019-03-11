package com.revature.controller;

import javax.servlet.http.HttpServletRequest;

import com.revature.util.FinalUtil;
import com.revature.util.LogUtil;

public class ErrorController {
	
	public static String showError(HttpServletRequest request) {
		if(request.getRequestURI().equals("/ERS/404.do")) {
			return "404.jsp" + FinalUtil.DEFAULT_REGEX + FinalUtil.VALUE_FORWARD;
		}
		else if(request.getRequestURI().equals("/ERS/403.do")) {
			return "403.jsp" + FinalUtil.DEFAULT_REGEX + FinalUtil.VALUE_FORWARD;
		}
		else {
			LogUtil.logger.warn("An exception has been thrown in a jsp.");
			return "oops.jsp" + FinalUtil.DEFAULT_REGEX + FinalUtil.VALUE_FORWARD;
		}
	}
}
