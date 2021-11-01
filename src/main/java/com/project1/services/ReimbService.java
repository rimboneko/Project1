package com.project1.services;

import java.sql.SQLException;
import java.util.List;

import com.project1.dao.ReimbursementDao;
import com.project1.logging.Logging;
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
		Logging.logger.info("New reimbursemen request was created");
	}
	
	public List<Reimbursement> getUpendReimb(String username) {
		
		return rdao.viewPendReimb(username);
	}
	
	public List<Reimbursement> getUresReimb(String username) {
		
		return rdao.viewResReimb(username);
	}
	
	public List<Reimbursement> getApendReimb() {
		
		return rdao.viewPendReimb();
	}
	
	public List<Reimbursement> getAresReimb() {
		
		return rdao.viewResReimb();
	}
	
	public List<Reimbursement> getEmpReimb(String username) {
		
		return rdao.viewEmpReimb(username);
	}
	
	public void changeStatus(int reim_id, String resolved, int resolver, int status_id) throws SQLException {
		
		rdao.changeStatus(reim_id, resolved, resolver, status_id);
		Logging.logger.info("Reimbursement status was changed");
	}
	
	public Reimbursement getReimbById(int reimb_id) {
		
		return rdao.getReimb(reimb_id);
	}

}
