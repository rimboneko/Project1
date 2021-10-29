package com.project1.dao;

import java.sql.SQLException;
import java.util.List;

import com.project1.models.Reimbursement;
import com.project1.models.ReimbursementStatus;
import com.project1.models.User;

public interface ReimbursementDao {
	
	List<Reimbursement> viewEmpReimb(String username);
	
	List<Reimbursement> viewPendReimb();
	
	List<Reimbursement> viewPendReimb(String username);
	
	List<Reimbursement> viewResReimb();
	
	List<Reimbursement> viewResReimb(String username);
	
	void createReimb(Reimbursement r) throws SQLException;
	
	void changeStatus(int reim_id, int status_id) throws SQLException;
	
 }
