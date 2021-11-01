package com.project1.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

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
import com.project1.models.User;
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
	
	public static void pendreimb(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {
		
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
		System.out.println(username);
		try {
			List<Reimbursement> reimbs = rServ.getUpendReimb(username);
			System.out.println(reimbs);
			res.getWriter().write(new ObjectMapper().writeValueAsString(reimbs));
		} catch(Exception e) {
			res.setStatus(403);
			res.getWriter().println("Error in pending Reimbursement");
		}
	}
	
	public static void resreimb(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {
		
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
		System.out.println(username);
		try {
			List<Reimbursement> reimbs = rServ.getUresReimb(username);
			System.out.println(reimbs);
			res.getWriter().write(new ObjectMapper().writeValueAsString(reimbs));
		} catch(Exception e) {
			res.setStatus(403);
			res.getWriter().println("Error in pending Reimbursement");
		}
	}
	
	public static void viewallres(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {
		
		List<Reimbursement> resolve = rServ.getAresReimb();
		res.getWriter().write(new ObjectMapper().writeValueAsString(resolve));
	}
	
	public static void viewallpend(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {
		
		List<Reimbursement> pending = rServ.getApendReimb();
		res.getWriter().write(new ObjectMapper().writeValueAsString(pending));
	}
	
	public static void viewempreq(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {
		
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
		System.out.println(username);
		try {
			List<Reimbursement> reimbs = rServ.getEmpReimb(username);
			System.out.println(reimbs);
			res.getWriter().write(new ObjectMapper().writeValueAsString(reimbs));
		} catch(Exception e) {
			res.setStatus(403);
			res.getWriter().println("Error in Reimbursement");
		}
	}
	
	public static void updatestatus(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {
		
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
		int id = Integer.parseInt(parsedObj.get("reimb_id").asText());
		String resdate = parsedObj.get("resdate").asText();
		int resolver = Integer.parseInt(parsedObj.get("resolver").asText());
		int status = Integer.parseInt(parsedObj.get("status").asText());
		
		try {
			//System.out.println(author);
			rServ.changeStatus(id, resdate, resolver, status);
			//We will keep track of if a user is signed in by storing their id in the session
			//req.getSession().setAttribute("id", u.getId());
			res.setStatus(200);
			res.getWriter().write(new ObjectMapper().writeValueAsString("Successfully registered"));
		} catch(Exception e) {
			res.setStatus(403);
			res.getWriter().println("Update failed");
		}
	}
	
	public static void getreimb(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {
		
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
		int id = Integer.parseInt(parsedObj.get("id").asText());
		System.out.println(id);
		try {
			Reimbursement reimb = rServ.getReimbById(id);
			res.getWriter().write(new ObjectMapper().writeValueAsString(reimb));
		} catch(Exception e) {
			res.setStatus(403);
			res.getWriter().println("Reimbursement does not exist");
		}
	}
	
}
