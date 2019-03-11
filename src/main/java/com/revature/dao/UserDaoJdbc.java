package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.exception.StringEmptyException;
import com.revature.model.User;
import com.revature.util.ConnectionUtil;
import com.revature.util.FinalUtil;
import com.revature.util.LogUtil;
import com.revature.util.StringUtil;

public class UserDaoJdbc implements UserDao {

	private static UserDao userDaoSingleton;

	private UserDaoJdbc() {

	}

	public static UserDao getUserDao() {
		if(userDaoSingleton == null) {
			return new UserDaoJdbc();
		}
		else {
			return userDaoSingleton;
		}
	}

	@Override
	public boolean insert(User user) {
		try(Connection connection = ConnectionUtil.getConnection()) {
			int statementIndex = 0;
			String command = "INSERT INTO USER_T VALUES (NULL,?,?,?,?,?,"
					+ "(SELECT UR_ID FROM USER_ROLE WHERE UR_TYPE = ?))";

			PreparedStatement statement = connection.prepareStatement(command);

			//Set attributes to be updated
			statement.setString(++statementIndex, user.getFirstName().toUpperCase());
			statement.setString(++statementIndex, user.getLastName().toUpperCase());
			statement.setString(++statementIndex, user.getUsername().toUpperCase());
			statement.setString(++statementIndex, user.getPassword());
			statement.setString(++statementIndex, user.getEmail().toUpperCase());
			statement.setString(++statementIndex, user.getUserRole().toUpperCase());

			if(statement.executeUpdate() > 0) {
				return true;
			}
		} catch (SQLException e) {
			LogUtil.logger.warn("Exception creating a new user", e);
		}
		return false;
	}
	
	@Override
	public String insertPasswordToken(User user) {
		try(Connection connection = ConnectionUtil.getConnection()) {
			int statementIndex = 0;
			String command = "INSERT INTO PASSWORD_RECOVERY VALUES (NULL,NULL,CURRENT_TIMESTAMP,?)";

			PreparedStatement statement = connection.prepareStatement(command);

			//Set attributes to be updated
			statement.setInt(++statementIndex, user.getId());

			if(statement.executeUpdate() > 0) {
				//Get token after created
				command = "SELECT PR_TOKEN FROM PASSWORD_RECOVERY WHERE U_ID = ?";
				statement = connection.prepareStatement(command);
				
				statement.setInt(statementIndex, user.getId());
				
				ResultSet result = statement.executeQuery();
				if(result.next()) {
					return result.getString("PR_TOKEN").toLowerCase();
				}
			}
		} catch (SQLException e) {
			LogUtil.logger.warn("Exception creating password recovery token", e);
		}
		return FinalUtil.EMPTY_STRING;
	}
	
	@Override
	public String checkIfTokenExists(String token) {
		try(Connection connection = ConnectionUtil.getConnection()) {
			int statementIndex = 0;
			String command = "SELECT U.U_USERNAME AS USERNAME FROM PASSWORD_RECOVERY PR, USER_T U WHERE PR.PR_TOKEN = ? AND PR.U_ID = U.U_ID";
			
			PreparedStatement statement = connection.prepareStatement(command);

			//Set attributes to be updated
			statement.setString(++statementIndex, token.toUpperCase());

			ResultSet result = statement.executeQuery();
			
			if(result.next()) {
				return result.getString("USERNAME");
			}
		} catch (SQLException e) {
			LogUtil.logger.warn("Exception checking if a password recovery token exists", e);
		}
		return FinalUtil.EMPTY_STRING;
	}

	@Override
	public void deleteTokens(User user) {
		try(Connection connection = ConnectionUtil.getConnection()) {
			int statementIndex = 0;
			String command = "DELETE FROM PASSWORD_RECOVERY WHERE U_ID = ?";

			PreparedStatement statement = connection.prepareStatement(command);

			//Set attributes to be updated
			statement.setInt(++statementIndex, user.getId());

			statement.executeUpdate();
			
		} catch (SQLException e) {
			LogUtil.logger.warn("Exception deleting all tokens of a user", e);
		}
	}
	
