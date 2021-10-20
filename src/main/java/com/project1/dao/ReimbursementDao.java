package com.project1.dao;

import java.sql.SQLException;
import java.util.List;

import com.project1.models.Reimbursement;
import com.project1.models.ReimbursementStatus;
import com.project1.models.User;

public interface ReimbursementDao {
	
	List<Reimbursement> viewEmpReimb(User u);
	
	List<Reimbursement> viewPendReimb(ReimbursementStatus status);
	
	void createReimb(Reimbursement r) throws SQLException;
	
 }
