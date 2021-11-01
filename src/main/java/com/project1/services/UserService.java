package com.project1.services;

import java.sql.SQLException;
import java.util.List;

import com.project1.dao.UserDao;
import com.project1.exceptions.InvalidCredentialsException;
import com.project1.exceptions.UserDoesNotExistException;
import com.project1.exceptions.UsernameAlreadyExistsException;
import com.project1.logging.Logging;
import com.project1.models.User;

public class UserService {
	
private UserDao uDao;
	
	public UserService(UserDao u) {
		this.uDao = u;
	}
	
	public void register(String username, String password, String first, String last, String email, int role) throws UsernameAlreadyExistsException {
		
		User u = new User(username, password, first, last, email, role);
		
		try {
			uDao.createUser(u);
			Logging.logger.info("New user was registered");
		} catch (SQLException e) {
			Logging.logger.info("Username created that already exists in the database");
			throw new UsernameAlreadyExistsException();
		}
		
	}
	
	public User signIn(String username, String password) throws UserDoesNotExistException, InvalidCredentialsException{
		
	
		User u = uDao.getUserByUsername(username);
		
		if(!u.getUsername().equals(username)) {
			Logging.logger.info("User tried logging in that does not exist");
			throw new UserDoesNotExistException();
		}
		else if(!u.getPassword().equals(password)) {
			Logging.logger.info("User tried to login with invalid credentials");
			throw new InvalidCredentialsException();
		}
		else{
			System.out.println("User was logged in");
			Logging.logger.info("User was logged in");
			return u;
		}
		
	}
	
	public List<User> getAllEmployees() {
		
		return uDao.getAllEmployees();
	}
	
}
