package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dto.TicketDTO;

@WebServlet("/DeleteBook")
public class DeleteBook extends HttpServlet {
	 private static final long serialVersionUID = 1L;

	 public DeleteBook() {
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
	   String sql = "delete from TicketInfo where TicketID = ?";
	   PreparedStatement stmt = conn.prepareStatement(sql);
	   stmt.setInt(1, TicketID);
	   TicketDTO tk = new TicketDTO();

	   tk.setTicketID(TicketID);
	   tk.setTranNo(TranNo);
	   tk.setSeat(Seat);
	   tk.setDeparture_ST(Departure_ST);
	   tk.setDestination_ST(Destination_ST);
	   tk.setDeparture_time(Departure_time);
	   tk.setArrival_time(Arrival_time);
	   tk.setPrice(price);
	   tk.setDate(null);

	   int row = stmt.executeUpdate();
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

	 protected void doPost(HttpServletRequest request, HttpServletResponse response)
	   throws ServletException, IOException {
	  doGet(request, response);
	 }
	}
