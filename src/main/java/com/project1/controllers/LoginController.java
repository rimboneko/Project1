package com.project1.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project1.dao.UserDao;
import com.project1.dao.UserDaoDB;
import com.project1.models.User;
import com.project1.services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LoginController {
	
	private static UserDao uDao = new UserDaoDB();
	private static UserService uServ = new UserService(uDao);
	private static User accInf = new User();
	
	public static void login(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {
		
		StringBuilder buffer = new StringBuilder();
		BufferedReader reader = req.getReader();
		String line;
		while((line = reader.readLine()) != null) {
			buffer.append(line);
			buffer.append(System.lineSeparator());
		}
		
		String data = buffer.toString();
		ObjectMapper mapper = new ObjectMapper();
		JsonNode parsedObj = mapper.readTree(data);
		String username = parsedObj.get("username").asText();
		String password = parsedObj.get("password").asText();
		
		try {
			System.out.println(username);
			User u = uServ.signIn(username, password);
			accInf = u;
			System.out.println(u);
			//We will keep track of if a user is signed in by storing their id in the session
			req.getSession().setAttribute("id", u.getUser_id());
			res.setStatus(200);
			res.getWriter().write(new ObjectMapper().writeValueAsString(u.getRole_id()));
		} catch(Exception e) {
			res.setStatus(403);
			res.getWriter().println("Username or password incorrect");
		}
		
	}
	
	public static void register(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {
		
		StringBuilder buffer = new StringBuilder();
		BufferedReader reader = req.getReader();
		String line;
		while((line = reader.readLine()) != null) {
			buffer.append(line);
			buffer.append(System.lineSeparator());
		}
		
		String data = buffer.toString();
		ObjectMapper mapper = new ObjectMapper();
		JsonNode parsedObj = mapper.readTree(data);
		String username = parsedObj.get("username").asText();
		String password = parsedObj.get("password").asText();
		String firstname = parsedObj.get("firstname").asText();
		String lastname = parsedObj.get("lastname").asText();
		String email = parsedObj.get("email").asText();
		int role = Integer.parseInt(parsedObj.get("role").asText());
		
		try {
			
			uServ.register(username, password, firstname, lastname, email, role);
			res.setStatus(200);
			res.getWriter().write(new ObjectMapper().writeValueAsString("Successfully registered"));
		} catch(Exception e) {
			res.setStatus(403);
			res.getWriter().println("Username already exist");
		}
		
	}
	
	public static void userinf(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {
		
		res.getWriter().write(new ObjectMapper().writeValueAsString(accInf));
	}
	
	public static void viewallemp(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {
		
		List<User> employees = uServ.getAllEmployees();
		res.getWriter().write(new ObjectMapper().writeValueAsString(employees));
	}
	
}
