package com.project1.controllers;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.project1.dao.ReimbursementDao;
import com.project1.dao.ReimbursementDaoDB;
import com.project1.dao.UserDao;
import com.project1.dao.UserDaoDB;
import com.project1.models.Reimbursement;
import com.project1.services.ReimbService;
import com.project1.services.UserService;

public class ReimbursementController {
	
	private static ReimbursementDao rDao = new ReimbursementDaoDB();
	private static ReimbService rServ = new ReimbService(rDao);
	
	public static void createreimb(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {
		
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
		double amount = Double.parseDouble(parsedObj.get("amount").asText());
		String subdate = parsedObj.get("subdate").asText();
		String description = parsedObj.get("description").asText();
		boolean receipt = Boolean.parseBoolean(parsedObj.get("receipt").asText());
		int author = Integer.parseInt(parsedObj.get("author").asText());
		int type = Integer.parseInt(parsedObj.get("type").asText());
		
		try {
			//System.out.println(author);
			rServ.subReimb(amount, subdate, "-", description, receipt, author, 1, 1, type);
			System.out.println(author);
			//We will keep track of if a user is signed in by storing their id in the session
			//req.getSession().setAttribute("id", u.getId());
			res.setStatus(200);
			res.getWriter().write(new ObjectMapper().writeValueAsString("Successfully registered"));
		} catch(Exception e) {
			res.setStatus(403);
			res.getWriter().println("Reimbursement unregistered");
		}
		
	}

}
