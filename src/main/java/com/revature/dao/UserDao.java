package com.revature.dao;

import java.util.List;

import com.revature.model.User;

public interface UserDao {
	
	public boolean insert(User user);
	public String insertPasswordToken(User user);
	public String checkIfTokenExists(String token);
	public void deleteTokens(User user);
	public User select(String username);
	public List<User> selectAll();
	public String getPasswordHash(String password);
	public boolean update(User user);
}
