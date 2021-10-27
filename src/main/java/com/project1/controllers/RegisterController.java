package com.project1.controllers;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.project1.dao.UserDao;
import com.project1.dao.UserDaoDB;
import com.project1.services.UserService;

public class RegisterController {
	
	private static UserDao uDao = new UserDaoDB();
	private static UserService uServ = new UserService(uDao);
	
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
			//We will keep track of if a user is signed in by storing their id in the session
			//req.getSession().setAttribute("id", u.getId());
			res.setStatus(200);
			res.getWriter().write(new ObjectMapper().writeValueAsString("Successfully registered"));
		} catch(Exception e) {
			res.setStatus(403);
			res.getWriter().println("Username already exist");
		}
		
	}

}
