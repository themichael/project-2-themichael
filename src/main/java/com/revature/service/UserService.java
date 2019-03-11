package com.revature.service;

import java.util.List;

import com.revature.dao.UserDaoJdbc;
import com.revature.exception.LoginFailedException;
import com.revature.model.User;
import com.revature.util.FinalUtil;

public class UserService {

	private static UserService userService;

	private UserService() {

	}

	public static UserService getUserService() {
		if(userService == null) {
			return new UserService();
		}
		else {
			return userService;
		}
	}

	/* User is able to login or view his information */
	public User login(User user) throws LoginFailedException {
		User loggedUser = UserDaoJdbc.getUserDao().select(user.getUsername());

		if(user.getUsername().equals(loggedUser.getUsername()) && UserDaoJdbc.getUserDao().getPasswordHash(user.getPassword()).equals(loggedUser.getPassword())) {
			return loggedUser;
		}

		throw new LoginFailedException("Wrong username or password.");
	}

	/* Get user info outside login */
	public User getUserInfo(String username) {
		return UserDaoJdbc.getUserDao().select(username);
	}
	
	/* Create user token */
	public String createPasswordToken(User user) {
		return UserDaoJdbc.getUserDao().insertPasswordToken(user);
	}
	
	/* Get username from token */
	public String getUsernameFromToken(String token) {
		return UserDaoJdbc.getUserDao().checkIfTokenExists(token);
	}
	
	/* Delete tokens */
	public void deleteUserTokens(User user) {
		UserDaoJdbc.getUserDao().deleteTokens(user);
	}

	/* User is able to update his information */
	public boolean updateInfo(User user) {
		return UserDaoJdbc.getUserDao().update(user);
	}

	/* User is able to change his password */
	public boolean changePassword(User user) {
		user.setPassword(UserDaoJdbc.getUserDao().getPasswordHash(user.getPassword()));		
		return UserDaoJdbc.getUserDao().update(user);
	}

	/* The manager is able to register an employee */
	public boolean registerEmployee(User user) {
		return UserDaoJdbc.getUserDao().insert(user);
	}

	/* The manager is able to view all employees */
	public List<User> getAllEmployees() {
		return UserDaoJdbc.getUserDao().selectAll();
	}

	/* Check if username is taken */
	public boolean isUsernameTaken(String username) {
		return !UserDaoJdbc.getUserDao().select(username).getUsername().equals(FinalUtil.EMPTY_STRING);
	}
}
