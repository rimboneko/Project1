package com.project1.dao;

import java.sql.SQLException;
import java.util.List;

import com.project1.models.User;

public interface UserDao {
	
	List<User> getAllEmployees();
	
	void createUser(User u) throws SQLException;
	
	void updateUser(User u);
	
	void deleteUser(User u);
	
	User getUserByUsername(String username);
	
	int getRoleId(String role);
	
}
