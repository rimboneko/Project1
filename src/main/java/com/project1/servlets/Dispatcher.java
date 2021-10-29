package com.project1.servlets;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project1.controllers.LoginController;
import com.project1.controllers.ReimbursementController;
import com.fasterxml.jackson.core.JsonProcessingException;

public class Dispatcher {

	public static void process(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {
		System.out.println("In the Servlet Dispatcher with URI: " + req.getRequestURI());
		switch(req.getRequestURI()) {
			case "/project1/api/login":
				LoginController.login(req, res);
				break;
			case "/project1/api/register":
				LoginController.register(req, res);
				break;
			case "/project1/api/userinf":
				LoginController.userinf(req, res);
				break;
			case "/project1/api/createreimb":
				ReimbursementController.createreimb(req, res);
				break;
				
		}
	}
	
}
