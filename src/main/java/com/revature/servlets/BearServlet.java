package com.revature.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.entities.Bear;
import com.revature.services.BearService;
import com.revature.util.HibernateUtil;

public class BearServlet extends HttpServlet {
	
	private BearService bearService = new BearService();
	
	@Override
	public void init() throws ServletException {
		HibernateUtil.configure();
		super.init();
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ObjectMapper om = new ObjectMapper();
		Bear bear = om.readValue(request.getInputStream(), Bear.class);
		
		bear = bearService.saveBear(bear);
		om.writeValue(response.getWriter(), bear);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] parts = request.getPathInfo().split("/");
		if (parts.length > 1) {
			int id = Integer.parseInt(parts[1]);
			handleGetBear(response, id);
		} else {
			getAllBears(response);
		}
	}
	
	private void handleGetBear(HttpServletResponse response, int id) throws IOException {
		Bear bear = this.bearService.getBearById(id);
		ObjectMapper om = new ObjectMapper();
		om.writeValue(response.getWriter(), bear);
	}
	
	private void getAllBears(HttpServletResponse response) throws IOException {
		List<Bear> bears = this.bearService.getAllBears();
		ObjectMapper om = new ObjectMapper();
		om.writeValue(response.getWriter(), bears);
	}
}