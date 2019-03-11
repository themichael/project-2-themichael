package com.revature.dao;

import java.util.List;

import com.revature.model.Reimbursement;
import com.revature.model.ReimbursementType;

public interface ReimbursementDao {
	
	public boolean insert(Reimbursement reimbursement);
	public List<Reimbursement> selectAllPending(int employeeId);
	public List<Reimbursement> selectAllResolved(int employeeId);
	public List<ReimbursementType> selectTypes();	
	public boolean update(Reimbursement reimbursement);
}
