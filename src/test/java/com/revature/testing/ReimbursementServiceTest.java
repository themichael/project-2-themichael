package com.revature.testing;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.revature.service.ReimbursementService;

public class ReimbursementServiceTest {

	int employeeId = 1;
	
	/* Expected more than 0 types in db */
    @Test
    public void getReimbursementTypesTest() {
    	assertTrue(ReimbursementService.getReimbursementService().getReimbursementTypes().size() > 0);
    }
    
    /* Expected more than 0 pending requests for employeeId */
    @Test
    public void getUserPendingRequestsTest() {
    	assertTrue(ReimbursementService.getReimbursementService().getUserPendingRequests(employeeId).size() > 0);
    }
    
    /* Expected more than 0 resolved requests for employeeId */
    @Test
    public void getUserResolvedRequestsTest() {
    	assertTrue(ReimbursementService.getReimbursementService().getUserResolvedRequests(employeeId).size() > 0);
    }
    
    /* Expected more than 0 pending requests */
    @Test
    public void getAllPendingRequestsTest() {
    	assertTrue(ReimbursementService.getReimbursementService().getAllPendingRequests().size() > 0);
    }
    
    /* Expected more than 0 resolved requests */
    @Test
    public void getAllResolvedRequestsTest() {
    	assertTrue(ReimbursementService.getReimbursementService().getAllResolvedRequests().size() > 0);
    }
}
