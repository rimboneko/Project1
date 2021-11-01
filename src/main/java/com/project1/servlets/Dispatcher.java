package com.project1.servlets;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project1.controllers.LoginController;
import com.project1.controllers.ReimbursementController;
import com.project1.controllers.SessionController;
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
			case "/project1/api/viewallemp":
				LoginController.viewallemp(req, res);
				break;
			case "/project1/api/createreimb":
				ReimbursementController.createreimb(req, res);
				break;
			case "/project1/api/pendreimb":
				ReimbursementController.pendreimb(req, res);
				break;
			case "/project1/api/resreimb":
				ReimbursementController.resreimb(req, res);
				break;
			case "/project1/api/viewallpend":
				ReimbursementController.viewallpend(req, res);
				break;
			case "/project1/api/viewallres":
				ReimbursementController.viewallres(req, res);
				break;
			case "/project1/api/viewempreq":
				ReimbursementController.viewempreq(req, res);
				break;
			case "/project1/api/updatestatus":
				ReimbursementController.updatestatus(req, res);
				break;
			case "/project1/api/getreimb":
				ReimbursementController.getreimb(req, res);;
				break;
			case "/project1/api/session":
				SessionController.getSession(req, res);
				
		}
	}
	
}
