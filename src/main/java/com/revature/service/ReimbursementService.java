package com.revature.service;

import java.util.List;

import com.revature.dao.ReimbursementDaoJdbc;
import com.revature.exception.InvalidInputException;
import com.revature.model.Reimbursement;
import com.revature.model.ReimbursementType;
import com.revature.util.FinalUtil;

public class ReimbursementService {
	
	private static ReimbursementService reimbursementService;
	
	private ReimbursementService() {
		
	}
	
	public static ReimbursementService getReimbursementService() {
		if(reimbursementService == null) {
			return new ReimbursementService();
		}
		else {
			return reimbursementService;
		}
	}
	
	/* An employee is able to submit a reimbursement request */
	public boolean submitReimbursementRequest(Reimbursement reimbursement) throws InvalidInputException {
		if(reimbursement.getDescription().equals(FinalUtil.EMPTY_STRING)) {
			throw new InvalidInputException("Description can't be empty.");
		}
		
		return ReimbursementDaoJdbc.getReimbursementDao().insert(reimbursement);
	}
	
	/* Get reimbursement types */
	public List<ReimbursementType> getReimbursementTypes() {
		return ReimbursementDaoJdbc.getReimbursementDao().selectTypes();
	}
	
	/* An user can get the pending requests of a specific user */
	public List<Reimbursement> getUserPendingRequests(int employeeId) {
		return ReimbursementDaoJdbc.getReimbursementDao().selectAllPending(employeeId);
	}
	
	/* An user can get the resolved requests of a specific user */
	public List<Reimbursement> getUserResolvedRequests(int employeeId) {
		return ReimbursementDaoJdbc.getReimbursementDao().selectAllResolved(employeeId);
	}
	
	/* A manager can get all pending requests */
	public List<Reimbursement> getAllPendingRequests() {
		return ReimbursementDaoJdbc.getReimbursementDao().selectAllPending(0);
	}
	
	/* A manager can get all resolved requests */
	public List<Reimbursement> getAllResolvedRequests() {
		return ReimbursementDaoJdbc.getReimbursementDao().selectAllResolved(0);
	}
	
	/* A manager can resolve a request */
	public boolean resolveRequest(Reimbursement reimbursement) {
		return ReimbursementDaoJdbc.getReimbursementDao().update(reimbursement);
	}
}
