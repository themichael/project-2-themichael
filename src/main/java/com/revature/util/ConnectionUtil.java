package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public final class ConnectionUtil {
	
	private static Logger logger = Logger.getLogger(ConnectionUtil.class);
	
	/* Make Tomcat now which database driver to use */
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			logger.warn("Exception thrown adding oracle driver.", e);
		}
	}
	
	public static Connection getConnection() throws SQLException {
		String url = System.getenv("JDBC_URL");
		String username = System.getenv("JDBC_USERNAME");
		String password = System.getenv("JDBC_PASSWORD");
		
		return DriverManager.getConnection(url, username, password);
	}
}
