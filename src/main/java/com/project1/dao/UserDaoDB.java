package com.project1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.project1.utils.ConnectionUtil;
import com.project1.models.User;

public class UserDaoDB implements UserDao {
	
	ConnectionUtil conUtil = ConnectionUtil.getConnectionUtil();

	@Override
	public List<User> getAllEmployees() {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getRoleId(String role) {
		// TODO Auto-generated method stub
		return 0;
	}

}
