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

	Connection conn;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection(
					"jdbc:sqlserver://localhost:1433;databaseName=projectTWO;TrustServerCertificate=True", "sa",
					"Passw0rd!!");
			String sql = "select * from TicketInfo";
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			List<TicketDTO> tk = new ArrayList<>();
			TicketDTO tk_dto = null;
			while (rs.next()) {
				tk_dto = new TicketDTO();
				tk_dto.setTicketID(rs.getInt("TicketID"));
				tk_dto.setTranNo(rs.getString("TranNo"));
				tk_dto.setSeat(rs.getString("Seat"));
				tk_dto.setDeparture_ST(rs.getString("Departure_ST"));
				tk_dto.setDestination_ST(rs.getString("Destination_ST"));
				tk_dto.setDeparture_time(rs.getString("Departure_time"));
				tk_dto.setArrival_time(rs.getString("Arrival_time"));
				tk_dto.setPrice(rs.getInt("Price"));
				tk_dto.setDate(rs.getDate("Date"));
				tk.add(tk_dto);
			}
			request.setAttribute("tk", tk);
			stmt.close();
			request.getRequestDispatcher("/html/BookingAdmin.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}
		}

	}

	
//	protected void doGet(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		String action = request.getParameter("action");
//		if(action == null) {
//			action = "";
//		}
//		switch (action) {
//		case Utils.ACTION_CREATE:
//			create(request, response);
//			break;
//		case Utils.ACTION_QUERY:
//			query(request, response);
//			break;
//		case Utils.ACTION_UPDATE:
//			update(request, response);
//			break;
//		case Utils.ACTION_DELETE:
//			delete(request, response);
//			break;
//
//		default:
//			index(request, response);
//			break;
//		}		
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	public void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BookingDAO bookingDAO = new BookingDAOImpl();
		request.setAttribute("stationList", bookingDAO.getAllStationInfo());
		request.setAttribute("priceInfos", bookingDAO.getAllPriceInfo());
		request.getRequestDispatcher("/html/BookingAdmin.jsp").forward(request, response);
	}

	public void create(HttpServletRequest request, HttpServletResponse response) {
		TicketDAO tkaDao = new TicketDAOImpl();
		
	}
	public void query(HttpServletRequest request, HttpServletResponse response) {
		TicketDAO tkaDao = new TicketDAOImpl();
		
	}
	public void update(HttpServletRequest request, HttpServletResponse response) {
		TicketDAO tkaDao = new TicketDAOImpl();
		
	}
	public void delete(HttpServletRequest request, HttpServletResponse response) {
		TicketDAO tkaDao = new TicketDAOImpl();
		
	}
}
