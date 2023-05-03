package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import db.dao.BookingDAO;
import db.dao.TicketDAO;
import db.dao.impl.BookingDAOImpl;
import db.dao.impl.TicketDAOImpl;
import model.Utils;
import model.dto.TicketDTO;

@WebServlet("/Bookingadmin")
public class Bookingadmin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Bookingadmin() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {	
		String action = request.getParameter("action");
		if(action == null) {
			action = "";
		}
		switch (action) {
		case Utils.ACTION_CREATE:
			create(request, response);
			break;
		case Utils.ACTION_QUERY:
			query(request, response);
			break;
		case Utils.ACTION_UPDATE:
			update(request, response);
			break;
		case Utils.ACTION_DELETE:
			delete(request, response);
			break;
		default:
			index(request, response);
			break;
		}	
			
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/html/BookingAdmin.jsp").forward(request, response);
	}

	public void create(HttpServletRequest request, HttpServletResponse response) {
		TicketDAO tkaDao = new TicketDAOImpl();
		
	}
	public void query(HttpServletRequest request, HttpServletResponse response) {
		try {
			TicketDAO ticketDao = new TicketDAOImpl();
			List<TicketDTO> tks = ticketDao.getAllTicketInfo();
			request.setAttribute("tks", tks);
			request.getRequestDispatcher("/html/BookingAdmin.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	public void update(HttpServletRequest request, HttpServletResponse response) {
		TicketDAO tkaDao = new TicketDAOImpl();
		
	}
	public void delete(HttpServletRequest request, HttpServletResponse response) {
		TicketDAO tkaDao = new TicketDAOImpl();
		
	}
}
