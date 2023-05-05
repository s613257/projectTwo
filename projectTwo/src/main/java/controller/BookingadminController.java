package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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

@WebServlet("/BookingadminController")
public class BookingadminController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BookingadminController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		System.out.println("action = " + action);
		if (action == null) {
			action = "";
		}
		switch (action) {
		case Utils.ACTION_CREATE:
			create(request, response);
			break;
		case "doInsert":
			doInsert(request, response);
			break;
		case Utils.ACTION_UPDATE:
			update(request, response);
			break;
		default:
			index(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public void index(HttpServletRequest request, HttpServletResponse response) {
		try {

			request.getRequestDispatcher("/html/BookingAdmin.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

	public void create(HttpServletRequest request, HttpServletResponse response) {
		try {
			BookingDAO bookingDAO = new BookingDAOImpl();
			request.setAttribute("stationList", bookingDAO.getAllStationInfo());
			request.setAttribute("priceInfos", bookingDAO.getAllPriceInfo());
			request.getRequestDispatcher("/html/BookingAdminDataPage.jsp").forward(request, response); // 這裡就跳轉到新增畫面了
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

	public void doInsert(HttpServletRequest request, HttpServletResponse response) {
		try {
			TicketDAO tkaDao = new TicketDAOImpl();
			TicketDTO tkdto = new TicketDTO();
			String TicketID = request.getParameter("TicketID");
			tkdto.setTicketID(Integer.parseInt(TicketID));
			String TranNo = request.getParameter("TranNo");
			tkdto.setTranNo(TranNo);
			String Seat = request.getParameter("Seat");
			tkdto.setSeat(Seat);
			String Departure_ST = request.getParameter("Departure_ST");
			tkdto.setDeparture_ST(Departure_ST);
			String Destination_ST = request.getParameter("Destination_ST");
			tkdto.setDestination_ST(Destination_ST);
			String Departure_time = request.getParameter("Departure_time");
			tkdto.setDeparture_time(Departure_time);
			String Arrival_time = request.getParameter("Arrival_time");
			tkdto.setArrival_time(Arrival_time);
			String Price = request.getParameter("price"); // 噗噗
			tkdto.setPrice(Integer.parseInt(Price));
			String date = request.getParameter("Date");
			tkdto.setDate(date);
			// 到這裡 tkdto 的資料才設定完成 所以這邊才可以做tkaDao.insertTicketInfo <3
			tkaDao.insertTicketInfo(tkdto);
			request.getRequestDispatcher("/html/BookingAdmin.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

	public void update(HttpServletRequest request, HttpServletResponse response) {
		try {
			TicketDAO tkaDao = new TicketDAOImpl();
			TicketDTO tkdto = new TicketDTO();
			tkaDao.updateTicketInfo(tkdto);
			String TicketID = request.getParameter("TicketID");
			tkdto.setTicketID(Integer.parseInt(TicketID));
			String TranNo = request.getParameter("TranNo");
			tkdto.setTranNo(TranNo);
			String Seat = request.getParameter("Seat");
			tkdto.setSeat(Seat);
			String Departure_ST = request.getParameter("Departure_ST");
			tkdto.setDeparture_ST(Departure_ST);
			String Destination_ST = request.getParameter("Destination_ST");
			tkdto.setDestination_ST(Destination_ST);
			String Departure_time = request.getParameter("Departure_time");
			tkdto.setDeparture_time(Departure_time);
			String Arrival_time = request.getParameter("Arrival_time");
			tkdto.setArrival_time(Arrival_time);
			String Price = request.getParameter("Price");
			tkdto.setPrice(Integer.parseInt(Price));
			String Date = request.getParameter("Date");
			Date d = new Date();
			tkdto.setDate(d);
			request.setAttribute("tkdto", tkdto);
			request.getRequestDispatcher("/html/Update.jsp").forward(request, response);
		} catch (IOException | ServletException e) {
			e.printStackTrace();
		}
	}

}
