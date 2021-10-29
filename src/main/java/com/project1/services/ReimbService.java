package com.project1.services;

import java.sql.SQLException;

import com.project1.dao.ReimbursementDao;
import com.project1.models.Reimbursement;

public class ReimbService {
	
	private ReimbursementDao rdao;

	public ReimbService(ReimbursementDao rdao) {
		super();
		this.rdao = rdao;
	}
	
	public void subReimb(double amount, String submitted, String resolved, String description, boolean receipt, int author_id, int resolver_id, int status_id, int type_id ) throws SQLException {
		
		Reimbursement r = new Reimbursement(amount, submitted, resolved, description, receipt, author_id, resolver_id, status_id, type_id);
		rdao.createReimb(r);
	}

}
