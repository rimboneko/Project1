package com.project1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.project1.models.Reimbursement;
import com.project1.models.User;
import com.project1.utils.ConnectionUtil;

public class ReimbursementDaoDB implements ReimbursementDao {
	
	ConnectionUtil conUtil = ConnectionUtil.getConnectionUtil();

	@Override
	public List<Reimbursement> viewEmpReimb(String  username) {
		
		UserDaoDB udao = new UserDaoDB();
		int author = udao.getUserByUsername(username).getUser_id();
		List<Reimbursement> reimbList = new ArrayList<Reimbursement>();
		
		try {
			
			Connection con = conUtil.getConnection();
			String sql = "SELECT * FROM reimbursement WHERE reimb_author = '"+author+"'";
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);
				
			while(rs.next()) {
					
				reimbList.add(new Reimbursement(rs.getInt(1), rs.getDouble(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getBoolean(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10)));
			}
				
			return reimbList;	
			
			} catch(SQLException e) {
				e.printStackTrace();
			}
		return null;
	}

	@Override
	public List<Reimbursement> viewPendReimb() {
		
		List<Reimbursement> reimbList = new ArrayList<Reimbursement>();
		
		try {
			
		Connection con = conUtil.getConnection();
		String sql = "SELECT * FROM reimbursement WHERE reimb_status_id =1";
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery(sql);
			
		while(rs.next()) {
				
			reimbList.add(new Reimbursement(rs.getInt(1), rs.getDouble(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getBoolean(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10)));
		}
			
		return reimbList;	
		
		} catch(SQLException e) {
			e.printStackTrace();
		}
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

	@Override
	public List<Reimbursement> viewResReimb() {
		
		List<Reimbursement> reimbList = new ArrayList<Reimbursement>();
		
		try {
			
		Connection con = conUtil.getConnection();
		String sql = "SELECT * FROM reimbursement WHERE reimb_status_id IN (2,3) ";
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery(sql);
			
		while(rs.next()) {
				
			reimbList.add(new Reimbursement(rs.getInt(1), rs.getDouble(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getBoolean(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10)));
		}
			
		return reimbList;	
		
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Reimbursement> viewPendReimb(String username) {
		
		UserDaoDB udao = new UserDaoDB();
		int author = udao.getUserByUsername(username).getUser_id();
		List<Reimbursement> reimbList = new ArrayList<Reimbursement>();
		
		try {
			
		Connection con = conUtil.getConnection();
		String sql = "SELECT * FROM reimbursement WHERE reimb_status_id =1 AND reimb_author = '"+author+"'";
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery(sql);
			
		while(rs.next()) {
				
			reimbList.add(new Reimbursement(rs.getInt(1), rs.getDouble(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getBoolean(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10)));
		}
			
		return reimbList;	
		
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Reimbursement> viewResReimb(String username) {
		
		UserDaoDB udao = new UserDaoDB();
		int author = udao.getUserByUsername(username).getUser_id();
		List<Reimbursement> reimbList = new ArrayList<Reimbursement>();
		
		try {
			
		Connection con = conUtil.getConnection();
		String sql = "SELECT * FROM reimbursement WHERE reimb_status_id IN (2,3) AND reimb_author = '"+author+"'";
		Statement s = con.createStatement();
		ResultSet rs = s.executeQuery(sql);
			
		while(rs.next()) {
				
			reimbList.add(new Reimbursement(rs.getInt(1), rs.getDouble(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getBoolean(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10)));
		}
			
		return reimbList;	
		
		} catch(SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public void changeStatus(int reim_id, int status_id) throws SQLException {
		
		Connection con = conUtil.getConnection();
		String sql = "UPDATE reimbursement SET reimb_status_id = ?" + "WHERE reimb_id = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, status_id);
		ps.setInt(2, reim_id);
		ps.execute();	
	}
	

}
