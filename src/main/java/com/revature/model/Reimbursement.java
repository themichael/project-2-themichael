package com.revature.model;

/* Reimbursement POJO */
public class Reimbursement {
	private int id;
	private String requested;
	private String resolved;
	private double amount;
	private String description;
	private StringBuilder receipt;
	private User employee;
	private User manager;
	private String status;
	private ReimbursementType type;
		
	public Reimbursement() {
		requested = "";
		resolved = "";
		description = "";
		status = "";
		employee = new User();
		manager = new User();
		type = new ReimbursementType();
	}
	
	public Reimbursement(int id, int managerId, String status) {
		this.id = id;
		requested = "";
		resolved = "";
		description = "";
		this.status = status;
		employee = new User();
		manager = new User(managerId);
		type = new ReimbursementType();
	}
	
	public Reimbursement(String description, String status, User employee, ReimbursementType type, double amount, StringBuilder receipt) {
		requested = "";
		resolved = "";
		this.description = description;
		this.status = status;
		this.employee = employee;
		manager = new User();
		this.type = type;
		this.amount = amount;
		this.receipt = receipt;
	}

	public Reimbursement(int id, String requested, String resolved, double amount, String description, StringBuilder receipt,
			User employee, User manager, String status, ReimbursementType type) {
		this.id = id;
		this.requested = requested;
		this.resolved = resolved;
		this.amount = amount;
		this.description = description;
		this.receipt = receipt;
		this.employee = employee;
		this.manager = manager;
		this.status = status;
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRequested() {
		return requested;
	}

	public void setRequested(String requested) {
		this.requested = requested;
	}

	public String getResolved() {
		return resolved;
	}

	public void setResolved(String resolved) {
		this.resolved = resolved;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public StringBuilder getReceipt() {
		return receipt;
	}

	public void setReceipt(StringBuilder receipt) {
		this.receipt = receipt;
	}

	public User getEmployee() {
		return employee;
	}

	public void setEmployee(User employee) {
		this.employee = employee;
	}

	public User getManager() {
		return manager;
	}

	public void setManager(User manager) {
		this.manager = manager;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ReimbursementType getType() {
		return type;
	}

	public void setType(ReimbursementType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Reimbursement [id=" + id + ", requested=" + requested + ", resolved=" + resolved + ", amount=" + amount
				+ ", description=" + description + ", receipt=" + receipt + ", employee=" + employee + ", manager="
				+ manager + ", status=" + status + ", type=" + type + "]";
	}
}
