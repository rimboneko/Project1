package com.project1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.project1.utils.ConnectionUtil;
import com.project1.models.User;

public class UserDaoDB implements UserDao {
	
	ConnectionUtil conUtil = ConnectionUtil.getConnectionUtil();

	@Override
	public List<User> getAllEmployees() {
		
		List<User> empList = new ArrayList<User>();
		
		try {
			
			Connection con = conUtil.getConnection();
			String sql = "SELECT * FROM users WHERE user_role_id=2";
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);
	
			while(rs.next()) {
				empList.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7)));
			}
			
			return empList;
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void createUser(User u) throws SQLException {
		
		Connection con = conUtil.getConnection();
		String sql = "INSERT INTO users(username, password, user_first_name, user_last_name, user_email, user_role_id) values"
				+ "(?,?,?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		
		ps.setString(1, u.getUsername());
		ps.setString(2, u.getPassword());
		ps.setString(3, u.getFirst_name());
		ps.setString(4, u.getLast_name());
		ps.setString(5, u.getEmail());
		ps.setInt(6, u.getRole_id());
		ps.execute();
	}

	@Override
	public void updateUser(User u) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUser(User u) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User getUserByUsername(String username) {
		
		User user = new User();
		
		try {
			Connection con = conUtil.getConnection();
			
			String sql = "SELECT * FROM users WHERE users.username = '" + username + "'";
			
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);
			
			while(rs.next()) {
				user.setUser_id(rs.getInt(1));
				user.setUsername(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setFirst_name(rs.getString(4));
				user.setLast_name(rs.getString(5));
				user.setEmail(rs.getString(6));
				user.setRole_id(rs.getInt(7));
			}
			
			return user;
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int getRoleId(String role) {
		// TODO Auto-generated method stub
		return 0;
	}

}
