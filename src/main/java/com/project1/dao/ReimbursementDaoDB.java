package com.project1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.project1.models.Reimbursement;
import com.project1.models.ReimbursementStatus;
import com.project1.models.User;
import com.project1.utils.ConnectionUtil;

public class ReimbursementDaoDB implements ReimbursementDao {
	
	ConnectionUtil conUtil = ConnectionUtil.getConnectionUtil();

	@Override
	public List<Reimbursement> viewEmpReimb(User u) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reimbursement> viewPendReimb(ReimbursementStatus status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createReimb(Reimbursement r) throws SQLException {
		
		Connection con = conUtil.getConnection();
		String sql = "INSERT INTO reimbursement(reimb_amount, reimb_submitted, reimb_resolved, reimb_description, reimb_receipt, reimb_author, reimb_resolver, reimb_status_id, reimb_type_id) values"
				+ "(?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		
		ps.setDouble(1, r.getAmount());
		ps.setString(2, r.getSubmitted());
		ps.setString(3, r.getResolved());
		ps.setString(4, r.getDescription());
		ps.setBoolean(5, r.isReceipt());
		ps.setInt(6, r.getAuthor_id());
		ps.setInt(7, r.getResolver_id());
		ps.setInt(8, r.getStatus_id());
		ps.setInt(9, r.getType_id());
		ps.execute();
		
	}
	

}