	@Override
	public User select(String username) {
		try(Connection connection = ConnectionUtil.getConnection()) {
			int statementIndex = 0;
			String command = "SELECT U.*, UR.UR_TYPE FROM USER_T U, USER_ROLE UR WHERE U_USERNAME = ? AND "
					+ "UR.UR_ID = U.UR_ID";
			PreparedStatement statement = connection.prepareStatement(command);
			statement.setString(++statementIndex, username.toUpperCase());
			ResultSet result = statement.executeQuery();

			while(result.next()) {
				return new User(
						result.getInt("U_ID"),
						StringUtil.toCamelCase(result.getString("U_FIRSTNAME")),
						StringUtil.toCamelCase(result.getString("U_LASTNAME")),
						result.getString("U_USERNAME").toLowerCase(),
						result.getString("U_PASSWORD"),
						result.getString("U_EMAIL").toLowerCase(),
						result.getString("UR_TYPE")
						);
			}
		} catch (SQLException e) {
			LogUtil.logger.warn("Exception selecting an user", e);
		} catch (StringEmptyException e) {
			LogUtil.logger.warn("Table returned empty string when not expected", e);
		}
		return new User();
	}

	@Override
	public List<User> selectAll() {
		try(Connection connection = ConnectionUtil.getConnection()) {
			int statementIndex = 0;
			String command = "SELECT U.*, UR.UR_TYPE FROM USER_T U, USER_ROLE UR "
					+ "WHERE UR.UR_ID = U.UR_ID AND UR.UR_TYPE = ? ORDER BY U.U_LASTNAME";
			PreparedStatement statement = connection.prepareStatement(command);
			statement.setString(++statementIndex, FinalUtil.USER_TYPE_EMPLOYEE);
			ResultSet result = statement.executeQuery();

			List<User> userList = new ArrayList<>();
			while(result.next()) {
				userList.add(new User(
						result.getInt("U_ID"),
						StringUtil.toCamelCase(result.getString("U_FIRSTNAME")),
						StringUtil.toCamelCase(result.getString("U_LASTNAME")),
						result.getString("U_USERNAME").toLowerCase(),
						result.getString("U_PASSWORD"),
						result.getString("U_EMAIL").toLowerCase(),
						result.getString("UR_TYPE")
						));
			}

			return userList;
		} catch (SQLException e) {
			LogUtil.logger.warn("Exception selecting all users", e);
		} catch (StringEmptyException e) {
			LogUtil.logger.warn("Table returned empty string when not expected", e);
		}
		return new ArrayList<>();
	}

	@Override
	public String getPasswordHash(String password) {
		try(Connection connection = ConnectionUtil.getConnection()) {
			int statementIndex = 0;
			String command = "SELECT GET_HASH(?) AS HASH FROM DUAL";
			PreparedStatement statement = connection.prepareStatement(command);
			statement.setString(++statementIndex, password);
			ResultSet result = statement.executeQuery();

			if(result.next()) {
				return result.getString("HASH");
			}
		} catch (SQLException e) {
			//log
		} 
		return new String();
	}

	@Override
	public boolean update(User user) {
		try(Connection connection = ConnectionUtil.getConnection()) {
			int statementIndex = 0;
			String command = "UPDATE USER_T SET "
					+ "U_FIRSTNAME = ?,"
					+ "U_LASTNAME = ?,"
					+ "U_USERNAME = ?,"
					+ "U_PASSWORD = ?,"
					+ "U_EMAIL = ? "
					+ "WHERE U_ID = ?";
			PreparedStatement statement = connection.prepareStatement(command);

			//Set attributes to be updated
			statement.setString(++statementIndex, user.getFirstName().toUpperCase());
			statement.setString(++statementIndex, user.getLastName().toUpperCase());
			statement.setString(++statementIndex, user.getUsername().toUpperCase());
			statement.setString(++statementIndex, user.getPassword());
			statement.setString(++statementIndex, user.getEmail().toUpperCase());

			//Set Id on where clause
			statement.setInt(++statementIndex, user.getId());

			if(statement.executeUpdate() > 0) {
				return true;
			}
		} catch (SQLException e) {
			LogUtil.logger.warn("Exception when updating a user", e);
		}
		return false;
	}
}
