package com.revature.model;

/* User POJO */
public class User {
	private int id;
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private String email;
	private String userRole;	

	public User() {
		firstName = "";
		lastName = "";
		username = "";
		password = "";
		email = "";
		userRole = "";
	}

	public User(int id) {
		this.id = id;
		firstName = "";
		lastName = "";
		username = "";
		password = "";
		email = "";
		userRole = "";
	}

	public User(String username, String password) {
		firstName = "";
		lastName = "";
		this.username = username;
		this.password = password;
		email = "";
		userRole = "";
	}

	public User(int id, String firstName, String lastName, String username, String password, String email,
			String userRole) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.email = email;
		this.userRole = userRole;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", username=" + username
				+ ", password=" + password + ", email=" + email + ", userRole=" + userRole + "]";
	}
	
	public static void main(String[] args) {
		System.out.println("String".replace('g','G')=="StrinG");
	}
}
