package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import model.dto.TicketDTO;

@WebServlet("/UpdateBooking")
public class UpdateBooking extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UpdateBooking() {
		super();
	}

	Connection conn;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int TicketID = Integer.parseInt(request.getParameter("TicketID"));
		String TranNo = request.getParameter("TranNo");
		String Seat = request.getParameter("Seat");
		String Departure_ST = request.getParameter("Departure_ST");
		String Destination_ST = request.getParameter("Destination_ST");
		String Departure_time = request.getParameter("Departure_time");
		String Arrival_time = request.getParameter("Arrival_time");
		int price = Integer.parseInt(request.getParameter("price"));
		String Date = request.getParameter("Date");
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection(
					"jdbc:sqlserver://localhost:1433;databaseName=projectTWO;TrustServerCertificate=True", "sa",
					"Passw0rd!!");
			String sql = "update TicketInfo set TranNo = ?, Seat = ?, Departure_ST = ?, Destination_ST = ?, Departure_time = ?,Arrival_time = ?,price = ?,Date = ? where TicketID = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, TranNo);
			stmt.setString(2, Seat);
			stmt.setString(3, Departure_ST);
			stmt.setString(4, Destination_ST);
			stmt.setString(5, Departure_time);
			stmt.setString(6, Arrival_time);
			stmt.setInt(7, price);
			stmt.setString(8, Date);
			stmt.setInt(9, TicketID);

			int row = stmt.executeUpdate();

			stmt.close();
			response.sendRedirect(request.getContextPath() + "/BookingAdmin");
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String updateTarget = request.getParameter("updateTarget");
		if (updateTarget != null && !updateTarget.isEmpty()) {

			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				conn = DriverManager.getConnection(
						"jdbc:sqlserver://localhost:1433;databaseName=projectTWO;TrustServerCertificate=True", "sa",
						"Passw0rd!!");
				String sql = "select * from TicketInfo where TicketID = ?";
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setString(1, updateTarget);
				ResultSet rs = stmt.executeQuery();
				TicketDTO tk = new TicketDTO();
				if (rs.next()) {
					tk.setTicketID(rs.getInt("TicketID"));
					tk.setTranNo(rs.getString("TranNo"));
					tk.setSeat(rs.getString("Seat"));
					tk.setDeparture_ST(rs.getString("Departure_ST"));
					tk.setDestination_ST(rs.getString("Destination_ST;"));
					tk.setDeparture_time(rs.getString("Departure_time"));
					tk.setArrival_time(rs.getString("Arrival_time"));
					tk.setPrice(rs.getInt("price"));
					tk.setDate(rs.getDate("Date"));
				}
				request.setAttribute("tk", tk);
				stmt.close();
				request.getRequestDispatcher("/html/Update.jsp").forward(request, response);

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
		} else {
			doGet(request, response);
		}

	}

}
