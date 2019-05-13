package com.revature.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import com.revature.entities.Cave;
import com.revature.services.CaveService;

public class CaveServlet extends HttpServlet {
	
	CaveService caveService = new CaveService();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper om = new ObjectMapper();
		Cave cave = om.readValue(req.getReader(), Cave.class);
		
		cave = this.caveService.create(cave);
		resp.setStatus(201);
		om.writeValue(resp.getWriter(), cave);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getPathInfo().split("/")[1]);
		Cave cave = caveService.findById(id);
		ObjectMapper om = new ObjectMapper();
		om.registerModule(new Hibernate5Module());
		om.writeValue(resp.getWriter(), cave);
	}
	
}
