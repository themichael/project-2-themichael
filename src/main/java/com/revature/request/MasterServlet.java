package com.revature.request;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.util.FinalUtil;

public class MasterServlet extends HttpServlet {

	private static final long serialVersionUID = 1159764852861289598L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String URI = RequestHelper.getRequestHelper().process(request);
		
		if(URI.split(FinalUtil.DEFAULT_REGEX)[1].equals(FinalUtil.VALUE_FORWARD)) {
			request.getRequestDispatcher(URI.split(FinalUtil.DEFAULT_REGEX)[0]).forward(request, response);
		}
		else {
			response.sendRedirect(URI.split(FinalUtil.DEFAULT_REGEX)[0]);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String URI = RequestHelper.getRequestHelper().process(request);
		
		if(URI.split(FinalUtil.DEFAULT_REGEX)[1].equals(FinalUtil.VALUE_FORWARD)) {
			request.getRequestDispatcher(URI.split(FinalUtil.DEFAULT_REGEX)[0]).forward(request, response);
		}
		else {
			response.sendRedirect(URI.split(FinalUtil.DEFAULT_REGEX)[0]);
		}
	}	
}
