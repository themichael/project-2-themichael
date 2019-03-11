package com.revature.request;

import javax.servlet.http.HttpServletRequest;

import com.revature.controller.ErrorController;
import com.revature.controller.HomeController;
import com.revature.controller.LoginController;
import com.revature.controller.PasswordRecoveryController;
import com.revature.controller.PasswordResetController;
import com.revature.controller.RegisterEmployeeController;
import com.revature.controller.SubmitRequestController;
import com.revature.controller.UpdateInfoController;
import com.revature.controller.ViewEmployeesController;
import com.revature.controller.ViewMultipleRequestsController;
import com.revature.controller.ViewSingleRequestController;

public class RequestHelper {

	private static RequestHelper requestHelper;

	private RequestHelper() {

	}

	public static RequestHelper getRequestHelper() {
		if(requestHelper == null) {
			return new RequestHelper();
		}
		else {
			return requestHelper;
		}
	}

	/* For GET requests */
	public String process(HttpServletRequest request) {
		switch(request.getRequestURI())
		{
		case "/ERS/login.do":
			return LoginController.login(request);
		case "/ERS/logout.do":
			return LoginController.logout(request);
		case "/ERS/employeeHome.do":
			return HomeController.showEmployeeHome(request);
		case "/ERS/managerHome.do":
			return HomeController.showManagerHome(request);
		case "/ERS/submitRequest.do":
			return SubmitRequestController.submitRequest(request);
		case "/ERS/updateInfo.do":
			return UpdateInfoController.updateInfo(request);
		case "/ERS/registerEmployee.do":
			return RegisterEmployeeController.registerEmployee(request);
		case "/ERS/changePassword.do":
			return UpdateInfoController.changePassword(request);
		case "/ERS/viewPendingEmployee.do":
			return ViewMultipleRequestsController.viewRequests(request);		
		case "/ERS/viewResolvedEmployee.do":
			return ViewMultipleRequestsController.viewRequests(request);
		case "/ERS/viewAllPending.do":
			return ViewMultipleRequestsController.viewRequests(request);
		case "/ERS/viewAllResolved.do":
			return ViewMultipleRequestsController.viewRequests(request);
		case "/ERS/viewRequest.do":
			return ViewSingleRequestController.viewRequest(request);
		case "/ERS/viewAllEmployees.do":
			return ViewEmployeesController.viewAllEmployees(request);
		case "/ERS/passwordRecovery.do":
			return PasswordRecoveryController.recoverPassword(request);
		case "/ERS/passwordReset.do":
			return PasswordResetController.resetPassword(request);
		default:
			return ErrorController.showError(request);
		}
	}
}
